
async function botonSoporte() {
    const usuarioStr = localStorage.getItem("usuario");
    console.log(usuarioStr);
    if (!usuarioStr) {
        alert("No hay datos de usuario en localStorage");
        return;
    }
    const usuario = JSON.parse(usuarioStr);
    const rutUsuario = usuario.rut;

    try {
        const responseDatos = await fetch(`http://localhost:8081/api/v1/usuarios/datos-usuario?rut=${rutUsuario}`);
        
        if (!responseDatos.ok) {
            alert("Error al obtener datos del usuario");
            return;
        }

        const datosUsuario = await responseDatos.json();
        const rut = datosUsuario.rut;
        const nombre = datosUsuario.nombre;

        const responseSoporte = await fetch('http://localhost:8085/api/v1/soporte', {  
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                rut: rut,
                nombreUsuario: nombre,
                mensaje: "Necesito ayuda con mi cuenta de estudiante",
                estado: "Pendiente",
                fechaCreacion: new Date().toISOString()

            })
        });

        if (responseSoporte.ok) {
            const data = await responseSoporte.json();
            alert("Solicitud de soporte enviada con el siguiente mensaje: " + data.mensaje + ". Un agente de soporte se pondrá en contacto contigo pronto.");
        } else {
            alert("Error al enviar la solicitud de soporte");
        }
    } catch (error) {
        console.error("Error en la solicitud de soporte:", error);
        alert("Error al enviar la solicitud de soporte");
    }
}