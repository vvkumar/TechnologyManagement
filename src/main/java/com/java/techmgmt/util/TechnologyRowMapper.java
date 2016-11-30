package com.java.techmgmt.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.java.techmgmt.entity.Technology;

public class TechnologyRowMapper implements RowMapper<Technology>{

	@Override
	public Technology mapRow(ResultSet rs, int rowNum) throws SQLException {
		Technology tech = new Technology(rs.getInt("Id"), rs.getString("Name"), rs.getString("Company"), rs.getInt("Year"));
		return tech;
	}

}
