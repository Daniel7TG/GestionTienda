# Sistema Punto de Venta (POS) - Java Swing

Este proyecto fue desarrollado para la materia de **Tópicos Avanzados de Programación**. Es una aplicación de escritorio completa que gestiona el ciclo de ventas y compras de un negocio, aplicando técnicas modernas de desarrollo en Java.

### Tecnologías y Conceptos Implementados

Para este proyecto, se hizo énfasis en el uso de herramientas avanzadas para lograr un código limpio y eficiente:

* **Java Swing:** Creación de una interfaz gráfica de usuario (GUI) amigable.
* **MySQL Connector:** Integración manual de la librería para la gestión de datos.
* **Consultas SQL Manuales:** Las queries se redactaron directamente en el código para tener control total sobre la persistencia.
* **Lambdas y Programación Funcional:** Implementadas para el manejo de eventos y procesamiento de listas.
* **Generics (Genéricos):** Uso de tipos genéricos para la creación de componentes y métodos reutilizables en diferentes módulos.

---

### Funcionalidades Principales

El sistema permite una gestión integral de los siguientes módulos:

* **Sistema de Seguridad:** Login de acceso con validación de credenciales.
* **Gestión de Usuarios:** CRUD completo con opción de **cambio de permisos** (roles) en tiempo real.
* **Inventario:** CRUD de Productos con control de stock.
* **Proveedores:** Registro y administración de proveedores.
* **Ventas:** Registro de ventas y listado histórico.
* **Compras:** Registro de entradas de mercancía y listado histórico.

---

### Capturas de Pantalla

A continuación se muestran los diferentes módulos del sistema:

### Gestión de Productos
<img width="1920" height="1080" alt="Screenshot (137)" src="https://github.com/user-attachments/assets/db8d160c-1672-406b-ad48-038913b1545f" />

### Registro de Ventas
<img width="1920" height="1080" alt="Screenshot (152)" src="https://github.com/user-attachments/assets/474d6e09-6c25-4d16-8707-640f8e15716b" />

### Registro de Compras
<img width="1920" height="1080" alt="Screenshot (151)" src="https://github.com/user-attachments/assets/59359e79-3515-4b73-a21f-3cca50f494ac" />

### Administración de Proveedores
<img width="1920" height="1080" alt="Screenshot (149)" src="https://github.com/user-attachments/assets/0a5c856c-378e-46e7-9ed4-fa7d82ca1d69" />

### Usuarios y Permisos
<img width="1920" height="1080" alt="Screenshot (150)" src="https://github.com/user-attachments/assets/cc90c40a-99e9-4651-a7f4-4d3776965547" />

---

### Instalación y Ejecución

Sigue estos pasos para poner en marcha el proyecto:

### 1. Preparar la Base de Datos
El script con la estructura de las tablas y datos iniciales se encuentra en:
`sql/sql.sql`

### 2. Importar Librería
Asegúrate de agregar el `mysql-connector-java.jar` a las bibliotecas de tu proyecto (Build Path).

### 3. Ejecutar la Aplicación
El punto de entrada del programa se encuentra en el archivo:
`Launcher.java`
