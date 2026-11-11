# Sistema de Gestión de Biblioteca - JavaFX

**Asignatura:** Programación Orientada a Objetos (POO)  
**Institución:** Universidad Tecnológica de Jalisco (UTJ)  
**Desarrollador:** Contreras Martínez Bryan Daniel  
**Especialidad:** Desarrollo de Software Multiplataforma (DSM)  

---

## Descripción del Proyecto

El **Sistema de Gestión de Biblioteca** es una aplicación de escritorio desarrollada en **Java** utilizando el framework **JavaFX** y gestionada mediante **Apache Maven**. El objetivo principal de la aplicación es proporcionar una interfaz gráfica de usuario (GUI) intuitiva y funcional para la administración integral de un catálogo bibliotecario, gestionando módulos de usuarios, bibliotecarios, autores, materiales bibliográficos, préstamos y sanciones.

---

## Arquitectura y Patrón de Diseño

El proyecto está estructurado bajo el patrón arquitectónico **MVC (Modelo-Vista-Controlador)**:

- **Modelo (`edu.utj.dsm.poo.biblioteca.modelo`):** Clases entidad (`Usuario`, `Bibliotecario`, `Autor`, `Libro`, `Multa`, etc.) que encapsulan la lógica de negocio y los datos.
- **Vista (`src/main/resources/fxml`):** Archivos FXML diseñados con **Scene Builder** que definen la interfaz gráfica y distribución espacial de los componentes.
- **Controlador (`edu.utj.dsm.poo.biblioteca.controlador`):** Clases encargadas de vincular la lógica con las vistas, capturar eventos de usuario y gestionar las listas observables.

---

## Tecnologías y Herramientas Utilizadas

- **Lenguaje:** Java 17 (JDK 17 LTS)
- **Framework GUI:** JavaFX 13 / 17 (JavaFX Controls, JavaFX FXML)
- **Gestor de Dependencias:** Apache Maven
- **Entorno de Desarrollo (IDE):** NetBeans IDE 25
- **Diseño de Interfaces:** Scene Builder
- **Persistencia en Memoria:** `ObservableList` / `FXCollections`

---

## Estructura del Proyecto

Biblioteca/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── edu/utj/dsm/poo/biblioteca/
│   │   │   │   ├── controlador/        # Controladores FXML de las vistas
│   │   │   │   ├── modelo/             # Clases POJO / Entidades del sistema
│   │   │   │   ├── App.java            # Clase principal de arranque JavaFX
│   │   │   │   └── Main.java           # Launcher para resolver runtime
│   │   │   └── module-info.java        # Configuración de módulos JPMS
│   │   └── resources/
│   │       └── fxml/                   # Archivos de vista FXML
├── pom.xml                             # Archivo de configuración Maven
└── README.md                           # Documentación del proyecto

---

## ⚙️ Funcionalidades Implementadas

1. **Gestión de Módulos (CRUD Completo):**
   - **Módulo de Usuarios:** Registro de datos personales, número de credencial, estado operativo y gestión de fotografía mediante `FileChooser`.
   - **Módulo de Bibliotecarios:** Registro de personal administrativo de la biblioteca.
   - **Módulo de Autores:** Catálogo de autores de obras bibliográficas.
   - **Módulos Complementarios:** Interfaces estructuradas para Libros, Editoriales, Préstamos y Multas.

2. **Carga e Integración de Imágenes:**
   - Selección dinámica de imágenes desde el sistema de archivos local (`FileChooser`) con vista previa inmediata en componentes `ImageView`.

3. **Interactividad y Eventos:**
   - Visualización de registros en tiempo real mediante `TableView` y vinculación de columnas con `PropertyValueFactory`.
   - Mensajes emergentes de validación y confirmación mediante ventanas de alerta (`Alert`).
   - Selección e inspección de datos en tabla con autollenado de formulario mediante listeners de selección.

---

## Instrucciones de Ejecución

### Prerrequisitos
- JDK 17 o superior instalado y configurado en las variables de entorno.
- Apache Maven instalado (o integrado en el IDE).

### Compilación y Ejecución en NetBeans
1. Clonar o abrir el proyecto en Apache NetBeans.
2. Hacer clic derecho sobre el nodo raíz del proyecto `Biblioteca` y seleccionar **Clean and Build** (`Shift + F11`).
3. Ejecutar el proyecto presionando el botón **Run Project** (botón verde de Play / `F6`) o ejecutando la meta Maven: `mvn clean javafx:run`

---

## ✒️ Autor

* **Bryan Daniel Contreras Martínez** - *Desarrollo e Implementación* - UTJ
