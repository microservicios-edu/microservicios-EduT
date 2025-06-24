

//funcionalidad del botón soporte

//seguir afinando la funcionalidad.

async function botonSoporte() {
    try {

        const rutUsuario = await fetch(`http://localhost:8081/api/v1/usuarios/datos-usuario?rut=${rutUsuario}`);
        const responseDatos = await fetch(`http://localhost:8081/api/v1/usuarios/datos-usuario?rut=${responseDatos}`);
        
        if (!responseDatos.ok) {
            alert("Error al obtener datos del usuario");
            return;
        }

        const datosUsuario = await responseDatos.json();
        const rut = datosUsuario.rut;
        const nombre = datosUsuario.nombre;

        // Enviar solicitud de soporte con los datos obtenidos
        const responseSoporte = await fetch('http://localhost:8081/api/v1/usuarios/soporte', {  
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                rut: rut,
                nombreUsuario: nombre,
                mensaje: "Necesito ayuda con mi cuenta de estudiante"
            })
        });

        if (responseSoporte.ok) {
            const data = await responseSoporte.json();
            alert("Solicitud de soporte enviada: " + data.message);
        } else {
            alert("Error al enviar la solicitud de soporte");
        }
    } catch (error) {
        console.error("Error en la solicitud de soporte:", error);
        alert("Error al enviar la solicitud de soporte");
    }
}