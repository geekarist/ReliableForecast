# ReliableForecast

Reliable weather forecast application for Android

## Build

Get yourself an OpenWeatherMap API key: http://openweathermap.org/appid

Create a `private` directory: `mkdir private`

Create a `secret.properties` file and put the key in it:

    openweather_appid=48a57224b7074025742859408520854f1
    
Now the build should be OK: `gradlew assemble`