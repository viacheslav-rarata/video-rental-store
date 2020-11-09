# Video rental store
Administration system for managing a video rental store

## Decisions
- java 8, maven, spring boot, postgresSql.
- monolith app with RestFull API. Using microservices will be redundant.

## Build and Run
- install maven
- install postgres and create database with name 'video-rental-store'.
- execute from terminal
```sh
mvn clean package
mvn spring-boot:run -Dspring-boot.run.profiles=local-dev
```

## Endpoints
Customer:  
- GET    /customers/ - list of customers
- GET    /customers/{id}  - customer details
- POST   /customers/ - create new customer

Films:  
- GET     /films/ - list of films
- GET     /films/{id} - film details

Rental:
- GET     /rental/  - list of rental
- GET     /rental/{id} - rental details
- POST    /rental/ - create new rental
- POST    /rental/{id} - update rental

## Conclusion:
- Price calculation is made in two steps - when film rented and when film returned
- Bonus points are calculated when the film is rented
