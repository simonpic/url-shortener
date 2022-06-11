package dev.simon.urlshortener.domaine.services;

import dev.simon.urlshortener.domaine.entities.ShortenedUrl;
import dev.simon.urlshortener.domaine.repositories.ShortenedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class ShortenerServiceTest {

    @Mock
    private UrlShortener urlShortener;

    @Mock
    private ShortenedRepository shortenedRepository;

    @InjectMocks
    private ShortenerServiceImpl shortenerService;

    @Test
    public void createShortUrl_throwsMalformedUrlException_whenMalformedUrl() {
        String malformedUrl = "htt42ps://www.lemonde.fr/international/";

        assertThatExceptionOfType(MalformedURLException.class)
                .isThrownBy(() -> shortenerService.createShortUrl(malformedUrl));

        verify(shortenedRepository, never()).save(any());
    }

    @Test
    public void createShortUrl_ReturnsShortenedUrl() throws MalformedURLException {
        String fullUrl = "https://www.lemonde.fr/international/";
        URL url = new URL(fullUrl);
        when(urlShortener.shortensUrl(url)).thenReturn("https://www.lemonde.fr/9b264132fc");

        String actual = shortenerService.createShortUrl(fullUrl);

        assertThat(actual).isEqualTo("https://www.lemonde.fr/9b264132fc");

        ArgumentCaptor<ShortenedUrl> captor = ArgumentCaptor.forClass(ShortenedUrl.class);
        verify(shortenedRepository, times(1)).save(captor.capture());
        ShortenedUrl captured = captor.getValue();
        assertThat(captured).isNotNull();
        assertThat(captured.getShortUrl()).isEqualTo("https://www.lemonde.fr/9b264132fc");
        assertThat(captured.getFullUrl()).isEqualTo("https://www.lemonde.fr/international/");
    }

    @Test
    public void searchFullUrl_returnsEmptyOptional_whenUnknownUrl() {
        String shortUrl = "https://www.lemonde.fr/9b264132fc";
        when(shortenedRepository.findByShortUrl(shortUrl)).thenReturn(Optional.empty());

        var actual = shortenerService.searchFullUrl(shortUrl);

        assertThat(actual).isEmpty();
    }

    @Test
    public void searchFullUrl_returnsOptionalWithFullUrl() {
        String shortUrl = "https://www.lemonde.fr/9b264132fc";
        var shortenedUrl = new ShortenedUrl(shortUrl, "https://www.lemonde.fr/international/");
        when(shortenedRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(shortenedUrl));

        var actual = shortenerService.searchFullUrl(shortUrl);

        assertThat(actual).contains("https://www.lemonde.fr/international/");
    }

}
