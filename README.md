
# SpringBoot WebFlux Examples
Simple Blog API to play with SpringBoot, WebFlux and R2DBC

### WebFlux example

`Flux` and `Mono` in `@RestController`, enable SSE using HTTP Header
`Accept: text/event-stream` to receive non-blocking stream of data, or 
`Accept: application/json` for a _request/response_ traditional behavior.

### R2DBC example 

New `spring-data-r2dbc:1.1.0.RELEASE` configuration in SpringBoot, 
there was no starter available for this version at the time of 
creating the example.
The example covers database schema creation and population, check
`R2dbcConfig` class for details.

Example of Spring Data Repositories support for Query Derivation. 
Now is possible to define methods like `findByFirstnameContaining()` 
without specifying the corresponding `SQL` query.

### Next examples
* Complete test coverage
* WebFlux JWT Security