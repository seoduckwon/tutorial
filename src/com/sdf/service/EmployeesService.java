package com.sdf.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.sdf.service.EmployeesService;
import com.sdf.vo.Allerge;
import com.sdf.vo.Employees;
import com.sdf.vo.EmployeesAllerge;
import com.sdf.dao.EmployeesDAO;
import com.sdf.exception.NotFoundException;

public class EmployeesService {
	private static EmployeesService service;
	private static String propertiesFileName;
	private EmployeesDAO dao;
	private EmployeesService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("service.properties"));
			env.load(new FileInputStream(propertiesFileName));
			String className = env.getProperty("employeesdao");
			dao = (EmployeesDAO)Class.forName(className).newInstance();
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
	
	public static EmployeesService getInstance() {
		if(service == null) {
			service = new EmployeesService();
		}
		return service;
	}
	
	public String login(String id,String pwd) {
		 try {
		     if(dao.selectById(id).getPwd().equals(pwd)) {
		        return "로그인 성공";
		     }
		  } catch (NotFoundException e) {
			  
		  }   
		  return "로그인 실패";
	}
	
	
	public List<Employees> findAll(){
		return dao.selectAll();
	}
	
	public Employees findById(String id) throws NotFoundException {
		return dao.selectById(id);
	}
	
	public Employees findByEmail(String email) throws NotFoundException {
		return dao.selectByEmail(email);		
	}
	
	public void ChangePwd(Employees newEmp) {
		dao.updatePwd(newEmp);
	}
	
	public void ChangeEmpAlg(List<EmployeesAllerge> ealist) {
		List<EmployeesAllerge> list = new ArrayList<>();
		String id = ealist.get(0).getEmployees().getId();
		list.addAll(dao.selectEmpAlg(id));
		if(list != null) {
			dao.deleteEmpAlg(id);
		}
		dao.insertEmpAlg(ealist);
	}

	public static String getPropertiesFileName() {
		return propertiesFileName;
	}

	public static void setPropertiesFileName(String propertiesFileName) {
		EmployeesService.propertiesFileName = propertiesFileName;
	}

	public static void main(String[] args) {
/*		EmployeesService service = new EmployeesService().getInstance();
		List<EmployeesAllerge> ealist = new ArrayList<>();
		
		EmployeesAllerge e1 = new EmployeesAllerge();
		Employees employees = new Employees();
		employees.setId("k1003");
		
		System.out.println(service.findAll());
		
		e1.setEmployees(employees);
		
		Allerge allerge = new Allerge();
		allerge.setAllerge_no(1);
		
		e1.setAllerge(allerge);
		
		ealist.add(e1);
		
		for(int i=10; i<15; i++) {
			e1.setEmployees(employees);
			
			Allerge allerge = new Allerge();
			allerge.setAllerge_no(i);
			
			e1.setAllerge(allerge);
			
			ealist.add(e1);
			System.out.println(i+":"+ealist);
			
		}
		service.ChangeEmpAlg(ealist);*/
	}
	
}