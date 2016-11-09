package com.collaborationserver.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.Fourm;
import com.collaborationserver.model.FourmComments;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.FourmService;

@Repository
public class FourmImpl implements FourmService
{
	@Autowired
	SessionFactory sessionFactory;

	public List<Fourm> findAll()
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<Fourm> showuser = s.createQuery("FROM Fourm").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}

	public Fourm findById(int id) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Fourm c=(Fourm)s.get(Fourm.class,id);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return c;
	}

	public void create(Fourm fourm)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		s.save(fourm);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}

	public void edit(Fourm fourm) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Fourm c=(Fourm)s.get(Fourm.class,fourm.getForumid());
		c.setForumsubtopic(fourm.getForumsubtopic());		
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
		Fourm c=(Fourm)s.get(Fourm.class,id);
		s.delete(c);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}

	public void postComment(FourmComments comments)
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

	public List<FourmComments> showcomment(int id)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<FourmComments> showuser = s.createQuery("FROM FourmComments where fourmid="+id).list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}
	

}
