package com.java.techmgmt.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import com.java.techmgmt.entity.Technology;

public class TechnologyDAOTest {

	static TechnologyDAO dao = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext("src\\main\\webapp\\WEB-INF\\techmgmt-servlet.xml");
		dao = context.getBean(TechnologyDAO.class);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTechnology() throws Exception{
		Technology t = dao.addTechnology(new Technology(-1, "TestAdd", "TestCompany", 2000));
		
		Technology tech = dao.getTechnology(t.getId());
		assertNotNull("Technology not retrieved",tech);
		
	}
	
	@Test
	public void testAddTechnology() throws Exception{
		Technology t = dao.addTechnology(new Technology(-1, "Test", "TestCompany", 2000));
		assertNotNull("Technology not addedd",t);
		assertNotEquals("Added Technology should not have Id=-1",t.getId(), -1);
	}
	
	@Test
	public void testDeleteTechnology() throws Exception{
		Technology t = dao.addTechnology(new Technology(-1, "TestNew", "TestCompanyNew", 2010));
		
		dao.deleteTechnology(t.getId());
		Technology ret = dao.getTechnology(t.getId());
		assertNull("Technology not deleted",ret);
	}

	@Test
	public void testUpdateTechnology() throws Exception{
		Technology t = dao.addTechnology(new Technology(-1, "TestForUpdate", "TestCompany", 2005));
		Technology ret = dao.updateTechnology(new Technology(t.getId(), "TestUpdate", "TestUpdate", 2005));
		
		assertNotNull(ret);
		assertEquals("Technology details not updated",ret.getName(), "TestUpdate");
	}
	
	@Test
	public void testGetAllTechnologies() throws Exception{
		List<Technology> t = dao.getAllTechnologies();
		
		assertNotNull(t);
	}
}
