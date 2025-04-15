# Microservicios EDU 📚

Este repositorio contiene los microservicios del sistema educativo desarrollado en Java con Spring Boot.

## Microservicios incluidos

- Gestión de Usuarios
- Recursos Educativos
- Inscripción y Matrícula
- Evaluaciones y Reportes
- Pagos y Notificaciones
- Soporte

Cada microservicio está en su carpeta correspondiente y tiene su propio `README.md` con instrucciones específicas.

## ¿Cómo comenzar?

1. Abre este proyecto en Visual Studio Code.
2. Ingresa a la carpeta del microservicio con el que quieras trabajar.
3. Genera tu proyecto en [https://start.spring.io](https://start.spring.io) y descomprime el contenido dentro de la carpeta correspondiente.

# microservicios-EduT

## 🚀 Subir el proyecto a GitHub

```bash
echo "# microservicios-EduT" >> README.md
git init
git add .
git commit -m "first commit"
git branch -M main  # O master, según cómo se llame tu rama principal
git remote add origin https://github.com/microservicios-edu/microservicios-EduT.git
git push -u origin main  # O master
```

---

## 🛠️ Guía rápida para trabajar con microservicios

1. **Clona el repositorio principal**  
   ```bash
   git clone https://github.com/microservicios-edu/microservicios-EduT.git
   cd microservicios-EduT
   ```

2. **Entra a la carpeta del microservicio que vas a editar**  
   Ejemplo:
   ```bash
   cd pagos-notificaciones
   ```

3. **Sube tus cambios**  
   ```bash
   git add .
   git commit -m "feat: cambios en pagos-notificaciones"
   git push origin main  # o master, según corresponda
   ```