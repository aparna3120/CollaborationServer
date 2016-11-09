package com.collaborationserver.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.BlogComments;
import com.collaborationserver.model.Blog;
import com.collaborationserver.service.BlogPostService;

@Repository
public class BlogPostImpl implements BlogPostService 
{

	@Autowired
	SessionFactory sessionFactory;
	

	public List<Blog> findAll() 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<Blog> showuser = s.createQuery("FROM Blog where status='true'").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}

	public List<Blog> findLatest5() 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<Blog> showuser = s.createQuery("FROM Blog order by blogid desc where status='true' FETCH FIRST 5 ROWS ONLY").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}

	public Blog findById(int id) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Blog c=(Blog)s.get(Blog.class,id);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return c;
	}
	
	public void create(Blog post)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		s.save(post);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}

	public void edit(Blog post)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Blog c=(Blog)s.get(Blog.class,post.getBlogid());
		c.setBlogpostcontent(post.getBlogpostcontent());
		c.setDate(post.getDate());
		s.update(c);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}

	public void deleteById(int id) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Blog c=(Blog)s.get(Blog.class,id);
		s.delete(c);
		tx.commit();
		s.flush();
		s.clear();
		s.close();

	}
	
	public void postComment(BlogComments comments)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		s.save(comments);
		tx.commit();
		s.flush();
		s.clear();
		s.close();		
	}

	public List<BlogComments> showcomment(int id)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<BlogComments> showuser = s.createQuery("FROM BlogComments where blogid="+id+"").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}


}
