package dev.simon.urlshortener.itcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.simon.urlshortener.api.resources.PostURLResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DisplayName("Case: create shortened URL")
public class ShortensUrlITCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortensUrlITCase.class);

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void shortensUrl() throws Exception {
        String fullUrl = "https://www.lemonde.fr/international/";

        String postBody = "{\"url\": \"https://www.lemonde.fr/international/\"}";

        LOGGER.info("--- Step 1 -> Send post request to shorten url\n{}", postBody);

        String postResponseBody = mvc.perform(post("/url-shortener/shortens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        LOGGER.info("\tResponse received\n{}", postResponseBody);

        LOGGER.info("--- Step 2 -> Verify previously create URL is persisted and retrievable by the shortened URL");

        PostURLResponse postURLResponse = objectMapper.readValue(postResponseBody, PostURLResponse.class);

        String getReponseBody = mvc.perform(get("/url-shortener/search").param("url", postURLResponse.getShortenedUrl()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullUrl", is(fullUrl)))
                .andReturn().getResponse().getContentAsString();

        LOGGER.info("\tFull URL successfully retrieved\n{}", getReponseBody);
    }

}
