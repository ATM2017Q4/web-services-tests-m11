package githubgists.entitites;

public class Gist {
    private String description;
    private Boolean publicity;
    private Files files;

    public String getDescription() {
        return description;
    }

    public Boolean getPublicity() {
        return publicity;
    }

    public Files getFiles() {
        return files;
    }

    public static class Files{
        private File file;

        public File getFile() {
            return file;
        }


    }
   public static class File{
        public String getContent() {
            return content;
        }

        private String content;


    }

}
