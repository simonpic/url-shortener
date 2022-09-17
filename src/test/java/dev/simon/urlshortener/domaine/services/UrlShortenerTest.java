package dev.simon.urlshortener.domaine.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlShortenerTest {

    private static final int SIZE = 10;

    private UrlHasher urlHasher;

    @BeforeEach
    public void setup() {
        urlHasher = new UrlHasher(SIZE);
    }

    @Test
    public void hashUrl_returnsHash() throws MalformedURLException {
        String fullUrl = "https://www.lemonde.fr/international/";
        URL url = new URL(fullUrl);

        String shortenedUrl = urlHasher.hashUrl(url);

        String expectedHash = computeHash(fullUrl);
        assertThat(shortenedUrl).isEqualTo(expectedHash);
    }

    private String computeHash(String url) {
        return DigestUtils
                .md5DigestAsHex(url.getBytes())
                .substring(0, SIZE);
    }

}
