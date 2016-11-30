package com.java.techmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.java.techmgmt.entity.Technology;
import com.java.techmgmt.exception.TechnologyException;
import com.java.techmgmt.service.TechnologyService;

@RestController
public class TechnologyController {

	
	 //@Autowired  
	 //private HttpServletRequest request;  
	 
	 @Autowired
	 private TechnologyService technologyService;
	 
	  
	 @RequestMapping(value = "/technologies", method = RequestMethod.GET, headers = "Accept=application/json")  
	 public List<Technology> getTechnologies()  throws TechnologyException{  
	    
		  List<Technology> listOfTechnologies = technologyService.getAllTechnologies();  
		  System.out.println("Returned Technologies " +listOfTechnologies);
		  return listOfTechnologies;  
	 }  
	  
	 @RequestMapping(value = "/technology/{id}", method = RequestMethod.GET, headers = "Accept=application/json")  
	 public ResponseEntity getTechnologyById(@PathVariable int id)  {  
		 ResponseEntity result = null;
		 try {
			 Technology tech = technologyService.getTechnology(id);
			 result = new ResponseEntity(tech, HttpStatus.OK);
			 System.out.println("Requested Technology with id " +id);
		 	
		 }
		 catch (Exception e) {
			 System.out.println("Controller Get caught exception "+e.getMessage());
			 result = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 return result;
	}  
	  
	 @RequestMapping(value = "/addTechnology", method = RequestMethod.POST, headers = "Accept=application/json")  
	 public Technology addTechnology(@RequestBody Technology Technology)  throws TechnologyException {  
		 System.out.println("Added Technology " +Technology);
		 return technologyService.addTechnology(Technology);  
	 } 
	 
	  
	@RequestMapping(value = "/updTechnology", method = RequestMethod.PUT, headers = "Accept=application/json")  
	 public Technology updateTechnology(@RequestBody Technology Technology)  throws TechnologyException {  
		 System.out.println("Updated Technology " +Technology);
		 return technologyService.updateTechnology(Technology);  
	  
	 }  
	 	 
	  
	 @RequestMapping(value = "/delTechnology/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	 public void deleteTechnology(@PathVariable("id") int id)  throws TechnologyException{ 
		 System.out.println("Deleted Technology with id" +id);
		 technologyService.deleteTechnology(id);  
	  
	 }    
}
