package ar.com.gopay;

import ar.com.gopay.payload.JwtAuthenticationResponse;
import ar.com.gopay.payload.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = GoPayApplication.class)
public class GoPayApplicationTests {

    private TestRestTemplate restTemplate;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @LocalServerPort
	private int port;

	private URL base;

    @Before
    public void setUp() throws MalformedURLException {

        restTemplate = new TestRestTemplate();

        base = new URL("http://localhost:" + port + contextPath);
    }

    @Test
    public void jwtAuthentication_ThenSuccess() throws IllegalStateException {

        HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("despegar", "123456"));

        ResponseEntity<JwtAuthenticationResponse> response =
                restTemplate.exchange(base.toString() + "/auth/signin",
                HttpMethod.POST, request, JwtAuthenticationResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response
                .getBody()
                .getTokenType(), "Bearer");
        assertEquals(response.getBody().getAccessToken().length(), 168);
    }

}
