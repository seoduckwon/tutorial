package com.sdf.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sdf.exception.NotFoundException;
import com.sdf.sql.MyConnection;
import com.sdf.vo.Allerge;
import com.sdf.vo.Employees;
import com.sdf.vo.EmployeesAllerge;

public class EmployeesDAOOracle implements EmployeesDAO {

	private SqlSessionFactory sqlSessionFatory;
	
	public EmployeesDAOOracle() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFatory = new SqlSessionFactoryBuilder().build(inputStream);
		/*String className="oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	@Override
	public List<Employees> selectAll() {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Employees> employeesAll = new ArrayList<Employees>();
		try {
		employeesAll = sqlSession.selectList("EmployeesMapper.selectAll");
		}finally {
			sqlSession.close();
		}
		return employeesAll;
		
		/*ArrayList<Employees> employeesAll = new ArrayList<Employees>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectAllSQL = 
				"SELECT *" + 
				" FROM EMPLOYEES";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Employees employee = new Employees();
				employee.setId(rs.getString("id"));
				employee.setPwd(rs.getString("pwd"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setIsadmin(rs.getInt("isadmin"));
				employee.setFirst(rs.getInt("first"));
				System.out.println(employee);
				employeesAll.add(employee);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
		return employeesAll;*/
	}

	@Override
	public Employees selectById(String id) throws NotFoundException {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		Employees employee = new Employees();
		try {
			employee = sqlSession.selectOne("EmployeesMapper.selectById",id);
		}finally {
			sqlSession.close();
		}
		 return employee;
		
		/*Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectByIdSQL = 
				"SELECT *" + 
				" FROM EMPLOYEES" + 
				" where id=?" ;
		try {
			con=MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Employees employee = new Employees();
				employee.setId(rs.getString("id"));
				employee.setPwd(rs.getString("pwd"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setIsadmin(rs.getInt("isadmin"));
				employee.setFirst(rs.getInt("first"));
				System.out.println(employee);
				return employee;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}throw new NotFoundException("해당 ID가 존재하지 않습니다."); */
	}

	@Override
	public Employees selectByEmail(String email) throws NotFoundException {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		Employees employee = new Employees();
		try {
		employee = sqlSession.selectOne("EmployeesMapper.selectByEmail",email);
		}finally {
			sqlSession.close();
		}
		return employee;
		/*Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectByIdSQL = 
				"SELECT *" + 
				" FROM EMPLOYEES" + 
				" where email=?" ;
		try {
			con=MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Employees employee = new Employees();
				employee.setId(rs.getString("id"));
				employee.setPwd(rs.getString("pwd"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setIsadmin(rs.getInt("isadmin"));
				employee.setFirst(rs.getInt("first"));
				return employee;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}throw new NotFoundException("해당 email이 존재하지 않습니다."); */
	}
	

	@Override
	public void updatePwd(Employees newEmp) {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
		sqlSession.update("EmployeesMapper.updatePwd",newEmp);
		System.out.println(newEmp.getId()+"사원의 비밀번호 변경 완료.");
		sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		/*Connection con = null;
		PreparedStatement pstmt = null;
		String updateSQL =
				"update employees SET pwd =? where id=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, newEmp.getPwd());
			pstmt.setString(2, newEmp.getId());
			pstmt.executeUpdate();
			System.out.println(newEmp.getId()+"사원의 비밀번호 수정 완료.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void insertEmpAlg(List<EmployeesAllerge> ealist) {
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
			for(EmployeesAllerge employeesAllerge : ealist) {
				sqlSession.insert("EmployeesMapper.insertEmpAlg",employeesAllerge);
			}
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		
		
		/*String id = ealist.get(0).getEmployees().getId();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		String insertSQL = 
				"INSERT INTO EMPLOYEES_ALLERGE "
				+ " VALUES(EMP_ALG_SEQ.NEXTVAL,?,?)";
		try {
			con = MyConnection.getConnection();
			for(EmployeesAllerge employeesAllerge : ealist) {
				pstmt = con.prepareStatement(insertSQL);
				pstmt.setString(1, id);
				pstmt.setInt(2, employeesAllerge.getAllerge().getAllerge_no());
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(null, pstmt, con);
		}*/
	}

	@Override
	public void deleteEmpAlg(String id) {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
			sqlSession.delete("EmployeesMapper.deleteEmpAlg",id);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		
		/*Connection con = null;
		PreparedStatement pstmt = null;
		String deleteSQL = 
				"DELETE EMPLOYEES_ALLERGE "+
				" WHERE id=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			System.out.println(id+"사원의 알레르기 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public List<EmployeesAllerge> selectEmpAlg(String id) {
		
		List<EmployeesAllerge> empAlgList = new ArrayList<EmployeesAllerge>();
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
		empAlgList = sqlSession.selectList("EmployeesMapper.selectEmpAlg",id);
		}finally {
			sqlSession.close();
		}
		return empAlgList;
		/*List<EmployeesAllerge> empAlgList = new ArrayList<EmployeesAllerge>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String selectEmpAlgSQL = 
				"SELECT *" + 
				" FROM employees_allerge WHERE id=?";
		try {
			con=MyConnection.getConnection();
			pstmt = con.prepareStatement(selectEmpAlgSQL);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				EmployeesAllerge employeesAllerge = new EmployeesAllerge();
				
				Employees employees = new Employees();
				employees.setId(rs.getString("id"));		
				employeesAllerge.setEmployees(employees);
				
				Allerge allerge = new Allerge();
				allerge.setAllerge_no(rs.getInt("allerge_no"));
				employeesAllerge.setAllerge(allerge);
				empAlgList.add(employeesAllerge);
				System.out.println(id+"사원의 알러지:"+employeesAllerge.getAllerge());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empAlgList;*/
	}

	public static void main(String[] args) throws IOException {
		/*EmployeesDAOOracle oracle = new EmployeesDAOOracle();
		System.out.println(oracle.selectAll());
		String id="k1001";
		
		String email = "qwqw@nate.com";
		
		try {
			System.out.println(oracle.selectByEmail(email));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		Employees newEmp = new Employees();
		newEmp.setId(id);
		newEmp.setPwd("940331");
		
		oracle.updatePwd(newEmp);
		
		try {
			oracle.selectById(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		oracle.deleteEmpAlg("k1003");
		System.out.println(oracle.selectEmpAlg(id));
		
		List<EmployeesAllerge> ealist = new ArrayList<>();
		
		Employees employees = new Employees();
		employees.setId("k1003");

		
		for(int i=10; i<15; i++) {
			EmployeesAllerge e1 = new EmployeesAllerge();
			e1.setEmployees(employees);
			Allerge allerge = new Allerge();
			allerge.setAllerge_no(i);
			e1.setAllerge(allerge);
			ealist.add(e1);
		}
		oracle.insertEmpAlg(ealist);
		System.out.println(ealist);
	*/	
	}
	
}