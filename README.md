# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por �ltimo el inicio y configuraci�n de la aplicaci�n.

Lee el art�culo [Clean Architecture � Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el m�dulo m�s interno de la arquitectura, pertenece a la capa del dominio y encapsula la l�gica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este m�dulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define l�gica de aplicaci�n y reacciona a las invocaciones desde el m�dulo de entry points, orquestando los flujos hacia el m�dulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no est�n arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
gen�ricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patr�n de dise�o [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicaci�n o el inicio de los flujos de negocio.

## Application

Este m�dulo es el m�s externo de la arquitectura, es el encargado de ensamblar los distintos m�dulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma autom�tica, inyectando en �stos instancias concretas de las dependencias declaradas. Adem�s inicia la aplicaci�n (es el �nico m�dulo del proyecto donde encontraremos la funci�n �public static void main(String[] args)�.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

<h1>
  <b>API Rest Foro</b>
</h1>

<h2>
    Desafio planteado por <a href="https://www.aluracursos.com/">Alura latam</a> y <a href="https://www.oracle.com/ar/">Oracle</a> para el progama ONE
</h2>


<h2>
  Informacion sobre el desafio planteado
</h2>

<p>Un foro es un espacio donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados tópicos. 
  Aquí en Alura Latam, los estudiantes utilizan el foro para sacar sus dudas sobre los cursos y proyectos en los que participan. 
  Este lugar mágico está lleno de mucho aprendizaje y colaboración entre estudiantes, profesores y moderadores.
</p>
<p>
  Ya sabemos para qué sirve el foro y conocemos su aspecto, ¿pero sabemos cómo funciona detrás de escena? Es decir, ¿dónde se almacenan las informaciones? 
¿Cómo se tratan los datos para relacionar un tópico con una respuesta, o cómo se relacionan los usuarios con las respuestas de un tópico?

Este es nuestro desafío, llamado ForoHub: en él, vamos a replicar este proceso a nivel de back end y, para eso, crearemos una API REST usando Spring.

</p>

<p>
  Nuestra API se centrará específicamente en los tópicos, y debe permitir a los usuarios:
</p>
<ul>
  <li>
    Crear un nuevo tópico;
  </li>
  <li>
    Mostrar todos los tópicos creados;
  </li>
  <li>
    Mostrar un tópico específico;
  </li>
  <li>
    Mostrar un tópico específico;
  </li>
  <li>
    Actualizar un tópico;
  </li>
  <li>
    Eliminar un tópico.
  </li>
</ul>

<p>
  Es lo que normalmente conocemos como CRUD* (CREATE, READ, UPDATE, DELETE) y es muy similar a lo que desarrollamos en LiterAlura, pero ahora de forma completa, 
agregando las operaciones de UPDATE y DELETE, y usando un framework que facilitará mucho nuestro trabajo.
</p>

<p>
  *Traducción libre (en orden): Crear, Consultar, Actualizar y Eliminar.
</p>

<p>
  En resumen, nuestro objetivo con este challenge es implementar una API REST con las siguientes funcionalidades:
</p>

<ol>
  <li>
    API con rutas implementadas siguiendo las mejores prácticas del modelo REST;
  </li>
  <li>
    Validaciones realizadas según las reglas de negocio;
  </li>
  <li>
    Implementación de una base de datos relacional para la persistencia de la información;
  </li>
  <li>
    Servicio de autenticación/autorización para restringir el acceso a la información.
  </li>
</ol>

<h2>
  Configuraciones del entorno
</h2>

<ul>
  <li>
    Java (versión 17) 
  </li>
  <li>
    Gradle 8.8
  </li>
  <li>
    Spring Boot (versión 3.3.1)
    Dependencias:
      <ul>
        <li>
          Lombok  
        </li>
        <li>
          Spring Web
        </li>
        <li>
          Spring Boot DevTools
        </li>
        <li>
          Spring Data JPA
        </li>
        <li>
          Flyway Migration
        </li>
        <li>
          MySQL Driver
        </li>
        <li>
          Validation
        </li>
        <li>
          Spring Security
        </li>
      </ul>
  </li>
</ul>

<h2>
    Documentacion
</h2>

<p>
    Se implemento Swagger para documentar la aplicación, con el objetivo de generar
    interfaz amigable y accesible.
</p>

<h2>
    URI SWAGGER
</h2>

```
http://localhost:8080/swagger-ui/index.html
```
<h2>
    Como usar?
</h2>

Para poder hacer uso de la API es necesario cargar un usuario en bd, por razones de tiempo no se a generado un end point para realizar tal cosa, por lo tanto debe realizarse de forma la inserción en la bd "INSERT INTO usuarios (nombre, email, contrasenia)
VALUES ('su nombre', 'su email' , 'contraseña');". Cabe destacar que la contraseña debe de estar cifrada con BCrypt a la hora de guardarla en la db.

```
https://www.browserling.com/tools/bcrypt
```

<p>
    Para poder hacer uso de los endpoint, toca descargar
    las collections, que se disponen para ello.
</p>

<p>
Podemos encontrarla con el siguiente nombre:

Insomnia_2024-07-08.json
</p>



<h2>
    Estado del proyecto
</h2>

<ul>
    <li>
        El desafio planteado por <a href="https://www.aluracursos.com/">Alura latam</a> y <a href="https://www.oracle.com/ar/">Oracle</a> esta completo.
    </li>
    <li>
        Ademas se agregaron end point para: crear, leer, modificar y borrar, tanto cursos, como respuestas.
A modo de desafio personal.
    </li>
</ul>