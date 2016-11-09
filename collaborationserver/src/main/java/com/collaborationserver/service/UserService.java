package com.collaborationserver.service;
import java.util.List;

import com.collaborationserver.model.Users;
 
 
 
public interface UserService {
     
   
     
    Users findByName(String name);
     
    void saveUser(Users user);
     
    void updateUser(Users user);
     
    void deleteUserById(String name);
 
    List<Users> findAllUsers(); 
     
   public boolean isUserExist(String name);
   
   public boolean check(String name,String Password);
     
}