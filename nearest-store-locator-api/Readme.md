# Nearest Store Locator API
Gives you the facility to add a Store with address. Stores the longitude and latitude of your Store using Google [Geocoding API](https://developers.google.com/maps/documentation/geocoding/intro). You can request for a Store mentioning your current latitude and longitude. The API would return you the Store nearest to you.

To add a resource
```
Resource			: /store
Description			: Adds a store. The API would find out the latitude and longitude of the 
					  store address using Google Maps API and store it with the address.
Method				: POST
Request Content-type: application/json
Example Request		:	{
						  "name": "Amphitheatre Parkway",
						  "address": {
						    "number": "1600",
						    "line1" : "Mountain View",
						    "line2" : "CA",
						    "postcode": "94043"
						  }
						}
Success Response	: 200 OK
```

To get a resource
```
Resource				: /store/{latitude}/{longitude}
Description				: Gives you the nearest store from your latitude and longitude.
Method					: GET
Response Content-type	: application/json;charset=UTF-8
Example Response		: 200 OK	
							{
							  "name": "Amphitheatre Parkway",
							  "address": {
							    "number": "1600",
							    "line1" : "Mountain View",
							    "line2" : "CA",
							    "postcode": "94043"
							  },
							  "latitude": 37.422364,
							  "longitude": -122.084364
							}
```
## How to run

**Maven :**
If using maven you can use below command to run it

`mvn spring-boot:run -Dserver.port=8082`


**As jar :**
You can directly run it as a jar using below command. Change the path to the jar accordingly

`java -jar -Dserver.port=8082 shops-near-you.jar`

If you do not change the port using `-Dserver.port=8082` by default the embedded Tomcat runs on port `8080`

## If you are behind a proxy server
If you are running it behind a proxy server, set the proxy setting in `application.properties`. e.g.
```
config.proxy=true
config.proxyaddress=<your proxy host address>
config.proxyport=<your proxy port>
config.proxyuser=<user name>
config.proxypassword=<proxy password>
```

Else update the file as `config.proxy=false`.