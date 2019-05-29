package entity;

import java.util.ArrayList;

public class ChapterInfo {
	private String id;
	private String pid;
    private String content;
    private String title;
    private ArrayList<ChapterInfo> children;
    
    
	public ChapterInfo() {
		children=new ArrayList<ChapterInfo>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	
	public ArrayList<ChapterInfo> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ChapterInfo> children) {
		this.children = children;
	}
	public void haveChildren() {
		this.children=new ArrayList<ChapterInfo>();
	}
	public ChapterInfo(String id, String pid, String content, String title) {
		super();
		this.id = id;
		this.pid = pid;
		this.content = content;
		this.title = title;
		
	}
    
}
