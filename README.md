# movie-ratings-spring-boot-microservices
This is movie-ratings micro services project  based on spring-boot

movie-catalog-service: This is the main microservices which calls movie-info-service and movie-ratings service to build a catalog with user ratings for a movie

movie-info-service: This microservice deals the movie info like movie name, title, summary.

movie-ratings-service: This microservice deals with user ratings for movies rated by the user

Version
- v1.0: This is build on spring Greenwich Release and uses Eureka discovery server to lookup services.