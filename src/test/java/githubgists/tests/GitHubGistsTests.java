package githubgists.tests;

import githubgists.service.GitHubGist;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;


public class GitHubGistsTests {
    RestTemplate restTemplate = new RestTemplate();
    String[] gistUrl;
    String fileName1 = "./src/test/resources/Gist1.json";
    String fileName2 = "./src/test/resources/Gist2.json";


    @Test
    public void createGist() {
//        String credentials = "tanya-marfel:81c369b6f2aa4aa8feeba55432ef4cc8ff6d1c8a";
//        byte[] creds = credentials.getBytes();
//        byte[] creds64 = Base64.getEncoder().encode(creds);
//        String base64Creds = new String(creds64);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic " + base64Creds);
//        Gist gist = null;
//        try {
//            gist = new Gson().fromJson(new JsonReader(new FileReader("./src/test/resources/Gist1.json")), Gist.class);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        HttpEntity<Gist> request = new HttpEntity<Gist>(gist, headers);
//        ResponseEntity<JsonObject> responseEntity = restTemplate.exchange("https://api.github.com/gists", HttpMethod.POST, request, JsonObject.class);
//        JsonObject response = responseEntity.getBody();
//        gistUrl = response.get("url").toString().split("\"");
//        System.out.println(gistUrl[1]);
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//        restTemplate.exchange(gistUrl[1], HttpMethod.DELETE,requestEntity , String.class);
        GitHubGist gist = new GitHubGist();
        gist.createGist(fileName1)
                .starGist()
                .editGist(fileName2)
                .unstarGist()
                ;
    }


}
