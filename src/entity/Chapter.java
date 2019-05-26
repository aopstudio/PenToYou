package entity;

public class Chapter {
    private String id;
    private String content;
    private String title;

    public Chapter(String id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public Chapter() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
