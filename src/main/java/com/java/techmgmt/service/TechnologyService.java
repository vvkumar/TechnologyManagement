package com.java.techmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.techmgmt.dao.TechnologyDAO;
import com.java.techmgmt.entity.Technology;
import com.java.techmgmt.exception.TechnologyException;

@Service
public class TechnologyService {


	@Autowired
	TechnologyDAO technologyDAO;
	
	public Technology getTechnology(int id) throws TechnologyException{
		
		if(id == 3) {
			throw new TechnologyException("You asked to throw exception for 3");
		}
		return technologyDAO.getTechnology(id);
	}
	
	public void deleteTechnology(int id)  throws TechnologyException{
		technologyDAO.deleteTechnology(id);
	}
	
	public Technology addTechnology(Technology e)  throws TechnologyException{
		
		return technologyDAO.addTechnology(e);
	}
	
	public Technology updateTechnology(Technology e)  throws TechnologyException {
		
		return technologyDAO.updateTechnology(e);
	}
	
	public List<Technology> getAllTechnologies()  throws TechnologyException{
		return technologyDAO.getAllTechnologies();
	}
}
