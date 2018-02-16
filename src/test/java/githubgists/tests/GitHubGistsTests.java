package githubgists.tests;

import githubgists.service.GitHubGist;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;


public class GitHubGistsTests {
    String[] gistUrl;
    String fileName1 = "./src/test/resources/Gist1.json";
    String fileName2 = "./src/test/resources/Gist2.json";


    @Test
    public void createGist() {
        GitHubGist gist = new GitHubGist();
        gist.createGist(fileName1)
                .starGist()
                .editGist(fileName2)
                .unstarGist()
                .deleteGist();
    }


}
