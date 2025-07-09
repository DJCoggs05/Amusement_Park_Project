# Final Project Team-D

----------------------------------------------------------------------------
This is a simple application that allows you to pick a theme park, and it will display the ride names and wait times! It also will display an interactive map and if you click on a ride/restaurant in the sidebar, it will show that ride/restaurant's location on the map! Lastly, there are a lot of buttons throughout the app to allow you to see reviews of the park,ride,and restaurants.

https://github.com/user-attachments/assets/60d91d1d-e2b5-4e37-a93b-913517dab980

## $${\color{lightgreen}Build Instructions}$$
In order to run this program you will need to create a file called ApiKeys.json, and add it to the resources folder inside of the src/main/resources. Inside of the file this should be the structure: 

```json
{
  "weather": "Your OpenWeatherMap(https://openweathermap.org/current) API key"
  "google": "Your Google Places API (https://developers.google.com/maps/documentation/places/web-service) API key"
}
```
Replace the content in the quotations with your actual API key for each instance.

## $${\color{lightgreen}Parsers}$$

### &#9679; $${\color{lightblue}ApiInputStream}$$ 
This class is used so that the program can have an input stream and open it more than once for parsing if desired.  

#### &bull; loadApiKey()
This method will load the API key from the required ApiKeys.json file.

#### &bull; *createRequestUrl(Park park)*
This method takes in a Park object as a parameter. It will then extract its latitude, longitude, and encode the park name to form the search URL.

#### &bull; *openInputStream()*
When creating an instance of this object you will need to give it an input stream, and it will store the input stream as a byte array. This method will then return that byte array as an input stream. 


### &#9679; $${\color{lightblue}Coordinates}$$
This class gets and returns longitude and latitude values.

#### &bull; getLatitude()
This method returns the latitude value.

#### &bull; getLongitude()
This method returns the longitude value.


### &#9679; $${\color{lightblue}Park}$$ 
This is what will contain all of the data about the parks. Each park object will have the id, name, country, continent, latitude, longitude, and timezone.  

### &#9679; $${\color{lightblue}ParkParser}$$ 
This is what will parse the data that is given from the API.  

#### &bull; *getQuery()*
This method will return a String that contains a JSON path query.

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. It will then loop through that array and convert each item in that list to a Park object. It will then return it as a HashMap with the park's name as the key, and the value as the Park object.


### &#9679; $${\color{lightblue}ParkReviewInformation}$$ 
This is what contains all the data we have about the park review. It will contain a variable that shows how many stars the park is, and it will contain a list of Review objects.


### &#9679; $${\color{lightblue}Parser}$$ 
This is an abstract class that will be used for all of the parsers. It also uses generics so it can return whatever type of data that is needed.  

#### &bull; *parse()*
This method will call the extract data to get the data, then call the convertRevisionsToList method and return the output. 

#### &bull; *extractData(InputStream inputStreamInstance)*
This takes in an inputStream as a parameter. It will then read the data into a JSONArray and return it. It uses the query given by getQuery() to extract the data.

#### &bull; *getQuery()*
This method will return a String that contains our JSON path query. It will be overridden by the subclass. 

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. This is overridden by the subclass and uses the generic when the instance is created to specify its return type. 


### &#9679; $${\color{lightblue}PlaceIDParser}$$ 
This is what will parse the place ID.

#### &bull; *getQuery()*
This method will return a String that contains our JSON path query.

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. It will then parse the data and return the string of the place ID.


### &#9679; $${\color{lightblue}Review}$$ 
This is what contains all the data about an individual review. It will show the rating, text, author name, profile picture URL, and relative time description. 


### &#9679; $${\color{lightblue}ReviewParser}$$ 
This is what will parse the reviews of the park. 

#### &bull; *getQuery()*
This method will return a String that contains our JSON path query.

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. It will then parse the data and convert it to a ParkReviewInformation object.

### &#9679; $${\color{lightblue}ReviewRetriever}$$ 
This class combines the PlaceIDConnection\PlaceIDParser and the ReviewConnection\ReviewParser to get the Place ID and give it to the ReviewConnection. Then the ReviewParser will take what is given from the ReviewConnection, parse out the reviews, and return a ParkReviewInformation object.

#### &bull; *getReviewInformation(Park park)*
This method takes in a park object. It will then parse the data and get the reviews for the park. 


### &#9679; $${\color{lightblue}Ride}$$ 
This will hold data for rides within a park. Each ride object will contain an id, a name, whether it is open, the wait time, and when it was last updated.  

### &#9679; $${\color{lightblue}RideParser}$$ 
This is the class that will parse data from the Ride class for later use.  

#### &bull; *getQuery*
This returns a string that contains the JSON query like in the Parser class. It will be overridden as well.

#### &bull; *convertData*
This will convert the data from a Linked Hashmap and populate the items into an ArrayList.

### &#9679; $${\color{lightblue}RidePositionSearch}$$ 
This is an object that will hold the ride name we're searching for and the park object for the park that should contain the ride. 


### &#9679; $${\color{lightblue}RidePositionParser}$$ 
This is what will parse the ride's position. 

#### &bull; *getQuery()*
This method will return a String that contains our JSON path query.

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. It will then parse the data and convert it to a Coordinates object containing the latitude and longitude.


### &#9679; $${\color{lightblue}Weather}$$ 
This is what contains all the data we have about the weather. Each weather object will have the id, name, temperature, feels_like, windSpeed, humidity, and iconID.  

### &#9679; $${\color{lightblue}WeatherParser}$$ 
This is what parses the object gotten from the Weather API.  

