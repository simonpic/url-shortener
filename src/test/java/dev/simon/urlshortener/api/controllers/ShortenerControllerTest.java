package dev.simon.urlshortener.api.controllers;

import dev.simon.urlshortener.domaine.services.ShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.MalformedURLException;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShortenerController.class)
public class ShortenerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShortenerService shortenerService;

    @Test
    public void shortensUrl_responds400WhenMalformedUrl() throws Exception {
        String plainUrl = "https://www.lemonde.fr/international/";
        when(shortenerService.createShortUrl(plainUrl)).thenThrow(new MalformedURLException());

        mvc.perform(post("/shortens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\": \"https://www.lemonde.fr/international/\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shortensUrl_Responds200WithShortenedUrl() throws Exception {
        String plainUrl = "https://www.lemonde.fr/international/";
        when(shortenerService.createShortUrl(plainUrl))
                .thenReturn("https://www.lemonde.fr/9b264132fc");

        mvc.perform(post("/shortens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\": \"https://www.lemonde.fr/international/\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortenedUrl", is("https://www.lemonde.fr/9b264132fc")));
    }

    @Test
    public void searchUrl_responds404_whenUnknownUrl() throws Exception {
        String url = "https://www.lemonde.fr/9b264132fc";
        when(shortenerService.searchPlainUrl(url)).thenReturn(Optional.empty());

        mvc.perform(get("/search").param("url", url))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchUrl_responds200WithPlainUrl() throws Exception {
        String url = "https://www.lemonde.fr/9b264132fc";
        when(shortenerService.searchPlainUrl(url))
                .thenReturn(Optional.of("https://www.lemonde.fr/international/"));

        mvc.perform(get("/search").param("url", url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plainUrl", is("https://www.lemonde.fr/international/")));
    }

}
