package dev.simon.urlshortener.domaine.services;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface ShortenerService {

    String createShortUrl(String fullUrl) throws MalformedURLException;

    Optional<String> searchFullUrl(String hash);
}
