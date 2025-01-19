# Gestión de Productos

Este proyecto es una aplicación web de **Gestión de Productos** construida con **Spring MVC** y **Thymeleaf**, que permite gestionar un catálogo de productos. Los usuarios pueden ver, crear, editar y eliminar productos a través de una interfaz web.

---

## Descripción

El sistema permite realizar las siguientes acciones:

### 1. **Listar productos**
Muestra todos los productos almacenados en la base de datos en una tabla con información relevante como el nombre, descripción, precio, y cantidad disponible.

### 2. **Crear un nuevo producto**
Permite agregar un producto al sistema proporcionando su nombre, descripción, precio y cantidad.

### 3. **Ver detalles de un producto**
Al hacer clic en un producto de la lista, se puede ver más información detallada sobre él, como su nombre, descripción, precio y cantidad.

### 4. **Editar un producto**
Los usuarios pueden modificar cualquier atributo de un producto existente y guardarlo en la base de datos.

### 5. **Eliminar un producto**
Permite eliminar un producto después de confirmar la acción con un cuadro de confirmación.

---

## Tecnologías utilizadas

- **Spring MVC**: Framework de Java para la creación de aplicaciones web basadas en el patrón Modelo-Vista-Controlador (MVC).
- **Thymeleaf**: Motor de plantillas para generar HTML de forma dinámica.
- **Bootstrap**: Framework CSS para diseñar una interfaz de usuario moderna y adaptable.
- **Java**: Lenguaje de programación utilizado para el backend.
- **Spring Data JPA**: Para interactuar con la base de datos utilizando JPA (Java Persistence API).
- **H2 Database**: Base de datos en memoria utilizada para almacenar los productos de forma temporal.

---

## Requisitos

Para ejecutar este proyecto, necesitarás tener instalado lo siguiente:

- **JDK 11 o superior**
- **Maven** (para gestionar las dependencias y la construcción del proyecto)
- **IDE recomendado**: IntelliJ IDEA, Eclipse, etc. (aunque puedes usar cualquier editor de texto y terminal)

---

## Instrucciones de instalación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/gestion-de-productos.git
