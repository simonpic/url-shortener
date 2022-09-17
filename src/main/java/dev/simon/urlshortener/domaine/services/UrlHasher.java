package dev.simon.urlshortener.domaine.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.net.URL;

@Component
public class UrlHasher {

    private final int size;

    public UrlHasher(@Value("${url-shortener.hash.size:10}") int size) {
        this.size = size;
    }

    String hashUrl(URL url) {
        return DigestUtils
                .md5DigestAsHex(url.toString().getBytes())
                .substring(0, size);
    }

}
