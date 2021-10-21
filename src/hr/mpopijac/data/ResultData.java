package hr.mpopijac.data;

public class ResultData {

    private String letters;

    private String path;

    public ResultData(String letters, String path) {
        this.letters = letters;
        this.path = path;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
