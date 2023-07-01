import franziska.greiner.config.WebClientConfig;
import franziska.greiner.config.WebClientConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WebClientConfigTest {

    @Mock
    private WebClientConfigProperties webClientConfigProperties;

    @Mock
    private WebClient.Builder webClientBuilderMock;

    private WebClientConfig webClientConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webClientConfig = new WebClientConfig(webClientConfigProperties, webClientBuilderMock);
    }

    @Test
    void webClient_returnsConfiguredWebClient() {
        // Mock the properties
        String baseUrl = "https://quizapi.io/api/v1";
        String apiKey = "78Kd7bqZLyRKpUbVoxSSnSzmzq2lM6IrGTTaXACW";
        when(webClientConfigProperties.getQuizApiBaseUrl()).thenReturn(baseUrl);
        when(webClientConfigProperties.getQuizApiKey()).thenReturn(apiKey);

        // Mock the behavior of WebClient.Builder
        WebClient webClientMock = WebClient.create();
        when(webClientBuilderMock.baseUrl(baseUrl)).thenReturn(webClientBuilderMock);
        when(webClientBuilderMock.defaultHeaders(Mockito.any())).thenReturn(webClientBuilderMock);
        when(webClientBuilderMock.build()).thenReturn(webClientMock);

        // Call the method under test
        WebClient webClient = webClientConfig.webClient();

        // Verify the WebClient configuration
        assertEquals(webClientMock, webClient);
    }
}
