package jsonplaceholdertests.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import jsonplaceholdertests.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleTests {
    private Gson gson = new Gson();
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void checkStatusCode() {
        int httpStatus = response.getStatusCodeValue();
        logger.info("The actual value is " + httpStatus);
        assertEquals(200, httpStatus);
    }

    @Test
    public void checkResponseHeader() {
        MediaType contentType = response.getHeaders().getContentType();
        logger.info("The value of Content-Type header is " + contentType.toString());
        assertNotNull(contentType);
    }

    @Test
    public void checkResponseBody() {
        User[] users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", User[].class);
        JsonArray jsonArray = new JsonParser().parse(gson.toJson(users)).getAsJsonArray();
        logger.info("The size of JSON array in response is " + jsonArray.size());
        assertEquals(10, jsonArray.size());
    }


}
