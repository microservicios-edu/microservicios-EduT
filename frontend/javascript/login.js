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
  