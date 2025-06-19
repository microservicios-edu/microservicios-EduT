const form = document.getElementById('userForm');
const nombreInput = document.getElementById('nombre');
const rutInput = document.getElementById('rut');
const userList = document.getElementById('userList');

let editingRut = null; // Rut que se está editando

// Cargar usuarios al iniciar
document.addEventListener('DOMContentLoaded', async () => {
  await cargarUsuarios();
});

form.addEventListener('submit', async function (e) {
  e.preventDefault();

  const nombre = nombreInput.value;
  const rut = rutInput.value;

  try {
    const endpoint = editingRut ? '/api/actualizar_usuario' : '/api/crear_usuario';
    const response = await fetch(`${endpoint}?nombre=${nombre}&rut=${rut}`);
    const data = await response.json();
    alert(data.message || (editingRut ? "Usuario actualizado" : "Usuario creado"));

    editingRut = null;
    form.reset();
    await cargarUsuarios();
  } catch (error) {
    alert("Error al procesar la solicitud");
  }
});

async function cargarUsuarios() {
  try {
    const response = await fetch('/api/usuarios');
    const usuarios = await response.json();

    userList.innerHTML = '';

    usuarios.forEach(usuario => {
      const div = document.createElement('div');
      div.classList.add('usuario');

      div.innerHTML = `
        <strong>${usuario.nombre}</strong> - ${usuario.rut}
        <button class="editar" data-rut="${usuario.rut}" data-nombre="${usuario.nombre}">Editar</button>
        <button class="eliminar" data-rut="${usuario.rut}">Eliminar</button>
      `;

      // Botón editar
      div.querySelector('.editar').addEventListener('click', () => {
        nombreInput.value = usuario.nombre;
        rutInput.value = usuario.rut;
        editingRut = usuario.rut;
      });

      // Botón eliminar
      div.querySelector('.eliminar').addEventListener('click', async () => {
        if (confirm(`¿Eliminar al usuario con RUT ${usuario.rut}?`)) {
          try {
            const res = await fetch(`/api/eliminar_usuario?rut=${usuario.rut}`);
            const data = await res.json();
            alert(data.message || "Usuario eliminado");
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
