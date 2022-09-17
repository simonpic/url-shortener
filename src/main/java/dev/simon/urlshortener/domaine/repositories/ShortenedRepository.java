package dev.simon.urlshortener.domaine.repositories;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortenedRepository extends MongoRepository<ShortenedUrl, String> {

}
