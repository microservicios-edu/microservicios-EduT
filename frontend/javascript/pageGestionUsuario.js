const form = document.getElementById('userForm');
const nombreInput = document.getElementById('nombre');
const rutInput = document.getElementById('rut');
const userList = document.getElementById('userList');
const tipoUsuarioInput = document.getElementById('tipoUsuario');
const clave = document.getElementById('password');

let editingId = null; // ID del usuario que se está editando

// Cargar usuarios al iniciar
document.addEventListener('DOMContentLoaded', async () => {
    await cargarUsuarios();
});

// Manejador del envío del formulario para crear o actualizar usuarios
form.addEventListener('submit', async function (e) {
    e.preventDefault(); // Previene el recargado de la página

    // Elimina espacios en blanco al inicio/final de los inputs
    const nombre = nombreInput.value.trim();
    const rut = rutInput.value.trim();
    const tipoUsuario = tipoUsuarioInput.value;
    const password = clave.value.trim();

    // --- Validación básica en el cliente ---
    if (!nombre || !rut || !tipoUsuario || !password) {
        alert("Por favor, complete todos los campos para crear/actualizar el usuario.");
        return; // Detiene la ejecución si algún campo está vacío
    }

    // Objeto con los datos del usuario a enviar
    const usuario = {
        nombre,
        rut,
        tipoUsuario,
        password
    };

    try {
        let response;
        if (editingId) {
            // Si editingId tiene un valor, es una operación de ACTUALIZACIÓN (PUT)
            response = await fetch(`http://localhost:8081/api/v1/usuarios/${editingId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(usuario)
            });
        } else {
            // Si editingId es null, es una operación de CREACIÓN (POST)
            response = await fetch('http://localhost:8081/api/v1/usuarios', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(usuario)
            });
        }

        // --- Manejo de la respuesta del servidor ---
        if (!response.ok) {
            // Si la respuesta no fue exitosa (ej. 409 Conflict, 500 Internal Server Error)
            let errorMessage = "Error en la respuesta del servidor.";
            try {
                // Intenta leer el mensaje de error del cuerpo de la respuesta si es JSON
                const errorData = await response.json();
                errorMessage = errorData.message || errorMessage; // Usa el mensaje si existe
            } catch (jsonError) {
                // Si no es JSON, usa el texto crudo de la respuesta o el status
                errorMessage = await response.text() || response.statusText;
            }
            alert(`Operación fallida: ${errorMessage}`);
            return; // Detiene la ejecución
        }

        // Si la operación fue exitosa
        alert(editingId ? "Usuario actualizado exitosamente." : "Usuario creado exitosamente.");

        // Reinicia el formulario
        editingId = null; // Vuelve al modo de creación
        form.reset();     // Limpia los campos del formulario
        await cargarUsuarios(); // Recarga la lista para mostrar los cambios
    } catch (error) {
        // Captura errores de red (ej. servidor no disponible)
        console.error("Error al procesar la solicitud:", error);
        alert("Error de conexión. Asegúrate de que el servidor esté funcionando.");
    }
});

// Función para cargar y mostrar la lista de usuarios
async function cargarUsuarios() {
    try {
        const response = await fetch('http://localhost:8081/api/v1/usuarios');

        if (!response.ok) {
            alert("Error al cargar usuarios: " + response.statusText);
            return;
        }

        const usuarios = await response.json(); // Convierte la respuesta a JSON

        userList.innerHTML = ''; // Limpia el contenedor de usuarios antes de añadir nuevos

        // Muestra un mensaje si no hay usuarios
        if (usuarios.length === 0) {
            const messageDiv = document.createElement('div');
            messageDiv.classList.add('message-container');
            messageDiv.innerHTML = `<p>No hay usuarios registrados aún. ¡Crea uno!</p>`;
            userList.appendChild(messageDiv);
            return;
        }

        // Itera sobre cada usuario y crea su elemento HTML
        usuarios.forEach((usuario, index) => {
            const div = document.createElement('div');
            div.classList.add('usuario');
            div.dataset.id = usuario.id; // Guarda el ID del usuario en el elemento HTML

            div.innerHTML = `
                <div class="user-details">
                    <p><strong>Nombre:</strong> ${usuario.nombre}</p>
                    <p><strong>RUT:</strong> ${usuario.rut}</p>
                    <p><strong>Tipo de Usuario:</strong> ${usuario.tipoUsuario}</p>
                    <p class="password-display"><strong>Clave:</strong> ${usuario.password}</p>
                </div>
                <div class="user-actions">
                    <button class="editar">Editar</button>
                    <button class="eliminar">Eliminar</button>
                </div>
            `;

            // Agrega el div del usuario al contenedor
            userList.appendChild(div);

            // Aplica la animación de fade-in con un pequeño retardo
            setTimeout(() => {
                div.classList.add('fade-in');
            }, index * 100); // Retardo de 100ms por cada tarjeta
            
            // --- Event Listeners para los botones de cada usuario ---
            
            // Botón Editar
            div.querySelector('.editar').addEventListener('click', () => {
                editingId = usuario.id; // Guarda el ID del usuario que se va a editar
                nombreInput.value = usuario.nombre;
                rutInput.value = usuario.rut;
                tipoUsuarioInput.value = usuario.tipoUsuario;
                clave.value = usuario.password; // Carga la clave para edición
                // Opcional: Desplazar la vista al formulario para facilitar la edición
                form.scrollIntoView({ behavior: 'smooth', block: 'start' });
            });

            // Botón Eliminar
            div.querySelector('.eliminar').addEventListener('click', async () => {
                if (confirm(`¿Está seguro de eliminar al usuario con RUT ${usuario.rut}? Esta acción no se puede deshacer.`)) {
                    try {
                        const res = await fetch(`http://localhost:8081/api/v1/usuarios/${usuario.id}`, {
                            method: 'DELETE'
                        });

                        if (!res.ok) {
                            const errorText = await res.text();
                            alert(`Error al eliminar usuario: ${errorText}`);
                            return;
                        }

                        const text = await res.text(); // Mensaje de éxito del backend
                        alert(text);
                        await cargarUsuarios(); // Recarga la lista para reflejar la eliminación
                    } catch (err) {
                        console.error("Error al eliminar usuario:", err);
                        alert("Error de conexión al intentar eliminar usuario. Verifique el servidor.");
                    }
                }
            });
        });
    } catch (error) {
        console.error("Error al cargar usuarios:", error);
        alert("Error al cargar usuarios. Verifique la conexión con el servidor y la URL.");
    }
}