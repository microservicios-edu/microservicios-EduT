//variables generales
let rut = document.getElementById('rut');
let clave = document.getElementById('clave');

  document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const rut = document.getElementById('rut').value;
    const clave = document.getElementById('clave').value;
  
    try {
      const response = await fetch(`http://localhost:8081/api/v1/usuarios/login?rut=${rut}&clave=${clave}`);
      
      // Verificamos si la respuesta fue exitosa (status 200)
      if (response.ok) {
        const data = await response.json(); 
  
        if (data.message == "Autenticación exitosa") {
          alert(data.message);
          console.log("Usuario autenticado:", data);
          console.log("Datos del usuario:", data);

          localStorage.setItem('usuario', JSON.stringify(data));

          const tipo = data.tipoUsuario.toLowerCase();

          if (tipo === "estudiante") {
            window.location.href = "/frontend/html/homePageEstudiante.html";
          } else if (tipo === "docente") {
            window.location.href = "/frontend/html/homePageProfesor.html";
          } else if (tipo === "gerente") {
            window.location.href = "/frontend/html/homePageGerenteCurso.html";
          } else if (tipo === "administrador") {
            window.location.href = "/frontend/html/homeAdministrador.html";
          } else if (tipo === "soporte") {
            window.location.href = "/frontend/html/homePageSoporte.html";
          } else {
            alert("Tipo de usuario desconocido. Contacta a tu administrador");
          }
          
        } else {
          alert(data.message);  // muestra "Usuario no encontrado" o "Clave incorrecta"
          console.log("No autenticado:", data);
          // No redirige, se queda en index.html
        }
      } else {
        alert("Error en la respuesta del servidor");
      }
  
    } catch (error) {
      alert("Error al autenticarse");
      console.error(error);
    }
  });
  

  function botonLimpiar(){

    if(rut.value !== '' || clave.value !== ''){
      rut.value = '';
      clave.value = '';
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
    
    // Se limpian los campos por si se quedó algo escrito
    document.getElementById('nuevoRut').value = '';
    document.getElementById('nuevaClave').value = '';
  }

  async function guardarContrasena() {
  const rut = document.getElementById('nuevoRut').value;
  const clave = document.getElementById('nuevaClave').value;

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
      body: JSON.stringify({ rut, password: clave }) // Aquí se usa "password"
    });

    const data = await response.json();
    alert(data.message || "Contraseña cambiada exitosamente");
    cerrarFormularioContrasena();
  } catch (error) {
    alert("Error al cambiar la contraseña");
  }
}
