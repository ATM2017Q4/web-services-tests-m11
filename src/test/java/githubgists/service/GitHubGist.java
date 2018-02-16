package githubgists.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import githubgists.entitites.Gist;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Base64;

public class GitHubGist {

    private String credentials = "tanya-marfel:81c369b6f2aa4aa8feeba55432ef4cc8ff6d1c8a";
    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate = new RestTemplate();
    private String[] gistUrl;
    private Gist gist = null;


    public GitHubGist() {
        httpHeaders = authenticateUser();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public HttpHeaders authenticateUser() {
        byte[] credentialsBytes = credentials.getBytes();
        byte[] credentialsBytes64 = Base64.getEncoder().encode(credentialsBytes);
        String base64Credentials = new String(credentialsBytes64);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        return headers;
    }

    public GitHubGist createGist(String fileName) {

        try {
            gist = new Gson().fromJson(new JsonReader(new FileReader(fileName)), Gist.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpEntity<Gist> request = new HttpEntity<>(gist, httpHeaders);
        ResponseEntity<JsonObject> responseEntity = restTemplate.exchange("https://api.github.com/gists", HttpMethod.POST, request, JsonObject.class);
        JsonObject response = responseEntity.getBody();
        gistUrl = response.get("url").toString().split("\"");
        System.out.println(gistUrl[1]);
        return this;

    }

    public GitHubGist starGist() {
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.PUT, requestEntity, String.class);
        return this;
    }

    public GitHubGist editGist(String fileName) {
        try {
            gist = new Gson().fromJson(new JsonReader(new FileReader(fileName)), Gist.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpEntity<Gist> request = new HttpEntity<>(gist, httpHeaders);
        ResponseEntity<JsonObject> responseEntity = restTemplate.exchange(gistUrl[1], HttpMethod.PATCH, request, JsonObject.class);
        return this;
    }

    public GitHubGist unstarGist() {
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        int starStatus = restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.GET, requestEntity, String.class).getStatusCodeValue();
        if (starStatus == 204) {
            restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.DELETE, requestEntity, String.class).getStatusCodeValue();
        }
        return this;
    }

    public GitHubGist deleteGist() {
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
       restTemplate.exchange(gistUrl[1], HttpMethod.DELETE, requestEntity, String.class);
        return this;
    }
}
