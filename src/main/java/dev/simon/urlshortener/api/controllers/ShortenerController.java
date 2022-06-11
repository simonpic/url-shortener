package dev.simon.urlshortener.api.controllers;

import dev.simon.urlshortener.api.resources.GetURLResponse;
import dev.simon.urlshortener.api.resources.PostURLRequest;
import dev.simon.urlshortener.api.resources.PostURLResponse;
import dev.simon.urlshortener.domaine.services.ShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/url-shortener")
public class ShortenerController {

    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping("/shortens")
    public PostURLResponse shortensUrl(@RequestBody PostURLRequest body) {
        try {
            String shortUrl = shortenerService.createShortUrl(body.getUrl());
            return new PostURLResponse(shortUrl);
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public GetURLResponse searchUrl(@RequestParam("url") String url) {
        String fullUrl = shortenerService.searchFullUrl(url)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown URL " + url));
        return new GetURLResponse(fullUrl);
    }

}