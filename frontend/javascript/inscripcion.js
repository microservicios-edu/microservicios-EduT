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

    const pagoResponse = await fetch('http://localhost:8083/api/v1/pagos', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(pago)
    });

    if (pagoResponse.ok) {
      alert("¡Inscripción y pago realizados exitosamente!");
    } else {
      alert("Inscripción realizada, pero hubo un error al registrar el pago.");
    }

    // Esperamos respuesta como string (según tu backend actual)
    const pagoMensaje = await pagoResponse.text();
    alert(pagoMensaje);

    // OPCIONAL: buscar el último pago por usuario para obtener el ID
    const pagosUsuarioResp = await fetch(`http://localhost:8083/api/v1/pagos`);
    const listaPagos = await pagosUsuarioResp.json();

    const ultimoPago = listaPagos
      .filter(p => p.usuario === datosUsuario.rut && p.fechaPago === fechaPago && p.monto === monto)
      .sort((a, b) => b.id - a.id)[0]; // Último por ID

    if (!ultimoPago || !ultimoPago.id) {
      alert("Pago realizado, pero no se pudo obtener su ID para generar notificación.");
      return;
    }

    // Crear notificación asociada
    const notificacion = {
      mensaje: `Pago exitoso del curso ${cursoId} por parte de ${datosUsuario.nombre}`,
      fechaEnvio: fechaPago,
      pagoId: ultimoPago.id
    };

    const notificacionResponse = await fetch('http://localhost:8083/api/v1/notificaciones', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(notificacion)
    });

    if (notificacionResponse.ok) {
      alert("¡Pago y notificación registrados exitosamente!");
    } else {
      alert("Pago realizado, pero error al registrar notificación.");
    }

  } catch (error) {
    console.error("Error en la inscripción:", error);
    alert("Error al conectarse con el servidor.");
  }
}

// Al cargar el DOM, obtener y renderizar los cursos
document.addEventListener("DOMContentLoaded", async () => {
  try {
    const response = await fetch('http://localhost:8084/api/v1/cursos/disponibles');
    const cursos = await response.json();

    const container = document.getElementById('cursosContainer');
    container.innerHTML = ''; // Limpiar contenedor

    cursos.forEach(curso => {
      const cursoDiv = document.createElement('div');
      cursoDiv.classList.add('curso');
      console.log("Curso recibido:", curso);
      cursoDiv.innerHTML = `
        <h3>${curso.nombre}</h3>
        <p>${curso.descripcion}</p>
        <button data-id="${curso.id}">Inscribirse</button>
        <button class="btn btn-secondary" onclick="window.location.href='../html/home.html'">Volver</button>
      `;

      // Buscar el botón y agregarle el evento
      const boton = cursoDiv.querySelector("button");
      boton.addEventListener("click", () => inscribirUsuario(curso.id));

      container.appendChild(cursoDiv);
    });
  } catch (error) {
    alert("Error al cargar los cursos. Volverá a la página de inicio.");
    window.location.href = "../html/homePageEstudiante.html"; // Redirigir a la página de inicio del estudiante
  }
});