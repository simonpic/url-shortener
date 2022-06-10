package dev.simon.urlshortener.domaine.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlShortenerTest {

    private static final int SIZE = 10;

    private UrlShortener urlShortener;

    @BeforeEach
    public void setup() {
        urlShortener = new UrlShortener(SIZE);
    }

    @Test
    public void shortensUrl_returnsShortUrl_sansPort() throws MalformedURLException {
        String plainUrl = "https://www.lemonde.fr/international/";
        URL url = new URL(plainUrl);

        String shortenedUrl = urlShortener.shortensUrl(url);

        String expectedHash = computeHash(plainUrl);
        assertThat(shortenedUrl).isEqualTo("https://www.lemonde.fr/" + expectedHash);
    }

    @Test
    public void shortensUrl_returnsShortUrl_withPort() throws MalformedURLException {
        String plainUrl = "https://www.lemonde.fr:4567/international/";
        URL url = new URL(plainUrl);

        String shortenedUrl = urlShortener.shortensUrl(url);

        String expectedHash = computeHash(plainUrl);
        assertThat(shortenedUrl).isEqualTo("https://www.lemonde.fr:4567/" + expectedHash);
    }

    private String computeHash(String url) {
        return DigestUtils
                .md5DigestAsHex(url.getBytes())
                .substring(0, SIZE);
    }

}
