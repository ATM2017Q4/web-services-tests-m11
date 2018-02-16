package githubgisttests.tests;

import githubgisttests.service.GitHubGist;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GitHubGistsTests {
    private RestTemplate restTemplate = new RestTemplate();
    private String fileName1 = "./src/test/resources/Gist1.json";
    private String fileName2 = "./src/test/resources/Gist2.json";

    @Test
    public void checkGitHubGist() {
        GitHubGist gist = new GitHubGist();
        gist.createGist(fileName1)
                .starGist()
                .editGist(fileName2)
                .unstarGist()
                .deleteGist();
        UrlResource gistUrl = null;
        try {
            gistUrl = new UrlResource(gist.getGistUrl()[1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assertFalse(gistUrl.exists());
    }


}
