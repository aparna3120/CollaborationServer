package com.collaborationserver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.collaborationserver.dao.UserServiceImpl;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;


@RestController
public class UserController
{
	
	@Autowired
	Users users;
	
	 @Autowired
	 UserService userService;  //Service which will do all data retrieval/manipulation work
	 
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);

	     
	    //-------------------Retrieve All Users--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<Users>> listAllUsers() 
	    {
	        List<Users> user = userService.findAllUsers();
	        if(user.isEmpty())
	        {
	        	users.setErrorCode("404");
				users.setErrorMessage("No users available");
	            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve Single User--------------------------------------------------------
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Users> getUser(@PathVariable("id") String id)
	    {
	        System.out.println("Fetching User with id " + id);
	        users = userService.findById(id);
	        if (users == null)
	        {
	            System.out.println("User with id " + id+ " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Users>(users, HttpStatus.OK);
	    }
	    
	    //-------------------Create a User--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public ResponseEntity<String> registerUSer(@RequestBody Users user)
	    {
	        System.out.println("Creating User " + user.getUsername());
	        if(!userService.isUserExist(user.getUsername()))
	        {
	        	user.setStatus('N');// N --> New User
	        	userService.saveUser(user);		
	        	return new ResponseEntity<String>(HttpStatus.CREATED);
	        }
	        else
	        {
	        	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    //------------------- Update a User --------------------------------------------------------
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Users> updateUser(@PathVariable("id") String id, @RequestBody Users user)
	    {
	    	System.out.println("Fetching & updating User with name " + id);
	  	    user = userService.findById(id);
	        if (user == null) 
	        {
	            System.out.println("Unable to update. User with name " + id + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	user.setId(user.getId());
	        	user.setName(user.getName());
	        	user.setUsername(user.getUsername());
	        	user.setAddress(user.getAddress());
	        	user.setEmail(user.getEmail());
	        	user.setPhno(user.getPhno());	
	        	user.setPassword(user.getPassword());
	        	user.setRole(user.getRole());
	        	user.setReason(user.getReason());
	        		        	
	        	userService.updateUser(user);
	        	return new ResponseEntity<Users>(HttpStatus.OK);
	        }
	    }
	    
	    //------------------- Delete a User --------------------------------------------------------
	  /*  @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
	    public ResponseEntity<Users> deleteUser(@PathVariable("id") String name) {
	        System.out.println("Fetching & Deleting User with name " + name);
	        Users user = userService.findById(name);
	        if (user == null) 
	        {
	            System.out.println("Unable to delete. User with name " + name + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	  
	        userService.deleteUserById(name);
	        return new ResponseEntity<Users>(HttpStatus.OK);
	    }
 */
	    
	    @RequestMapping(value = "/tvalidate/", method = RequestMethod.POST)
		public ResponseEntity<Users> login(@RequestBody Users user, HttpSession session) {
			log.debug("Starting of the method login" );
			
			String id = user.getId();
			 user = userService.validate(user.getId(), user.getPassword());
			if (user == null) {
				user = new Users();
				user.setErrorCode("404"); // NLP NullPointerException
				user.setErrorMessage("User does not exist with this id:" + id);
			} else {
				user.setIsOnline('Y');
				user.setErrorCode("200"); // NLP NullPointerException
				user.setErrorMessage("You have successfully logged");
		
				userService.updateUser(user);

				session.setAttribute("loggedInUserID", user.getId());
			}
			log.debug("Ending of the method login" );
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}

		@RequestMapping(value = "/tlogout/", method = RequestMethod.GET)
		public ResponseEntity<Users> logout(HttpSession session) {
			log.debug("Starting of the method logout" );
			
			String loggedInUserID= (String) session.getAttribute("loggedInUserID");
			
			log.debug("loggedInUserID : " + loggedInUserID);
			
			users =userService.findById(loggedInUserID);
			
			log.debug("user:"+ users);
			users.setIsOnline('N');

			session.invalidate();

			if (userService.updateUser(users)) {
				users.setErrorCode("200");
				users.setErrorMessage("You have logged out successfully");
				log.debug("ErrorMessageOfLogout"+ users.getErrorMessage());
			} else {
				users.setErrorCode("404");
				users.setErrorMessage("You could not logged. please contact admin");
			}
			log.debug("Ending of the method logout" );
			return new ResponseEntity<Users>(users, HttpStatus.OK);

		}


}