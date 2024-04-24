package net.chrisrichardson.faramir;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {FaramirControllerTest.Config.class})
class FaramirControllerTest {

    private RestClient client;

    @ComponentScan
    @EnableAutoConfiguration
    public static class Config {
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        client = RestClient.builder().baseUrl(String.format("http://localhost:%d", port)).build();
    }

    @Test
    public void testValidPhoneNumber() {
        var response = parsePhoneNumber("5105551212");
        assertEquals("(510) 555-1212", response.get("result"));
    }

    private Map<String, String> parsePhoneNumber(String phoneNumber) {
        ResponseEntity<Map<String, String>> response = client
                .post()
                .uri("/parse")
                .body(Map.of("phoneNumber", phoneNumber))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
        });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        return response.getBody();
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertThrows(HttpClientErrorException.BadRequest.class, () -> parsePhoneNumber("51055512"));
    }
}