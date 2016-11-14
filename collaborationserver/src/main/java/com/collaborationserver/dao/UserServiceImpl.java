package com.collaborationserver.dao;
import java.util.List;


import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;


@Repository("userService")
public class UserServiceImpl implements UserService 
{
	@Autowired
	SessionFactory sessionFactory;
	
	 private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	 
	 public UserServiceImpl(SessionFactory sessionFactory)
		{
			log.debug("Starting of the method  TUserDAOImpl");
			this.sessionFactory = sessionFactory;
			log.debug("Ending of the method  TUserDAOImpl");
			
		}
		


	public Users findById(String id) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users c=(Users)s.get(Users.class,id);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return c;
	}

	public void saveUser(Users user) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		s.save(user);
		/*Users uc=new Users();
		uc.setUsername(user.getUsername());
		uc.setPassword(user.getPassword());
		s.save(uc);*/
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}

	public boolean updateUser(Users user)
	{
		log.debug("Starting of the method  update");
		log.debug("update the user " + user);
		try {
			sessionFactory.openSession().update(user);
			log.debug("updated " + user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		/*try{
				Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users c=(Users)s.get(Users.class,user.getUsername());
		c.setAddress(user.getAddress());
		c.setPhno(user.getPhno());
		c.setEmail(user.getEmail());
		s.update(c);
		tx.commit();
		s.flush();
		s.close();
		return true;
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
			return false;
		}*/
	}
	/*public void deleteUserById(String name)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users c=(Users)s.get(Users.class,name);
		UserCredentials uc=(UserCredentials)s.get(UserCredentials.class,name);
		s.delete(uc);
		s.delete(c);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
	}*/
	public List<Users> findAllUsers() 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<Users> showuser = s.createQuery("FROM Users").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}
	public boolean isUserExist(String name)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users c=(Users)s.get(Users.class,name);
		s.flush();
		s.clear();
		s.close();
		if(c==null)
			return false;
		else
			return true;
	}

	public boolean check(String name,String Password) 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users u=(Users)s.get(Users.class,name);
		System.out.println(u.getPassword());
		s.flush();
		s.clear();
		s.close();
		if(u.getPassword().equals(Password))
		{
			System.out.println("hello");
			return true;
		}
		else
			return false;
	}
	
	public Users validate(String id, String password) {
		log.debug("Starting of the method  validate");
		//select * from User where id= '' and password = ''
		String hql = "from Users  where id= '" + id +"' and password = '" + password+"'";
		
		Query query=  sessionFactory.openSession().createQuery(hql);
		log.debug("Ending of the method  UserServiceImpl");
		return  (Users)query.uniqueResult();
	
	}

	}
