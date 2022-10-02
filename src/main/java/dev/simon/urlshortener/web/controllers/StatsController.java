package dev.simon.urlshortener.web.controllers;

import dev.simon.urlshortener.domaine.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public String getStats(Model model) {
        var urls = statsService.getUrls();
        model.addAttribute("urls", urls);
        return "stats";
    }

}
