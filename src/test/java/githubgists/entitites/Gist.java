package githubgists.entitites;

public class Gist {
    private String description;
    private Files files;

    public String getDescription() {
        return description;
    }

    public Files getFiles() {
        return files;
    }

    public static class Files {

        private File file;

        public File getFile() {
            return file;
        }


    }

    public static class File {
        private String content;
        private String fileName;

        public String getFileName() {
            return fileName;
        }

        public String getContent() {
            return content;
        }

    }

}
