# iFood Backend Advanced Test by @cesarnog

Micro-service that accepts RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Non functional requirements

As this service will be a worldwide success, it is prepared to be fault tolerant,responsive and resilient.

Solution, architecture details, choice of patterns and frameworks are detailed on each README.


# Main Instructions

In this project we have 2 folders.
Please first run the gateway-app with the instructions located on inner gateway-app/README.MD
Second run the microservice with the instructions located on inner microservice/README.MD


# About this Project

Three mains top layers compose this project:
    Controller: API gateway - gets incoming requests
    Service: Business layer - handles business rules
    Network: Communication layer, calls external APIs for the suggestion engine

# Required tools
    JDK 8.0 (or higher)
    Maven 3.5.0 (or higher)
    Eclipse Oxygen (Optional)
    
# API's Documentation

The API's documentation can be found on http://localhost:8080/doc and was made using Swagger when the server (gateway-app) is up.

# Endpoints
    http://localhost:8080/sugestions?city=Campinas
    http://localhost:8080/sugestions?lat=-15.9204&lon=-44.12
    
