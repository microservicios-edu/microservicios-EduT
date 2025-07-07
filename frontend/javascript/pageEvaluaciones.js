const API_URL = 'http://localhost:8080/api/v1/evaluaciones';

const rutUsuario = localStorage.getItem('rut');

// Crear nueva evaluación
async function addEvaluacion(event) {
    event.preventDefault();

    if (!rutUsuario) {
        console.error('No se encontró el RUT del usuario en localStorage.');
        return;
    }

    const nuevaEvaluacion = {
        nombre: document.getElementById('courseName').value,
        descripcion: document.getElementById('courseDescription').value,
        puntaje: parseInt(document.getElementById('puntaje').value),
        fecha: document.getElementById('fecha').value,
        rutUsuario: rutUsuario
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevaEvaluacion)
        });

        console.log('Enviando evaluación:', nuevaEvaluacion);

        if (!response.ok) {
            console.error('Error al enviar la evaluación:', response.statusText);
        } else {
            alert('Evaluación creada exitosamente.');
        }
    } catch (error) {
        alert('Error en la solicitud:', error);
    }

    event.target.reset();
}

// Eliminar curso
async function deleteCourse(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
}

// Editar curso
async function editCourse(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const course = await response.json();

    document.getElementById('courseName').value = course.nombre;
    document.getElementById('courseDescription').value = course.descripcion;
    document.getElementById('puntaje').value = course.cuposTotales;
    document.getElementById('fecha').value = course.cuposDisponibles;

    const form = document.getElementById('evaluacionForm');
    form.onsubmit = async (event) => {
        event.preventDefault();
        await updateCourse(id);
    };
}

// Actualizar curso
async function updateCourse(id) {
    const updatedCourse = {
        nombre: document.getElementById('courseName').value,
        descripcion: document.getElementById('courseDescription').value,
        cuposTotales: parseInt(document.getElementById('puntaje').value),
        cuposDisponibles: parseInt(document.getElementById('fecha').value),
    };

    await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedCourse)
    });

    document.getElementById('evaluacionForm').reset();
    document.getElementById('evaluacionForm').onsubmit = addEvaluacion;
}

// Buscar en la tabla (solo frontend)
// function searchCourses() {
//     const searchTerm = document.getElementById('searchInput').value.toLowerCase();
//     const rows = document.querySelectorAll('#courseList tr');

//     rows.forEach(row => {
//         const text = row.innerText.toLowerCase();
//         row.style.display = text.includes(searchTerm) ? '' : 'none';
//     });
// }

// Inicializar
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('evaluacionForm').onsubmit = addEvaluacion;
});
