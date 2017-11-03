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

## Main Instructions

In this project we have 2 main folders.
Please first run the registry using ./registry/mvnw (on Windows just dir into registry and run on CMD mvnw) on a separate CMD window
Then run the gateway-app using ./gateway-app/mvnw (on Windows just dir into gateway-app and run on CMD mvnw)on another separated window

## Required tools
    JDK 8.0 (or higher)
    Maven 3.5.0 (or higher)
    Eclipse Oxygen
    
## API's Documentation

The API's documentation can be found on http://localhost:8080/api and was made using Swagger when the server (gateway-app) is up.

## Endpoints (please use with Authorization Header)

    curl -X GET \
    http://localhost:8080/api/tracks/location?lat=-22&lon=-47 \
    -H 'accept: application/json' \
    -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUxMTA1OTU2OX0.6MpynHS5qjxxcvTccJ1obekAuYHLx7z3gNFn-dmkXbtqEYZiKmTj-CMwZyF0w1evOOkcNzrN9l_b_rsfR4VIIw'

    curl -X GET \
    http://localhost:8080/api/suggestions/city/Campinas \
    -H 'accept: application/json' \
    -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUxMTA1OTU2OX0.6MpynHS5qjxxcvTccJ1obekAuYHLx7z3gNFn-dmkXbtqEYZiKmTj-CMwZyF0w1evOOkcNzrN9l_b_rsfR4VIIw'

    curl -X GET \
    http://localhost:8080/api/suggestions/city/Dois+Lajeados \
    -H 'accept: application/json' \
    -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUxMTA1OTU2OX0.6MpynHS5qjxxcvTccJ1obekAuYHLx7z3gNFn-dmkXbtqEYZiKmTj-CMwZyF0w1evOOkcNzrN9l_b_rsfR4VIIw'

