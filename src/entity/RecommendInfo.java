package entity;

public class RecommendInfo {
	private String author;
	private String id;
    private String title;
    private String description;
    private String opening;
    private int display=0;
    private String scale="";
    private String slateX="";
    private int zIndex=0;
    private String style=" ";
	public RecommendInfo(Article article) {
		super();
		this.author = article.getAuthor();
		this.id = article.getId();
		this.title = article.getTitle();
		this.description = article.getDescription();
		this.opening = article.getOpening();
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getOpening() {
		return opening;
	}
	public void setOpening(String opening) {
		this.opening = opening;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getSlateX() {
		return slateX;
	}
	public void setSlateX(String slateX) {
		this.slateX = slateX;
	}
	public int getzIndex() {
		return zIndex;
	}
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
    
}
