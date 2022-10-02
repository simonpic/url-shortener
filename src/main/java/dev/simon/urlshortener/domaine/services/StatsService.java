package dev.simon.urlshortener.domaine.services;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;
import dev.simon.urlshortener.domaine.repositories.ShortenedRepository;
import dev.simon.urlshortener.web.dto.StatUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final ShortenedRepository shortenedRepository;
    private final String redirecterDomain;

    @Autowired
    public StatsService(ShortenedRepository shortenedRepository, @Value("${url-shortener.redirecter.domain}") String redirecterDomain) {
        this.shortenedRepository = shortenedRepository;
        this.redirecterDomain = redirecterDomain;
    }

    public List<StatUrl> getUrls() {
        return shortenedRepository.findAll().stream().map(this::mapUrl).collect(Collectors.toList());
    }

    private StatUrl mapUrl(ShortenedUrl shortenedUrl) {
        String shortUrl = String.format("http://%s/%s", redirecterDomain, shortenedUrl.getHash());
        return new StatUrl(shortUrl, shortenedUrl.getOriginalUrl());
    }

}
