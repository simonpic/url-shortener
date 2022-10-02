package dev.simon.urlshortener.web.controllers;

import dev.simon.urlshortener.domaine.services.ShortenerService;
import dev.simon.urlshortener.web.dto.UrlForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ShortenerService shortenerService;

    @Autowired
    public HomeController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("urlForm", new UrlForm());
        return "index";
    }

    @PostMapping
    public String submitForm(@ModelAttribute UrlForm urlForm, Model model) {
        System.out.println(urlForm.getUrl());
        String shortUrl = null;
        try {
            shortUrl = shortenerService.createShortUrl(urlForm.getUrl());
            model.addAttribute("shortUrl", shortUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }

        return homePage(model);
    }

}
