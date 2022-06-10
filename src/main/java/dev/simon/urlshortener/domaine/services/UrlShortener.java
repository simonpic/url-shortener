package dev.simon.urlshortener.domaine.services;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.net.URL;

@Component
public class UrlShortener {

    String shortensUrl(URL url) {
        String hash = DigestUtils
                .md5DigestAsHex(url.toString().getBytes())
                .substring(0, 10);

        return buildShortURL(url, hash);
    }

    private String buildShortURL(URL url, String hash) {
        StringBuilder sb = new StringBuilder(url.getProtocol());
        sb.append("://");
        sb.append(url.getHost());
        if (url.getPort() > -1) {
            sb.append(url.getPort());
        }
        sb.append("/");
        sb.append(hash);
        return sb.toString();
    }

}
