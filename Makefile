build_and_start: create_buildpack start_app

create_buildpack:
	./mvnw spring-boot:build-image

start_app:
	docker-compose -f docker/stack.yml up

stop_app:
	docker-compose -f docker/stack.yml down
