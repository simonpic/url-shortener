package dev.simon.urlshortener.domaine.services;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;
import dev.simon.urlshortener.domaine.repositories.ShortenedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
public class ShortenerServiceImpl implements ShortenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortenerService.class);

    private final UrlHasher urlHasher;
    private final ShortenedRepository shortenedRepository;
    private final String domain;

    public ShortenerServiceImpl(UrlHasher urlHasher, ShortenedRepository shortenedRepository,
                                @Value("${url-shortener.domain}") String domain) {
        this.urlHasher = urlHasher;
        this.shortenedRepository = shortenedRepository;
        this.domain = domain;
    }

    @Override
    public String createShortUrl(String fullUrl) throws MalformedURLException {
        URL url = new URL(fullUrl);
        String hash = urlHasher.hashUrl(url);
        ShortenedUrl shortenedUrl = new ShortenedUrl(hash, fullUrl);

        LOGGER.info("Hashed URL {} to {}", fullUrl, hash);

        shortenedRepository.save(shortenedUrl);

        return String.format("http://%s/%s", domain, hash);
    }

    @Override
    public Optional<String> searchFullUrl(String hash) {
        return shortenedRepository.findById(hash).map(ShortenedUrl::getOriginalUrl);
    }
}
