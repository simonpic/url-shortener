package dev.simon.urlshortener.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class RedirectController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);

    @GetMapping
    public void redirect(HttpServletRequest request) {
        LOGGER.info(request.getPathInfo());
    }

}
