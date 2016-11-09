package com.collaborationserver.model;

import javax.persistence.*;


@Entity
public class FourmComments 
{
	@Id
	private int fourmcommentid;
	@Column
	private int fourmid;
	@Column
	private String username;
	@Column
	private String comments;
	
	public int getFourmcommentid() {
		return fourmcommentid;
	}
	public void setFourmcommentid(int fourmcommentid) {
		this.fourmcommentid = fourmcommentid;
	}
	public int getFourmid() {
		return fourmid;
	}
	public void setFourmid(int fourmid) {
		this.fourmid = fourmid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comments;
	}
	public void setComment(String comments) {
		this.comments = comments;
	}

}
