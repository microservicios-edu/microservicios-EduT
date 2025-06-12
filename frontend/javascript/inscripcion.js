// Cargar y mostrar los cursos al iniciar
document.addEventListener("DOMContentLoaded", async () => {
  try {
    const response = await fetch('/api/cursos'); // Ruta GET para obtener los cursos
    const cursos = await response.json();

    const container = document.getElementById('cursosContainer');
    container.innerHTML = ''; // Limpiar por si acaso

    cursos.forEach(curso => {
      const cursoDiv = document.createElement('div');
      cursoDiv.classList.add('curso');

      cursoDiv.innerHTML = `
        <h3>${curso.nombre}</h3>
        <p>${curso.descripcion}</p>
        <button data-id="${curso.id}">Inscribirse</button>
      `;

      // Agregar evento al botón
      const boton = cursoDiv.querySelector('button');
      boton.addEventListener('click', async () => {
        try {
          const res = await fetch(`/api/inscribirse?curso_id=${curso.id}`);
          const result = await res.json();
          alert(result.message || "Inscripción exitosa");
        } catch (err) {
          alert("Error al inscribirse");
        }
      });

      container.appendChild(cursoDiv);
    });
  } catch (error) {
    alert("Error al cargar los cursos. Serás redirigido al Home.");
    window.location.href = "../html/home.html";
  }
});
