package com.sdf.dao;

import java.util.Collection;
import java.util.List;

import com.sdf.exception.NotFoundException;
import com.sdf.vo.Employees;
import com.sdf.vo.EmployeesAllerge;

public interface EmployeesDAO {
	/**
	 * 
	 * @return 사원 전체
	 */
	List<Employees> selectAll();
	
	/**
	 * 
	 * @param id 사원id
	 * @return id가 일치하는 사원
	 */
	Employees selectById(String id) throws NotFoundException;
	
	
	/**
	 * 
	 * @param email 사원email
	 * @return email이 일치하는 사원
	 * @throws NotFoundException 
	 */
	Employees selectByEmail(String email) throws NotFoundException;
	
	/**
	 * 
	 * @param newEmp employees객체를 넣어줘서 비밀번호 변경하기
	 * @return 비밀번호가 변경된 사원
	 */
	void updatePwd(Employees newEmp);
	
	/**
	 * 
	 * @param collection service에서 호출시 인자유의-updateEmpAlg(List<EmployeesAllerge>)
	 */
	//void insertEmpAlg(Collection collection);
	void insertEmpAlg(List<EmployeesAllerge> ealist);
	
	/**
	 * 검사
	 * @param id 해당id의 allerge삭제
	 */
	void deleteEmpAlg(String id);
	
	
	/**
	 * 검사
	 * @param id 사원 id
	 * @return 해당 id의 사원이 갖고 있는 allerge
	 */
	List<EmployeesAllerge> selectEmpAlg(String id);
	
	

	
}