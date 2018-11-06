package com.sdf.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.sdf.dao.AllergeDAO;
import com.sdf.vo.Allerge;

public class AllergeService {
	private static AllergeService service;
	private static String propertiesFileName;
	private AllergeDAO dao;
	private AllergeService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("service.properties"));
			env.load(new FileInputStream(propertiesFileName));
			String className = env.getProperty("allergedao");
			dao = (AllergeDAO)Class.forName(className).newInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPropertiesFileName() {
		return propertiesFileName;
	}

	public static void setPropertiesFileName(String propertiesFileName) {
		AllergeService.propertiesFileName = propertiesFileName;
	}

	public static AllergeService getInstance() {
		if(service == null) {
			service = new AllergeService();
		}
		return service;
	}
	
	public List<Allerge> findAll(){
		return dao.selectAll();
	}
	
	public Allerge findByNo(int allerge_no) {
		return dao.selectByNo(allerge_no);
	}
	
	public Allerge findByName(String allerge_name) {
		return dao.selectByName(allerge_name);
	}
}
