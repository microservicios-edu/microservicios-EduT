//variables generales
let rutInput = document.getElementById('rut'); // Renamed from 'rut' to avoid conflict with 'const rut'
let claveInput = document.getElementById('clave'); // Renamed from 'clave' to avoid conflict with 'const clave'

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const rut = rutInput.value.trim(); // Trim whitespace
    const clave = claveInput.value.trim(); // Trim whitespace

    try {
        const response = await fetch(`http://localhost:8081/api/v1/usuarios/login?rut=${rut}&clave=${clave}`);

        // Check if the response was successful (status 200-299)
        if (response.ok) {
            const data = await response.json();

            if (data.message === "Autenticación exitosa") { // Use strict equality ===
                alert(data.message);
                console.log("Usuario autenticado:", data);

                // Store user data in localStorage
                localStorage.setItem('usuario', JSON.stringify(data));

                const tipo = data.tipoUsuario.toLowerCase().trim(); // Trim and lowercase for robustness

                // Redirect based on user type using relative paths
                if (tipo === "estudiante") {
                    window.location.href = "html/homePageEstudiante.html";
                } else if (tipo === "docente") {
                    window.location.href = "html/homePageProfesor.html";
                } else if (tipo === "gerente") {
                    // Assuming gerente also goes to homeAdministrador as per your previous code
                    window.location.href = "html/homeAdministrador.html";
                } else if (tipo === "administrador") {
                    window.location.href = "html/homeAdministrador.html";
                } else if (tipo === "soporte") {
                    window.location.href = "html/homePageSoporte.html";
                } else {
                    alert("Tipo de usuario desconocido. Contacta a tu administrador.");
                }

            } else {
                alert(data.message); // Displays "Usuario no encontrado" or "Clave incorrecta"
                console.log("No autenticado:", data);
                // Stays on index.html if authentication fails
            }
        } else {
            // Handle non-OK HTTP responses (e.g., 404, 500)
            let errorMessage = `Error en la respuesta del servidor: ${response.status} ${response.statusText}`;
            try {
                const errorData = await response.json();
                errorMessage = errorData.message || errorMessage;
            } catch (jsonError) {
                // If response is not JSON, use the raw text
                errorMessage = await response.text() || errorMessage;
            }
            alert(errorMessage);
            console.error("HTTP Error:", response.status, response.statusText, errorMessage);
        }

    } catch (error) {
        // Catch network errors or other unexpected issues
        alert("Error al autenticarse. Verifica la conexión con el servidor.");
        console.error("Authentication Error:", error);
    }
});

function botonLimpiar(){
    // Using rutInput and claveInput variables
    if(rutInput.value !== '' || claveInput.value !== ''){
        rutInput.value = '';
        claveInput.value = '';
        alert('Datos borrados');
    } else {
        alert('No hay datos que borrar');
    }
}

function mostrarFormularioContrasena() {
    document.getElementById('btnNuevaContrasena').style.display = 'none';
    document.getElementById('formNuevaContrasena').style.display = 'block';
}

function cerrarFormularioContrasena() {
    document.getElementById('btnNuevaContrasena').style.display = 'inline-block';
    document.getElementById('formNuevaContrasena').style.display = 'none';

    // Clear fields in the new password form
    document.getElementById('nuevoRut').value = '';
    document.getElementById('nuevaClave').value = '';
}

async function guardarContrasena() {
    const rut = document.getElementById('nuevoRut').value.trim();
    const clave = document.getElementById('nuevaClave').value.trim();

    if (rut === '' || clave === '') {
        alert('Por favor, complete todos los campos');
        return;
    }

    try {
        const response = await fetch('http://localhost:8081/api/v1/usuarios/cambiar-contrasena', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ rut, password: clave }) // Backend expects "password"
        });

        // Handle non-OK responses from the password change endpoint
        if (!response.ok) {
            let errorMessage = `Error al cambiar la contraseña: ${response.status} ${response.statusText}`;
            try {
                const errorData = await response.json();
                errorMessage = errorData.message || errorMessage;
            } catch (jsonError) {
                errorMessage = await response.text() || errorMessage;
            }
            alert(errorMessage);
            return;
        }

        const data = await response.json();
        alert(data.message || "Contraseña cambiada exitosamente");
        cerrarFormularioContrasena(); // Close and clear the form on success
    } catch (error) {
        alert("Error al cambiar la contraseña. Verifica la conexión.");
        console.error("Password change error:", error);
    }
}