
```shell
$ docker-compose -f docker/stack-dev.yml up
$ ./mvnw spring-boot:run
```

Create build pack and start application stack:
```shell
$ ./mvnw spring-boot:build-image
$ docker-compose -f docker/stack.yml up
```