package com.java.techmgmt.entity;

public class Technology implements Comparable<Technology>{

	private String name;
	private int id;
	private int year;
	private String company;
	
	public Technology() {
		
	}
	
	public Technology(int id, String name, String company, int year) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.year = year;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Technology other = (Technology) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Technology [name=" + name + ", id=" + id + ", year=" + year + ", company=" + company + "]";
	}
	
	public int compareTo(Technology o) {
		if (o == null) {
			return -1;
		}
		else {
			return Integer.valueOf(this.id).compareTo(o.getId());
		}
	}
	
	
}
