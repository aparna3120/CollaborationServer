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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.collaborationserver.model.Fourm;
import com.collaborationserver.model.FourmComments;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.FourmService;

@RestController
public class FourmController
{
	@Autowired
	 FourmService fs;
	     
	    //-------------------Retrieve All Fourm--------------------------------------------------------
	    @RequestMapping(value = "/fourm", method = RequestMethod.GET)
	    public ResponseEntity<List<Fourm>> listAllBlogs() 
	    {
	        List<Fourm> fourm = fs.findAll();
	        if(fourm.isEmpty())
	        {
	            return new ResponseEntity<List<Fourm>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Fourm>>(fourm, HttpStatus.OK);
	    }
	    
	  
	  //-------------------Create a Fourm--------------------------------------------------------
	    @RequestMapping(value = "/fourm", method = RequestMethod.POST)
	    public ResponseEntity<Void> createBlog(@RequestBody Fourm fourm,    UriComponentsBuilder ucBuilder)
	    {
	    	fs.create(fourm);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/fourm/{id}").buildAndExpand(fourm.getForumid()).toUri());
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	    
	    //------------------- Update a Fourm --------------------------------------------------------
	    @RequestMapping(value = "/fourm/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Fourm> updateBlog(@PathVariable("id") int id, @RequestBody Fourm  fourm)
	    {
	    	Fourm existingpost = fs.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Fourm>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	existingpost.setForumsubtopic(fourm.getForumsubtopic());
	        	fs.edit(existingpost);
	        	return new ResponseEntity<Fourm>(HttpStatus.OK);
	        }
	    }
	    
	    //------------------- Delete a Fourm --------------------------------------------------------
	    @RequestMapping(value = "/fourm/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Fourm> deleteBlog(@PathVariable("id") int id) {
	      
	    	Fourm existingpost = fs.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Fourm>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	fs.deleteById(existingpost.getForumid());
	        	return new ResponseEntity<Fourm>(HttpStatus.OK);
	        }
	    }
	    
	  //------------------- Comment a Fourm --------------------------------------------------------
	    @RequestMapping(value = "/fourmcomment/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Fourm> commentBlog(@PathVariable("id") int id, @RequestBody FourmComments  comment)
	    {
	    	Fourm existingpost = fs.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Fourm>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	comment.setFourmid(existingpost.getForumid());
	        	fs.postComment(comment);
	        	return new ResponseEntity<Fourm>(HttpStatus.OK);
	        }
	    }
	    
	  //-------------------Retrieve All comments--------------------------------------------------------
	    @RequestMapping(value = "/fourmcomment/{id}", method = RequestMethod.GET)
	    public ResponseEntity<List<FourmComments>> listAllComments(@PathVariable("id") int id) 
	    {
	        List<FourmComments> comment = fs.showcomment(id);
	        if(comment.isEmpty())
	        {
	            return new ResponseEntity<List<FourmComments>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<FourmComments>>(comment, HttpStatus.OK);
	    }
}