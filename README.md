# URL Shortener

The URL Shortener service is a Springboot web service that shorts any given URL. The service
hash the url, keeps the 10 first characters and concatenate them with the original URL's domain.
The created URLs are persisted in a Mongodb collection.

The service exposes two endpoints:
 - POST /url-shortener/shortens' to short an url
 - GET /url-shortener/search?url=<shortenedUrl> to retrieve a full url given its shortened url

Contract and Postman collection can be found in the respective files
openapi.yml & url_shortener_collection.json.

## Development

- Clone the repository on your computer
```shell
$ git clone https://github.com/simonpic/url-shortener
```

To use the application you'll need a Mongodb instance, if you don't have one running
on your computer you can launch one with the following command (Docker needed):
```shell
$ docker-compose -f docker/stack-dev.yml up
```
This will start a containerized Mongodb instance, this instance is meant to be used for the development.
No volume is mount so data won't persist after the container is stopped.

- To start the Url Shortener service
```shell
$ ./mvnw spring-boot:run
```

## Run the application in a "production" like environment

**!! Docker required !!**

- First launch:

```shell
$ make build_and_start
```
This will create a buildpack of the springboot application and then launch a docker-compose
stack composed by the Url Shortener service and a Mongodb instance.

 - To start the application without rebuilding the service image:
```shell
$ make start_app
```

- To stop the app
```shell
$ make stop_app
```