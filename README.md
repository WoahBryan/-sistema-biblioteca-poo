# Sistema de Biblioteca - Autenticación y Autorización (Actividad 3.10)

Este repositorio contiene la actualización de mi Sistema de Gestión de Biblioteca en JavaFX. En esta fase del proyecto, se implementó un sistema de seguridad para restringir el acceso y controlar qué puede hacer cada persona dentro de la aplicación.

## ¿Qué se hizo en esta actividad?

Se agregó un sistema de **Inicio de Sesión (Login)** conectado a una base de datos MySQL, validando las credenciales y el estado de la cuenta. Además, se configuró un control de acceso basado en roles (Perfiles de Usuario), bloqueando o habilitando funciones de la interfaz gráfica dependiendo de quién inicie sesión.

## Perfiles y Permisos Implementados

1. **Administrador:** Tiene acceso total al sistema. Puede Crear, Leer, Actualizar y Eliminar (CRUD completo) en todas las tablas.
2. **Bibliotecario:** Perfil operativo. Puede agregar y modificar registros (libros, revistas, etc.), pero tiene **bloqueado el botón de Eliminar** por seguridad y no puede ver los datos del personal.
3. **Consulta (Estudiante/Público):** Perfil de solo lectura. Únicamente puede ver los catálogos de libros y revistas; tiene bloqueados los botones de guardar, editar y eliminar, así como las cajas de texto.

*Nota: El sistema también detecta si una cuenta está marcada como "Inactiva" en la base de datos y le bloquea el acceso automáticamente mostrando un mensaje de error.*

## Tecnologías y Patrones de Diseño utilizados
* **Lenguaje:** Java
* **Interfaz Gráfica:** JavaFX (Scene Builder)
* **Base de Datos:** MySQL
* **Arquitectura:** Modelo-Vista-Controlador (MVC) y Patrón DAO para la conexión y consultas a la base de datos.
