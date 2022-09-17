package dev.simon.urlshortener.domaine.services;

import java.net.MalformedURLException;
import java.util.Optional;

public interface ShortenerService {

    String createShortUrl(String fullUrl) throws MalformedURLException;

    Optional<String> searchFullUrl(String hash);
}
