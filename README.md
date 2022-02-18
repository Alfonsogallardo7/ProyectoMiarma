# ProyectoMiarma

La aplicación MiarmaApp es una red social Sevillana, con la que podremos subir fotos y videos e interactuar con ellos.

## Autor

- Alfonso Gallardo Rodríguez

### Entidades

- Publicación
- Usuario

## Arrancar el proyecto

Para arrancar nuestro proyecto deberemos de seguir los siguientes pasos.

### - Descargar e instalar IntelliJ IDEA Community Edition 2021.2.2

### - Descargar JAVA JDK 17

### - Abrimos el IntelliJ IDEA

### - Configuramos la version de JDK

    1. Clickamos en File
    2. A continuación en Proyect Estructure
    3. Por último seleccionamos en Proyect SDK la versión 17 (la cual hemos descargado previamente)

### - Colección de Postman

    1. Descargamos y descomprimimos la colección de postman
    2. La importamos al nuestro
### - Arrancamos el Proyecto 

    1. Hacemos click en el botón de Maven,la cual encontraremos en la esquina superior derecha de la ventana
    2. A continuación clickamos en Pluggins.
    3. Después en Spring Boot
    4. Y por último en Spring Boot Run

## - ¿Qué podemos hacer en MiarmaApp?

- Login y Registro
    - Podremos registrarnos en la aplicación rellenando algunos campos.
    - Podremos loguearnos si ya tenemos una cuenta registrada.
    - Además también podremos ver nuestros datos

- Funcionalidades de Publicación
    - Crear un nuevo post (se creará al usuario, con el que estemos logueado)
    - Obtener todos los post públicos.
    - Obtener los datos de un post por id, solo lo podremos ver si esta marcada como pública
    - Modificar un post (eliminando la foto anterior, y añadiendo una nueva)
    - Eliminar un post (eliminando a su misma vez la foto o video adjunto)
    - Obtener todos los post de un usuario, buscandolo por su username, solo lo podremos ver si esta marcada como pública
    - Obtendremos todas las publicaciones del usuario logueado (independientemente de que sean publicas o privadas)
