package com.collaborationserver.service;
import java.util.List;

import com.collaborationserver.model.Users;
 
 
 
public interface UserService {
     
   
     
    Users findById(String id);
     
    void saveUser(Users user);
     
    boolean updateUser(Users user);
     
    //void deleteUserById(String name);
 
    List<Users> findAllUsers(); 
     
   public boolean isUserExist(String name);
   
   public boolean check(String name,String Password);
 
   public Users validate(String id, String password);
}