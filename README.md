# URL Shortener

The URL Shortener service is a Springboot web service that shorts any given URL. The service
hash the URL, keeps the 10 first characters and concatenate them with the original URL's domain.
The created URLs are persisted in a Mongodb collection.

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
To use the application you'll need a Mongodb instance, if you don't have one running
on your computer you can launch one with the following command (Docker needed):
```shell
$ docker-compose -f docker/stack-dev.yml up
```
This will start a containerized Mongodb instance, this instance is meant to be used for the development.
No volume is mount so data won't persist after the container is stopped.

- To start the URL Shortener service
```shell
$ ./mvnw spring-boot:run
```
- To stop the Mongodb container
```shell
$ ./mvnw docker-compose -f stack-dev.yml down
```



## Run the application in a "production" like environment

**!! Docker required !!**

- First launch

```shell
$ make build_and_start
```
This will create a buildpack of the springboot application, then launch a docker-compose
stack composed by the URL Shortener service and a Mongodb instance.

 - To start the application without rebuilding the service image:
```shell
$ make start_app
```

- To stop the app
```shell
$ make stop_app
```