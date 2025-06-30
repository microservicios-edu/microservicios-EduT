async function inscribirUsuario(cursoId) {
  const rutUsuarioAutenticado = JSON.parse(localStorage.getItem('usuario'))?.rut;

  if (!rutUsuarioAutenticado) {
    alert("Usuario no autenticado.");
    return;
  }

  try {
    // Obtener los datos del usuario
    const datosUsuarioResponse = await fetch(`http://localhost:8081/api/v1/usuarios/datos-usuario?rut=${rutUsuarioAutenticado}`);
    const datosUsuario = await datosUsuarioResponse.json();

    if (datosUsuario.message) {
      alert("No se pudieron obtener los datos del usuario.");
      return;
    }

    const matricula = {
      rut: datosUsuario.rut,
      nombre: datosUsuario.nombre,
      cursoId: cursoId
    };

    // Registrar matrícula
    const matriculaResponse = await fetch('http://localhost:8082/api/v1/matricula', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(matricula)
    });

    if (!matriculaResponse.ok) {
      alert("Error al inscribirse.");
      return;
    }

    // Registrar pago
    const pago = {
      usuario: datosUsuario.rut,
      monto: 50000,
      fechaPago: new Date().toISOString().split("T")[0]
    };
    console.log(pago);

    const pagoResponse = await fetch('http://localhost:8083/api/v1/pagos', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(pago)
    });

    if (pagoResponse.ok) {
      alert(`✅ Se ha registrado un pago de $${pago.monto} por parte del usuario con el Rut ${matricula.rut} para el curso ${cursoId}.`); //Se simplifica la noificación
    } else {
      alert("Inscripción realizada, pero hubo un error al registrar el pago.");
    }

    // Esperamos respuesta como string (según tu backend actual)
    const pagoMensaje = await pagoResponse.text();
    if (!pagoMensaje) {
      alert("Pago realizado, pero no se recibió mensaje del servidor.");
      return;
    }

  } catch (error) {
    console.error("Error en la inscripción:", error);
    alert("Error al conectarse con el servidor.");
  }
}

// Al cargar el DOM, obtener y renderizar los cursos
document.addEventListener("DOMContentLoaded", async () => {
  const container = document.getElementById('cursosContainer');
  container.innerHTML = '<img src="../../public/gif/loading.gif" alt="Cargando...">'; // Mostrar gif de carga

  try {
    // Se espera 4 segundos antes de hacer la solicitud. Esto es para que se pueda apreciar el GIF de loading
    await new Promise(resolve => setTimeout(resolve, 3500));

    const response = await fetch('http://localhost:8084/api/v1/cursos/disponibles');
    const cursos = await response.json();

    container.innerHTML = ''; // Limpiar loading gif

    if (cursos.length === 0) {
      container.innerHTML = `<p>No hay cursos disponibles.</p>
                            <button class="btn btn-secondary" onclick="window.location.href='../html/homePageEstudiante.html'">Volver</button>`;
      return;
    }

    cursos.forEach((curso, index) => {
    const cursoDiv = document.createElement('div');
    cursoDiv.classList.add('curso');
    console.log("Curso recibido:", curso);
    cursoDiv.innerHTML = `
        <h3>${curso.nombre}</h3>
        <p>${curso.descripcion}</p>
        <button data-id="${curso.id}">Inscribirse</button>
        <button class="btn btn-secondary" onclick="window.location.href='../html/homePageEstudiante.html'">Volver al Home</button>
    `;

    // Buscar el botón y agregarle el evento
    const boton = cursoDiv.querySelector("button");
    boton.addEventListener("click", () => inscribirUsuario(curso.id));

    container.appendChild(cursoDiv);

    // Add fade-in class after a small delay for a staggered effect
    setTimeout(() => {
        cursoDiv.classList.add('fade-in');
    }, index * 100); // 100ms delay for each subsequent card
});
  } catch (error) {
    alert("Error al cargar los cursos. Volverá a la página de inicio.");
    window.location.href = "../html/homePageEstudiante.html"; // Redirigir a la página de inicio del estudiante
  }
});