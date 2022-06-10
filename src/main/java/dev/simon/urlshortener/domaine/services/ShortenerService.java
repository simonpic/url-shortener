package dev.simon.urlshortener.domaine.services;

import java.net.MalformedURLException;
import java.util.Optional;

public interface ShortenerService {

    String createShortUrl(String plainUrl) throws MalformedURLException;

    Optional<String> searchPlainUrl(String shortUrl);
}
