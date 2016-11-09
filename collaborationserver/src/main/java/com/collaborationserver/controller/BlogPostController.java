package com.collaborationserver.controller;

import java.util.Date;
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
import com.collaborationserver.model.BlogComments;
import com.collaborationserver.model.Blog;
import com.collaborationserver.service.BlogPostService;


@RestController
public class BlogPostController 
{
	 @Autowired
	 BlogPostService bps;
	     
	    //-------------------Retrieve All Blogs--------------------------------------------------------
	    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
	    public ResponseEntity<List<Blog>> listAllBlogs() 
	    {
	        List<Blog> blog = bps.findAll();
	        if(blog.isEmpty())
	        {
	            return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	    }
	    
	  //-------------------Retrieve top 5 Blogs--------------------------------------------------------
	    @RequestMapping(value = "/blog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Blog>> list5Blogs() 
	    {
	    	List<Blog> blog = bps.findLatest5();
	        if(blog.isEmpty())
	        {
	            return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	    }
	    
	  //-------------------Create a Blog--------------------------------------------------------
	    @RequestMapping(value = "/blog", method = RequestMethod.POST)
	    public ResponseEntity<Void> createBlog(@RequestBody Blog post,    UriComponentsBuilder ucBuilder)
	    {
	           	Date d=new Date();
	           	post.setDate(d);
	    		bps.create(post);
	        	HttpHeaders headers = new HttpHeaders();
	        	headers.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(post.getBlogid()).toUri());
	        	return new ResponseEntity<Void>(HttpStatus.CREATED);	       
	    }
	    
	    //------------------- Update a Blog --------------------------------------------------------
	    @RequestMapping(value = "/blog/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog  post)
	    {
	    	Blog existingpost = bps.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	existingpost.setBlogpostcontent(post.getBlogpostcontent());
	        	Date d=new Date();
	        	existingpost.setDate(d);
	        	bps.edit(existingpost);
	        	return new ResponseEntity<Blog>(HttpStatus.OK);
	        }
	    }
	    
	    //------------------- Delete a Blog --------------------------------------------------------
	    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id) {
	      
	    	Blog existingpost = bps.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	bps.deleteById(id);
	        	return new ResponseEntity<Blog>(HttpStatus.OK);
	        }
	    }
	    
	  //------------------- Comment a Blog --------------------------------------------------------
	    @RequestMapping(value = "/blogcomment/{id}", method = RequestMethod.POST)
	    public ResponseEntity<Blog> commentBlog(@PathVariable("id") int id, @RequestBody BlogComments  comment)
	    {
	    	Blog existingpost = bps.findById(id);
	        if (existingpost == null) 
	        {
	            System.out.println("post not found");
	            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	        }
	        else
	        {
	        	comment.setBlogid(existingpost.getBlogid());
	        	bps.postComment(comment);
	        	return new ResponseEntity<Blog>(HttpStatus.OK);
	        }
	    }
	    
	  //-------------------Retrieve All comments--------------------------------------------------------
	    @RequestMapping(value = "/blogcomment/{id}", method = RequestMethod.GET)
	    public ResponseEntity<List<BlogComments>> listAllComments(@PathVariable("id") int id) 
	    {
	        List<BlogComments> comment = bps.showcomment(id);
	        if(comment.isEmpty())
	        {
	            return new ResponseEntity<List<BlogComments>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<BlogComments>>(comment, HttpStatus.OK);
	    }
}