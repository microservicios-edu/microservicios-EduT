const API_URL = 'http://localhost:8080/api/v1/reportes';

// Renderizar cursos desde la API
async function renderEvaluaciones() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        console.log('Respuesta completa:', data);

        let evaluaciones;

        if (Array.isArray(data)) {
            evaluaciones = data;
        } else if (Array.isArray(data.reportes)) {
            evaluaciones = data.reportes;
        } else {
            console.warn('La respuesta no contiene datos de reportes:', data);
            alert(data.mensaje || 'No hay evaluaciones registradas.');
            return;
        }

        const tbody = document.getElementById('evaluacionList');
        tbody.innerHTML = '';

        evaluaciones.forEach(evaluacion => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${evaluacion.id}</td>
                <td>${evaluacion.rutUsuario}</td>
                <td>${evaluacion.contenido}</td>
                <td>${evaluacion.fecha}</td>
                <td>
                    <button class="delete-button" onclick="deleteevaluacion(${evaluacion.id})">Cerrar y finalizar ticket</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error al cargar los cursos:', error);
        alert('No se pudo cargar las evaluaciones.');
        alert('Contacta al área de Soporte en el Home presionando "Solicitar Ayuda".');
        alert('Volverás al Home.');
        window.location.href = '/frontend/html/homePageProfesor.html';
    }
}

// Buscar en la tabla (solo frontend)
function searchCourses() {
     const searchTerm = document.getElementById('searchInput').value.toLowerCase();
     const rows = document.querySelectorAll('#evaluacionList tr');

     rows.forEach(row => {
         const text = row.innerText.toLowerCase();
         row.style.display = text.includes(searchTerm) ? '' : 'none';
     });
}


async function deleteevaluacion(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        alert('Evaluación eliminada exitosamente.');
        renderEvaluaciones();
    } catch (error) {
        console.error('Error al eliminar la evaluación:', error);
        alert('No se pudo eliminar la evaluación.');
    }
}



// Inicializar
document.addEventListener('DOMContentLoaded', () => {
    // document.getElementById('courseForm').onsubmit = addCourse;
    document.getElementById('searchInput').addEventListener('input', searchCourses);
    renderEvaluaciones();
});
