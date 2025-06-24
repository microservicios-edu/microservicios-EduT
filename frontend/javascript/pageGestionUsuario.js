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

form.addEventListener('submit', async function (e) {
  e.preventDefault();

  const nombre = nombreInput.value;
  const rut = rutInput.value;
  const tipoUsuario =  tipoUsuarioInput.value;
  const password = clave.value;

  const usuario = {
    nombre,
    rut,
    tipoUsuario,
    password
  };

  try {
    let response;
    if (editingId) {
      response = await fetch(`http://localhost:8081/api/v1/usuarios/${editingId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
      });
    } else {
      response = await fetch('http://localhost:8081/api/v1/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
      });
    }

    if (!response.ok) throw new Error("Error en la respuesta");

    const data = await response.json();
    alert(editingId ? "Usuario actualizado" : "Usuario creado");

    editingId = null;
    form.reset();
    await cargarUsuarios();
  } catch (error) {
    alert("Error al procesar la solicitud");
  }
});

async function cargarUsuarios() {
  try {
    const response = await fetch('http://localhost:8081/api/v1/usuarios');
    const usuarios = await response.json();

    userList.innerHTML = '';

    usuarios.forEach(usuario => {
      const div = document.createElement('div');
      div.classList.add('usuario');

      div.innerHTML = `
        <strong>Usuario</strong>
        <strong>${usuario.nombre}</strong> - ${usuario.rut}
        <strong>Tipo de Usuario: </strong>${usuario.tipoUsuario}
        </strong> - ${usuario.password}
        <button class="editar">Editar</button>
        <button class="eliminar">Eliminar</button>
      `;


      // Botón editar
      div.querySelector('.editar').addEventListener('click', () => {
        editingId = usuario.id;
        rutInput.value = usuario.rut;
        nombreInput.value = usuario.nombre;
        tipoUsuarioInput.value = usuario.tipoUsuario;
        password.value = usuario.password; 
      });

      // Botón eliminar
      div.querySelector('.eliminar').addEventListener('click', async () => {
        if (confirm(`¿Eliminar al usuario con RUT ${usuario.rut}?`)) {
          try {
            const res = await fetch(`http://localhost:8081/api/v1/usuarios/${usuario.id}`, {
              method: 'DELETE'
            });
            const text = await res.text();
            alert(text);
            await cargarUsuarios();
          } catch (err) {
            alert("Error al eliminar usuario");
          }
        }
      });
      userList.appendChild(div);
    });
  } catch (error) {
    alert("Error al cargar usuarios");
  }
}
