package dev.simon.urlshortener.domaine.services;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;
import dev.simon.urlshortener.domaine.repositories.ShortenedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
public class ShortenerServiceImpl implements ShortenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortenerService.class);

    private final UrlShortener urlShortener;
    private final ShortenedRepository shortenedRepository;

    public ShortenerServiceImpl(UrlShortener urlShortener, ShortenedRepository shortenedRepository) {
        this.urlShortener = urlShortener;
        this.shortenedRepository = shortenedRepository;
    }

    @Override
    public String createShortUrl(String fullUrl) throws MalformedURLException {
        URL url = new URL(fullUrl);
        String shortUrl = urlShortener.shortensUrl(url);
        ShortenedUrl shortenedUrl = new ShortenedUrl(shortUrl, fullUrl);

        LOGGER.info("Shortens URL {} into {}", fullUrl, shortUrl);

        shortenedRepository.save(shortenedUrl);
        return shortUrl;
    }

    @Override
    public Optional<String> searchFullUrl(String shortUrl) {
        return shortenedRepository.findByShortUrl(shortUrl).map(ShortenedUrl::getFullUrl);
    }
}
