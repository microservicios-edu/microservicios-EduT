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
  