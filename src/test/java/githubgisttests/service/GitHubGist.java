package githubgisttests.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import githubgisttests.entitites.Gist;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Base64;
import java.util.logging.Logger;

public class GitHubGist {

    private String credentials = "tatsiana-marfel-test:be4f99ad7519dfb18254b25953542eb1fdc6ac44";
    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate = new RestTemplate();
    private String[] gistUrl;
    private Gist gist = null;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private HttpEntity<?> requestEntity;

    public GitHubGist() {
        httpHeaders = authenticateUser();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        requestEntity = new HttpEntity<>(httpHeaders);
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
        requestEntity = new HttpEntity<>(gist, httpHeaders);
        ResponseEntity<JsonObject> responseEntity = restTemplate.exchange("https://api.github.com/gists", HttpMethod.POST, requestEntity, JsonObject.class);
        JsonObject response = responseEntity.getBody();
        gistUrl = response.get("url").toString().split("\"");
        if (responseEntity.getStatusCodeValue() == 201) {
            logger.info("The gist URL is " + gistUrl[1]);
        } else {
            logger.info("There was a failure creating the gist");
        }
        return this;

    }

    public GitHubGist starGist() {
        try {
            restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.GET, requestEntity, String.class).getStatusCodeValue();
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            int starStatus = restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.PUT, requestEntity, String.class).getStatusCodeValue();
            if (starStatus == 204) {
                logger.info("The gist was successfully starred");
            } else {
                logger.info("There was a failure starring the gist");
            }
        }
        return this;
    }

    public GitHubGist editGist(String fileName) {
        try {
            gist = new Gson().fromJson(new JsonReader(new FileReader(fileName)), Gist.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        requestEntity = new HttpEntity<>(gist, httpHeaders);
        int editStatus = restTemplate.exchange(gistUrl[1], HttpMethod.PATCH, requestEntity, JsonObject.class).getStatusCodeValue();
        if (editStatus == 200) {
            logger.info("The gist was successfully edited");
        } else {
            logger.info("There was a failure editing the gist.");
        }
        return this;
    }

    public GitHubGist unstarGist() {
        int starStatus = restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.GET, requestEntity, String.class).getStatusCodeValue();
        if (starStatus == 204) {
            int unstarStatus = restTemplate.exchange(gistUrl[1] + "/star", HttpMethod.DELETE, requestEntity, String.class).getStatusCodeValue();
            if (unstarStatus == 204) {
                logger.info("The star was successfully removed");
            } else {
                logger.info("There was a failure unstarring the gist.");
            }
        }
        return this;
    }

    public GitHubGist deleteGist() {
        int deleteStatus = restTemplate.exchange(gistUrl[1], HttpMethod.DELETE, requestEntity, String.class).getStatusCodeValue();
        if (deleteStatus == 204) {
            logger.info("The gist with at " + gistUrl[1] + "was successfully deleted.");
        }
        return this;
    }


    public String[] getGistUrl() {
        return gistUrl;
    }
}
