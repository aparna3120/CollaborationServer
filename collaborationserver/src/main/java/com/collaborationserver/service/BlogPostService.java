package com.collaborationserver.service;

import java.util.List;
import com.collaborationserver.model.BlogComments;
import com.collaborationserver.model.Blog;

public interface BlogPostService
{
	List<Blog> findAll();
	List<Blog> findLatest5();
	Blog findById(int id);
	void create(Blog post);
	void edit(Blog post);
	void deleteById(int id);
	void postComment(BlogComments comments);
	List<BlogComments> showcomment(int id);
}