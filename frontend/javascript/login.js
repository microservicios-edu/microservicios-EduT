//variables generales
let rut = document.getElementById('rut');
let clave = document.getElementById('clave');

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const rut = document.getElementById('rut').value;
    const clave = document.getElementById('clave').value;
  
    try {
      const response = await fetch(`/api/login?rut=${rut}&clave=${clave}`);
      const data = await response.json();
      alert(data.message || "Autenticación exitosa");
      window.location.href = "home.html";
    } catch (error) {
      alert("Error al autenticarse");
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
        method: 'POST',
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
  