package com.collaborationserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Blog")
public class Blog
{
	@Id
	private int blogid;
	@Column
	private String username;
	@Column
	private String blogtitle;
	@Column
	private String blogpostcontent;
	@Column
	private String tags;
	@Column
	private Date Dateofcreation ;
	@Column
	private String status="true";
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getUserid() {
		return username;
	}
	public void setUserid(String username) {
		this.username = username;
	}
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	public String getBlogpostcontent() {
		return blogpostcontent;
	}
	public void setBlogpostcontent(String blogpostcontent) {
		this.blogpostcontent = blogpostcontent;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getDate() {
		return Dateofcreation ;
	}
	public void setDate(Date Dateofcreation) 
	{
		this.Dateofcreation  = Dateofcreation;
	}

}
