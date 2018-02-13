package jsonplaceholdertests.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import jsonplaceholdertests.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleTests {
    Gson gson = new Gson();
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);

    @Test
    public void checkStatusCode() {
        int httpStatus = response.getStatusCodeValue();
        assertEquals(200, httpStatus);
    }

    @Test
    public void checkResponseHeader(){
       assertNotNull(response.getHeaders().getContentType());
    }

    @Test
    public void checkResponseBody(){
        User[] users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", User[].class);
        JsonArray jsonArray = new JsonParser().parse(gson.toJson(users)).getAsJsonArray();
        assertEquals(10, jsonArray.size());
    }




}
