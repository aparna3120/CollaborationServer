package com.collaborationserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;


@RestController
public class UserController
{
	 @Autowired
	 UserService userService;  //Service which will do all data retrieval/manipulation work
	     
	    //-------------------Retrieve All Users--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<Users>> listAllUsers() 
	    {
	        List<Users> users = userService.findAllUsers();
	        if(users.isEmpty())
	        {
	            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve Single User--------------------------------------------------------
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Users> getUser(@PathVariable("name") String name)
	    {
	        System.out.println("Fetching User with name " + name);
	        Users user = userService.findByName(name);
	        if (user == null)
	        {
	            System.out.println("User with name " + name+ " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Users>(user, HttpStatus.OK);
	    }
	    
	    //-------------------Create a User--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public ResponseEntity<String> createUser(@RequestBody Users user)
	    {
	        System.out.println("Creating User " + user.getUsername());
	        if(!userService.isUserExist(user.getUsername()))
	        {
	        	userService.saveUser(user);
	        	//HttpHeaders headers = new HttpHeaders();
	        	//headers.setLocation(ucBuilder.path("http://localhost:8085/collaborationclient/login.jsp").buildAndExpand().toUri());
	        	return new ResponseEntity<String>(HttpStatus.CREATED);
	        }
	        else
	        {
	        	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    //------------------- Update a User --------------------------------------------------------
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.POST)
	    public ResponseEntity<Users> updateUser(@PathVariable("name") String name, @RequestBody Users user)
	    {
	    	System.out.println("Fetching & updating User with name " + name);
	  	    Users currentuser = userService.findByName(name);
	        if (currentuser == null) 
	        {
	            System.out.println("Unable to update. User with name " + name + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	currentuser.setEmail(user.getEmail());
	        	currentuser.setPhno(user.getPhno());
	        	currentuser.setAddress(user.getAddress());
	        	userService.updateUser(currentuser);
	        	return new ResponseEntity<Users>(HttpStatus.OK);
	        }
	    }
	    
	    //------------------- Delete a User --------------------------------------------------------
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
	    public ResponseEntity<Users> deleteUser(@PathVariable("name") String name) {
	        System.out.println("Fetching & Deleting User with name " + name);
	        Users user = userService.findByName(name);
	        if (user == null) 
	        {
	            System.out.println("Unable to delete. User with name " + name + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	  
	        userService.deleteUserById(name);
	        return new ResponseEntity<Users>(HttpStatus.OK);
	    }
} 