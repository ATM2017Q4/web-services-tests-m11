package githubgists.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import githubgists.entitites.Gist;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GitHubGistsTests {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void createGist() throws FileNotFoundException {

        //JsonObject jsonObject = new JsonParser().parse(new JsonReader(new FileReader("./src/test/java/resources/Gist.json"))).getAsJsonObject();
        Gist gist = new Gson().fromJson(new JsonReader(new FileReader("./src/test/java/resources/Gist.json")), Gist.class);
        System.out.println(gist.getDescription());
        System.out.println(gist.getFiles().getFile().getContent());
        System.out.println(gist.getPublicity());
        //System.out.println(gist.getFiles().getFile().getContent());

//        System.out.println(gist.getDescription());
//        System.out.println(gist.isPublicity());
        //Gist result = restTemplate.postForObject("https://api.github.com/gists", gist, Gist.class);
        //System.out.println(result);
    }
}
