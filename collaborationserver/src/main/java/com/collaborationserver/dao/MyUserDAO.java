package com.collaborationserver.dao;

import java.util.List;

import com.collaborationserver.model.Users;

public interface MyUserDAO {
	public boolean save(Users users);
	public boolean update(Users users);
	public Users get(String id);
	public List<Users> list();
	public Users validate(String id, String password);
}
