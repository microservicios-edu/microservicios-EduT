const API_URL = 'http://localhost:8084/api/v1/cursos';

// Renderizar cursos desde la API
async function renderCourses() {
    try {
        const response = await fetch(API_URL);
        
        // Verifica que la respuesta sea correcta
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        
        const courses = await response.json();

        console.log('Cursos obtenidos:', courses); // Agrega este log para depurar

        const tbody = document.getElementById('courseList');
        tbody.innerHTML = ''; // Limpiar tabla antes de cargar nuevos datos

        courses.forEach(course => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${course.nombre}</td>
                <td>${course.descripcion}</td>
                <td>${course.cuposTotales}</td>
                <td>${course.cuposDisponibles}</td>
                <td>${course.categoria}</td>
                <td>
                    <button class="edit-button" onclick="editCourse(${course.id})">Editar</button>
                    <button class="delete-button" onclick="deleteCourse(${course.id})">Eliminar</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error al cargar los cursos:', error);
        alert('No se pudieron cargar los cursos. Ver consola para más detalles.');
    }
}


// Crear un nuevo curso
async function addCourse(event) {
    event.preventDefault();

    const newCourse = {
        nombre: document.getElementById('courseName').value,
        descripcion: document.getElementById('courseDescription').value,
        cuposTotales: parseInt(document.getElementById('totalSlots').value),
        cuposDisponibles: parseInt(document.getElementById('availableSlots').value),
        categoria: document.getElementById('category').value,
        activo: true
    };

    await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newCourse)
    });

    event.target.reset();
    renderCourses();
}

// Eliminar curso
async function deleteCourse(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    renderCourses();
}

// Editar curso
async function editCourse(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const course = await response.json();

    document.getElementById('courseName').value = course.nombre;
    document.getElementById('courseDescription').value = course.descripcion;
    document.getElementById('totalSlots').value = course.cuposTotales;
    document.getElementById('availableSlots').value = course.cuposDisponibles;
    document.getElementById('category').value = course.categoria;

    const form = document.getElementById('courseForm');
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
        cuposTotales: parseInt(document.getElementById('totalSlots').value),
        cuposDisponibles: parseInt(document.getElementById('availableSlots').value),
        categoria: document.getElementById('category').value,
        activo: true
    };

    await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedCourse)
    });

    document.getElementById('courseForm').reset();
    document.getElementById('courseForm').onsubmit = addCourse;
    renderCourses();
}

// Buscar en la tabla (solo frontend)
function searchCourses() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const rows = document.querySelectorAll('#courseList tr');

    rows.forEach(row => {
        const text = row.innerText.toLowerCase();
        row.style.display = text.includes(searchTerm) ? '' : 'none';
    });
}

// Inicializar
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('courseForm').onsubmit = addCourse;
    document.getElementById('searchInput').addEventListener('input', searchCourses);
    renderCourses();
});