#### &bull; *getQuery*
This method returns a JSONPath query string, that is used to select all elements within the JSON structure.

#### &bull; *convertData(JSONArray list)*
This method converts raw JSON weather data into a structured object.


----------------------------------------------------------------------------

## $${\color{lightgreen}Internet Connections}$$

### &#9679; $${\color{lightblue}InternetConnection}$$
This abstract class also uses generics to handle what the searchItem is. This will grab an input stream that will then be parsed.  

#### &bull; *search(T searchItem)*
This method will be given a search item. It will then call the createURLRequest and give it the search item to get the API endpoint we want to go to. It will then give that URL to getInputStream.

#### &bull; *getInputStream(String url)*
This method takes in a URL. It will then go to that endpoint and grab an input stream and return it.

#### &bull; *createRequestURL(T searchItem)*
This method will take in a search item that will then be used to build a URL and return it. This will be overridden by the subclass. 


### &#9679; $${\color{lightblue}ParkConnection}$$
This class handles the fetching of all the parks from the QueueTimes API.  

#### &bull; *createURLRequest(Park park)*
This method will still take in a search item, but here it grabs the URL. We won't be using the search item for this method, but it will still be overridden to use the URL desired. 


### &#9679; $${\color{lightblue}PlaceIDConnection}$$
This class handles the connection to the Google Places API to find the place ID for the location the user is searching for. 

#### &bull; loadApiKey()
This method will load the API key from the required ApiKeys.json file.

#### &bull; *createRequestUrl(Park park)*
This method takes in a Park object as a parameter. It will then extract its latitude, longitude, and encode the park name to form the search URL.


### &#9679; $${\color{lightblue}ReviewConnection}$$
This class class handles the connection to grab the actual reviews.

#### &bull; loadApiKey()
This method will load the API key from the required ApiKeys.json file.

#### &bull; *createRequestUrl(String placeID)*
This method takes in a placeID. It will then create the URL to get reviews from that placeID. 


### &#9679; $${\color{lightblue}RideConnection}$$
This class extends InternetConnection, and allows the program to handle the searchItem as well, but for rides. This overrides the createRequestURL and uses getSearchItem in this class to adjust to different data.  


### &#9679; $${\color{lightblue}RidePositionConnection}$$
This class is very similar to the PlaceID/Review Connection since it is using the same API just a different endpoint. It will handle the finding of a ride. 

#### &bull; loadApiKey()
This method will load the API key from the required ApiKeys.json file.

#### &bull; *createRequestUrl(RidePositionSearch searchItem)*
This method takes in a RidePositionSearch object that contains a Ride name, and a park object. It will then use the ride name and the park's latitude and longitude to give the search URL that will return a list of matches ranked by distance where the closest match is the first element.


### &#9679; $${\color{lightblue}WeatherConnection}$$
This class handles the fetching of all the weather information from the OpenWeatherMap(https://openweathermap.org/) API.  

#### &bull; loadApiKey()
This method will load the API key from the required ApiKeys.json file.

#### &bull; *createRequestUrl(Coordinates latitudeAndLongitude)*
This method generates the API request URL for retrieving weather data based on geographic coordinates.


----------------------------------------------------------------------------

## $${\color{lightgreen}GUI}$$

### &#9679; $${\color{lightblue}GUIModel}$$
This class is what will handle all the logic for our GUI.  

#### &bull; *fetchParks()*
This method will fetch the parks using the ParkConnection and ParkParser class. 

#### &bull; *getWaitTimeColor(int waitTime)*
This takes in an integer that will be our wait time. It will then return the CSS style color that we want.

#### &bull; *convertMinToHours(int minutes)*
This method converts minutes to hours and returns the result.

#### &bull; *fetchRides(int id)*
This takes in an id as the parameter. It will use the RideConnection and RideParser class to get a list of rides. 

#### &bull; *getWeather(String latitude, String longitude)*
This method takes a string of longitude and latitude. It then uses the WeatherConnection and WeatherParser class to return a Weather object containing all the weather data.


### &#9679; $${\color{lightblue}GUI}$$  

#### &bull; *start()*
This method creates and displays the GUI.


### &#9679; $${\color{lightblue}MapManager}$$ 

#### &bull; *createMap()*
This method creates the Map.

#### &bull; *addMarker()*
This method creates and places a marker on the Map.


### &#9679; $${\color{lightblue}SharedState}$$ 

#### &bull; *setAtomicBooleanValue()*
THis method sets the atomic boolean value.

----------------------------------------------------------------------------

## $${\color{lightgreen}Error Handling}$$

### &#9679; $${\color{lightblue}networkErrorException}$$
This is a custom exception. This exception is thrown when a network error occurs. This is handled in the GUI.  


### &#9679; $${\color{lightblue}noItemFoundException}$$
This is a custom exception that happens when the item cannot be found. It is handled inside of the GUI.  


### &#9679; $${\color{lightblue}openInputStreamException}$$
This is a custom exception that happens when we cannot open the input stream. This is also handled inside of the GUI.  

----------------------------------------------------------------------------

## $${\color{lightgreen}APIs/Technologies}$$

* Java
* JavaFX
* HTML
* Javascript
* Cascading Style Sheets (CSS)
* Gradle
* IntelliJ
* [Leaflet](https://leafletjs.com)
* [OpenWeatherMap](https://openweathermap.org/current)
* [QueueTimes](https://queue-times.com/en-US/pages/api)
* [Google Places](https://developers.google.com/maps/documentation/places/web-service)

## $${\color{lightgreen}Authors}$$
* Nolan Meyer
* Dakota Coughlin
* Sinclair Nzenwata
* Chibiuike Anyiam


