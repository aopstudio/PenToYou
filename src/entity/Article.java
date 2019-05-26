package entity;

public class Article {
    private String id;
    private String title;
    private String description;
    private String picurl;
    private String opening;
    private String category;

    public Article() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Article(String id, String title, String description, String picurl, String opening, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.picurl = picurl;
        this.opening = opening;
        this.category = category;
    }
}
