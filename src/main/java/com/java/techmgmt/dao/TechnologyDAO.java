package com.java.techmgmt.dao;


import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.java.techmgmt.entity.Technology;
import com.java.techmgmt.exception.TechnologyException;
import com.java.techmgmt.util.TechnologyRowMapper;

@Component
public class TechnologyDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Technology> mapper = new TechnologyRowMapper();
	
	
	final String updateQuery = "UPDATE Technology SET Name=?, Company=?, Year=? WHERE Id=?";
	final String deleteQuery = "DELETE FROM Technology WHERE Id=?";
	final String selectQuery = "SELECT * FROM Technology";
	final String selectForTechQuery = "SELECT * FROM Technology WHERE Id=?";
	
		
	
	public void deleteTechnology(int id) throws TechnologyException{
		try {
			jdbcTemplate.update(deleteQuery, id);
		}
		catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("delete caught exception "+ex.getMessage());
			throw new TechnologyException("Exception while deleting record with Id="+id);
		}
	}
	
	public Technology addTechnology(Technology e) throws TechnologyException{
		
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Technology")
					.usingColumns(new String[] {"Name", "Company", "Year"})
					.usingGeneratedKeyColumns(new String[] {"Id"});
			
			Map<String,Object> params = new HashMap<>();
			params.put("name", e.getName());
			params.put("company", e.getCompany());
			params.put("year", e.getYear());
			int id = insert.executeAndReturnKey(params).intValue();
			System.out.println("Inserted for id "+id);
			e.setId(id);
			return e;
		}
		catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Insert caught exception "+ex.getMessage());
			throw new TechnologyException("Exception while Inserting record with name="+e.getName());
		}

	}
	
	public Technology updateTechnology(Technology e) throws TechnologyException {
		
		try {
			jdbcTemplate.update(updateQuery, 
					new Object[] {e.getName(), e.getCompany(), e.getYear(), e.getId()}, 
					new int[] {Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER});
			return e;
		}
		catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Update caught exception "+ex.getMessage());
			throw new TechnologyException("Exception while Updating record with Id="+e.getId());
		}

	}
	
	public List<Technology> getAllTechnologies() throws TechnologyException{
		try {
			List<Technology> techList = jdbcTemplate.query(selectQuery, mapper);
			return techList;
		}
		catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("All caught exception "+ex.getMessage());
			throw new TechnologyException("Exception while getting Technology list");
		}
	}

	public Technology getTechnology(int id) throws TechnologyException{
		try {
			List<Technology> techs = jdbcTemplate.query(selectForTechQuery, new Object[] {id}, mapper );
			if(techs != null && techs.size() == 1) {
				return techs.get(0);
			} else {
				return null;
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new TechnologyException("Exception while getting Technology by Id="+id);
		}
	}
}
