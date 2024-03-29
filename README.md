# URL Shortener

The URL Shortener service is a Springboot web service that shorts any given URL.
The created URLs are persisted in a MongoDB collection.

The service exposes two endpoints:
 - POST /url-shortener/shortens' to short an URL
 - GET /url-shortener/search?url=\<shortenedUrl> to retrieve a full URL given its shortened url

Contract and Postman collection can be found in the respective files
***openapi.yml*** & ***url_shortener_collection.json***.

## Development

- Clone the repository on your computer
```shell
$ git clone https://github.com/simonpic/url-shortener
```
and open it with your preferred IDE.
### Testing

- To run unit tests
```shell
$ ./mvnw test
```
- To run integration tests
```shell
$ ./mvnw verify
```
Integration tests require Docker as they use testcontainers

### Run the service
To use the application you'll need a MongoDB instance, if you don't have one running
on your computer you can launch one with the following command (Docker needed):
```shell
$ docker-compose -f docker/stack-dev.yml up
```
This will start a containerized MongoDB instance, this instance is meant to be used for the development.
No volume is mount so data won't persist after the container is stopped.

- To start the URL Shortener service
```shell
$ ./mvnw spring-boot:run
```
- To stop the MongoDB container
```shell
$ ./mvnw docker-compose -f stack-dev.yml down
```



## Run the application in a "production" like environment

**!! Docker required !!**


### Create the buildpack and launch the application

```shell
$ make build_and_start
```
Or
```shell
$ ./mvnw spring-boot:build-image
$ docker-compose -f docker/stack.yml up
```

This will create a buildpack of the springboot application, then launch a docker-compose
stack composed by the URL Shortener service and a MongoDB instance.

### Start the application without rebuilding the service image

```shell
$ make start_app
```
Or
```shell
$ docker-compose -f docker/stack.yml up
```

### Stop the application

```shell
$ make stop_app
```
Or
```shell
$ docker-compose -f docker/stack.yml down
```