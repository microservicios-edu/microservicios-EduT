// Función global para inscribir usuario
async function inscribirUsuario(cursoId) {

  const rutUsuarioAutenticado = JSON.parse(localStorage.getItem('usuario'))?.rut; //obtenemos el rut del alamacenamiento local

  if (!rutUsuarioAutenticado) {
    alert("Usuario no autenticado.");
    console.log(rutUsuarioAutenticado);
    return;
  }

  try {
    // Obtener los datos del usuario autenticado
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

    // Enviar datos al microservicio de matrículas
    const response = await fetch('http://localhost:8082/api/v1/matricula', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(matricula)
    });

    if (response.ok) {
      alert("¡Inscripción exitosa!");
    } else {
      alert("Error al inscribirse.");
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
