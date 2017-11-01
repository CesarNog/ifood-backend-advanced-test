# iFood Backend Advanced Test

Micro-service that accepts RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success, it is prepared to be fault tolerant,responsive and resilient.

Solution, architecture details, choice of patterns and frameworks are detailed on each README.


# Instructions

In this project we have 2 folders.

Please first run the gateway-app with the instructions located on inner gateway-app/README.MD

Second run the microservice with the instructions located on inner microservice/README.MD
