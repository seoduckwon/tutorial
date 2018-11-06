package com.sdf.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sdf.sql.MyConnection;
import com.sdf.vo.Allerge;

public class AllergeDAOOracle implements AllergeDAO {
	private SqlSessionFactory sqlSessionFatory;
	
	
	public AllergeDAOOracle() throws IOException {
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

/*	@Override
	public void insert(Allerge a) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insertSQL = 

	}*/

	@Override
	public List<Allerge> selectAll() {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Allerge> alglist = new ArrayList<>();
		try {
			alglist = sqlSession.selectList("AllergeMapper.selectAll");
			System.out.println(alglist);
		}finally {
			sqlSession.close();
		}
		return alglist;
	
		/*List<Allerge> alglist = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insertSQL = "SELECT * FROM ALLERGE";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Allerge alg = new Allerge();
				alg.setAllerge_no(rs.getInt("allerge_no"));
				alg.setAllerge_name(rs.getString("allerge_name"));
				alglist.add(alg);
			}
			System.out.println(alglist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}return alglist;*/
	}	

	@Override
	public Allerge selectByNo(int allerge_no) {
		Allerge alg = new Allerge();
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
			alg = sqlSession.selectOne("AllergeMapper.selectByNo",allerge_no);
			System.out.println(alg);
		}finally {
			sqlSession.close();
		}
		return alg;
		/*
		  Allerge alg = new Allerge();
		  Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectbynoSQL = "select * from allerge " + 
				"where allerge_no=?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectbynoSQL);
			pstmt.setInt(1, allerge_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			alg.setAllerge_no(rs.getInt("allerge_no"));
			alg.setAllerge_name(rs.getString("allerge_name"));
			
			System.out.println(alg);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(null,pstmt, con);
		}return alg;*/
		
		
	}
	
	public Allerge selectByName(String allerge_name) {
		Allerge alg = new Allerge();
		SqlSession sqlSession = sqlSessionFatory.openSession();
		try {
			alg = sqlSession.selectOne("AllergeMapper.selectByName",allerge_name);
			System.out.println(alg);
		}finally {
			sqlSession.close();
		}
		return alg;
		
		/*Allerge alg = new Allerge();
		 Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectbynoSQL = "select * from allerge " + 
				"where allerge_name=?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectbynoSQL);
			pstmt.setString(1, allerge_name);
			rs = pstmt.executeQuery();
			
			rs.next();
			alg.setAllerge_no(rs.getInt("allerge_no"));
			alg.setAllerge_name(rs.getString("allerge_name"));
			System.out.println(alg);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(null,pstmt, con);
		}
		
		return alg;*/
	}
	
	public static void main(String[] args) throws IOException {
		AllergeDAOOracle oracle = new AllergeDAOOracle();
		int allerge_no = 3;
		String allerge_name = "달걀";
		
		oracle.selectAll();
		System.out.println("-----------------------------");
		oracle.selectByName(allerge_name);
		System.out.println("-----------------------------");
		oracle.selectByNo(allerge_no);
	}
	
}
