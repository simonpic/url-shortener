package dev.simon.urlshortener.api.controllers;

import dev.simon.urlshortener.domaine.services.ShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class RedirectController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);

    private final ShortenerService shortenerService;

    @Autowired
    public RedirectController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping("/**")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hash = request.getRequestURI().substring(1);
        String url = shortenerService.searchFullUrl(hash).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LOGGER.info("Redirect {} to {}", hash, url);
        response.sendRedirect(url);
    }

}
