ALKEMY CHALLENGE BACKEND - Java Spring Boot (API)

-------------------------------

Objetivo:

Desarrollar una API REST que permita navegar por los personajes y películas de Disney. Se expondrá la información para que cualquier frontend pueda consumirla.

Tecnologías utilizadas:

-Java

-SpringBoot

-Spring Security

-------------------------------

Endpoints:

-GET /movies (Desplegar todas las peliculas con sus datos)

-GET /movies?name=nombre (Filtrar películas por título)

-GET /movies?genre=idGenero (Filtrar películas por género)

-GET /movies?order=ASC | DESC (Filtrar películas por orden ascendente o descendente)

-POST /movies/create (Crear una película nueva)

-POST /movies/update (Actualizar datos de una película ya existente)

-------------------------------

-GET /characters (Desplegar todos los personajes con sus datos)

-GET /characters?name=nombre (Filtrar personajes por su nombre)

-GET /characters?age=edad (Filtrar personajes por su edad)

-GET /characters?movies=idMovie (Filtrar personajes por película)

-POST /characters/create (Crear un personaje nuevo)

-POST /characters/update (Actualizar datos de un personaje ya existente)

-------------------------------

-POST /movies/{idMovie}/characters/{idCharacter} (Agregar un personaje a una película)

-DELETE /movies/{idMovie}/characters/{idCharacter} (Eliminar personaje de una película)

-------------------------------

-GET /movies/genre (Desplegar todos los géneros con sus datos)

-POST /movies/genre/create(Crear un género nuevo)

-POST /movies/{idMovie}/genre/{idGenre} (Agregar una película a un género)

-DELETE /movies/{idMovie}/genre/{idGenre} (Quitar una película de un género)
