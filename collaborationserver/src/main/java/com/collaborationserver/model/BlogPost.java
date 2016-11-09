package com.collaborationserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BlogPost")
public class BlogPost
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogid;
	@Column
	private int userid;
	@Column
	private String blogtitle;
	@Column
	private String blogpostcontent;
	@Column
	private String tags;
	@Column
	private Date date;
	@Column
	private boolean status=false;
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
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
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
