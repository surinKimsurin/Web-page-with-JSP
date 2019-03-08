package com.myspring.blog.post.vo;

import org.springframework.stereotype.Component;

@Component("postVO")
public class PostVO {
	private int post_articleNO;
	private int post_parentNO;
	private String post_title;
	private String post_content;
	private String post_imageFileName;
	private String post_writeDate;
	private String post_theme;
	private String post_id;
	
	public int getPost_articleNO() {
		return post_articleNO;
	}
	public void setPost_articleNO(int post_articleNO) {
		this.post_articleNO = post_articleNO;
	}
	public int getPost_parentNO() {
		return post_parentNO;
	}
	public void setPost_parentNO(int post_parentNO) {
		this.post_parentNO = post_parentNO;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPost_imageFileName() {
		return post_imageFileName;
	}
	public void setPost_imageFileName(String post_imageFileName) {
		this.post_imageFileName = post_imageFileName;
	}
	public String getPost_writeDate() {
		return post_writeDate;
	}
	public void setPost_writeDate(String post_writeDate) {
		this.post_writeDate = post_writeDate;
	}
	public String getPost_theme() {
		return post_theme;
	}
	public void setPost_theme(String post_theme) {
		this.post_theme = post_theme;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public PostVO(int post_articleNO, int post_parentNO, String post_title, String post_content,
			String post_imageFileName, String post_writeDate, String post_theme, String post_id) {
		super();
		this.post_articleNO = post_articleNO;
		this.post_parentNO = post_parentNO;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_imageFileName = post_imageFileName;
		this.post_writeDate = post_writeDate;
		this.post_theme = post_theme;
		this.post_id = post_id;
	}
	
	public PostVO() {}
}
