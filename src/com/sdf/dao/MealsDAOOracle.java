package com.sdf.dao;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sdf.exception.DuplicatedException;
import com.sdf.exception.NotFoundException;
import com.sdf.vo.Food;
import com.sdf.vo.FoodAllerge;
import com.sdf.vo.Meals;

public class MealsDAOOracle implements MealsDAO {
	
	private SqlSessionFactory sqlSessionFatory;
	
	public MealsDAOOracle() throws IOException {
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
	public List<Meals>  selectByMonth(int month) {
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Meals> mealslist = new ArrayList<>();	
		mealslist = sqlSession.selectList("MealsMapper.selectByMonth", month);
		
		System.out.println("List<FoodAllerge> selectByMonth() - return size :"+mealslist.size());
		return mealslist;
		
	/*	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//int searchdate = (date.getMonth()+1);	//service 단에서 +1해줌
		
		List<Meals> mealslist = new ArrayList<>();		
		
		try {
			con = MyConnection.getConnection();
	
			String sql = "SELECT m.meals_date, m.food_no,"
					+ " f.food_name FROM MEALS m join FOOD f"
					+ " on m.food_no = f.food_no"
					+ " WHERE extract(month from m.meals_date) = ?"
					+ " ORDER BY 1"
					; //빠른 날짜 순 정리
		//검색 SQL 문을 LIKE '18/09/%'로 할경우, 함수를 두번 불러서 조합해야해서 인자를 간단하게 넣는 구문으로 수정함
			
			
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, searchdate);
			pstmt.setInt(1, month);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				Meals m = new Meals() ;
				m.setMeals_date(rs.getDate("meals_date"));
				
				Food food = new Food();
				food.setFood_no(rs.getInt("food_no"));
				food.setFood_name(rs.getString("food_name"));
				m.setFood(food);
				
				mealslist.add(m);
				
			}
			System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
				//확인용
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			MyConnection.close(rs,pstmt,  con);
		}
		
		if(mealslist.isEmpty()) {
			try {
				throw new NotFoundException("월간 식단이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return mealslist;*/
		
	
	}

	@Override
	public List<Meals> selectByDay(String date) {
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Meals> daylist = new ArrayList<>();
		daylist = sqlSession.selectList("MealsMapper.selectByDay",date);
		
		return daylist;
		
		
	/*	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		List<Meals> mealslist = new ArrayList<>();		
		
		try {
			con = MyConnection.getConnection();
	
			String sql = "SELECT  m.meals_date, f.food_no "
					+ ", f.food_name"
					+ ",f.food_cate ,f.ingredient ,f.kcal"
					+ " FROM MEALS m join FOOD f"
					+ " on m.food_no = f.food_no"
					+ " WHERE m.meals_date = ?"
					+ " ORDER BY 2"
					; //빠른 food_no 순 정리
				
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				Meals m = new Meals() ;
				m.setMeals_date(rs.getDate("meals_date"));
				
				Food food = new Food();
				food.setFood_no(rs.getInt("food_no"));
				food.setFood_name(rs.getString("food_name"));
				food.setFood_cate(rs.getString("food_cate"));
				food.setIngredient(rs.getString("ingredient"));
				food.setKcal(rs.getInt("kcal"));
				m.setFood(food);
				
				mealslist.add(m);
				
			}
			System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
			//확인용
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			MyConnection.close(rs,pstmt,  con);
		}
		
		if(mealslist.isEmpty()) {
			try {
				throw new NotFoundException("하루 식단이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return mealslist;*/
		
		
	}

	
	@Override
	public List<Meals>  selectByWeek(int week) {
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Meals> mealslist = new ArrayList<>();	
		mealslist = sqlSession.selectList("MealsMapper.selectByWeek", week);
		
		System.out.println("List<FoodAllerge> selectByWeek() - return size :"+mealslist.size());
		
		return mealslist;
		
		/*Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Meals> mealslist = new ArrayList<>();		
		try {
			con = MyConnection.getConnection();
			String sql
				= "SELECT m.meals_date, m.food_no, f.food_name" 
				+ " FROM MEALS m join FOOD f on m.food_no=f.food_no" 
				+ " where TO_CHAR(meals_date,'IW') = ?" 
				+ " order by 1 asc" 
				;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, week);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Meals m = new Meals() ;
				m.setMeals_date(rs.getDate("meals_date"));
				Food food = new Food();
				food.setFood_no(rs.getInt("food_no"));
				food.setFood_name(rs.getString("food_name"));
				m.setFood(food);
				mealslist.add(m);
			}
			System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");	//확인용
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close( rs,pstmt, con);
		}
		if(mealslist.isEmpty()) {
			try {
				throw new NotFoundException("주간 식단이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return mealslist;*/
		
		
	}

	
	public List<FoodAllerge> selectAllFoodAllerge(){
		
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<FoodAllerge> foodallergelist = new ArrayList<>();
		foodallergelist = sqlSession.selectList("MealsMapper.selectAllFoodAllerge");
		System.out.println("List<FoodAllerge> selectAllFoodAllerge() - return size :"+foodallergelist.size());
		return foodallergelist;
		
		
		
		/*List<FoodAllerge> foodallergelist = new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectAllSQL = 
				"SELECT fa.allerge_no, f.* FROM FOOD_ALLERGE fa JOIN FOOD f "
				+ " on fa.food_no = f.food_no";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FoodAllerge foodallerge = new FoodAllerge();
				
				Allerge allerge = new Allerge();
				allerge.setAllerge_no(rs.getInt("allerge_no"));
				foodallerge.setAllerge(allerge);
				
				Food food = new Food();
				food.setFood_no(rs.getInt("food_no"));
				food.setFood_name(rs.getString("food_name"));
				food.setFood_cate(rs.getString("food_cate"));
				food.setIngredient(rs.getString("ingredient"));
				food.setKcal(rs.getInt("kcal"));
				foodallerge.setFood(food);
			
				foodallergelist.add(foodallerge);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
		
		if(foodallergelist.isEmpty()) {
			try {
				throw new NotFoundException("알레르기가 포함된 음식들이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return foodallergelist;*/
		
	}
	
	
	
	
	public List<Meals> selectByMonthWithAllerge(int month){
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Meals> listwithAllerge = new ArrayList<>();	
		listwithAllerge = sqlSession.selectList("MealsMapper.selectByMonthWithAllerge", month);
		
		System.out.println("List<FoodAllerge> selectByMonthWithAllerge() - return size :"+listwithAllerge.size());
		
		return listwithAllerge;
		
		
		/*List<Meals> listwithAllerge = new  ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			con = MyConnection.getConnection();
			
			List<FoodAllerge> foodallerge=null;
			
			Date older_date = null;
			int older_food_no = 0;
			
			String sql = "SELECT m.meals_date, f.food_no, f.food_name, fa.allerge_no FROM MEALS m" + 
					"  left join food f on m.food_no=f.food_no" + 
					"  left join FOOD_ALLERGE fa on f.FOOD_NO = fa.FOOD_NO" + 
					"  where extract(month from meals_date) = ? "+ 
					" order by 1,2,4"
					
					; //빠른 날짜->food_no -> allerge_no (null first) 순 정리
		
			
			
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, searchdate);
			pstmt.setInt(1, month);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				Meals meals = new Meals();
				Food food = new Food();
				Date meals_date = rs.getDate("meals_date");
				int food_no = rs.getInt("food_no");
				
				if(older_food_no!=food_no) {
					//Meals m = new Meals() ;
					//m.setMeals_date(rs.getDate("meals_date"));
					
					meals.setMeals_date(meals_date);					
					
					//food.setFood_no(rs.getInt("food_no"));
					food.setFood_no(food_no);
					food.setFood_name(rs.getString("food_name"));
					meals.setFood(food);
					
					//새로 생성되는 List<Allerge>
					foodallerge = new ArrayList<>();				
					meals.setFoodallergeList(foodallerge);
					
					//listwithAllerge.add(meals);
					older_date = meals_date;
					older_food_no = food_no;
					
				}			
				
				meals.setMeals_date(meals_date);
				food.setFood_no(food_no);
				food.setFood_name(rs.getString("food_name"));
				meals.setFood(food);
				
				Allerge allerge = new Allerge();
				allerge.setAllerge_no(rs.getInt("allerge_no"));
				FoodAllerge fa = new FoodAllerge( );
				fa.setFood(food);
				fa.setAllerge(allerge);
				
				foodallerge.add(fa);				
				
				listwithAllerge.add(meals);
				
				
			}//while
			
			System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
				//확인용
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		if(listwithAllerge.isEmpty()) {
			try {
				throw new NotFoundException("월간 식단이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return listwithAllerge;*/
			
		
		
	}
	
	
	public List<Meals> selectByDayWithAllerge(String date){		
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Meals> listwithAllerge = new ArrayList<>();
		listwithAllerge = sqlSession.selectList("MealsMapper.selectByDayWithAllerge",date);
		
		return listwithAllerge;
		
/*		
		List<Meals> listwithAllerge = new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			con = MyConnection.getConnection();
			
			List<FoodAllerge> foodallerge=null;
			
			Date older_date = null;
			int older_food_no = 0;
			
			String sql = "SELECT m.meals_date, f.food_no, f.food_name, fa.allerge_no FROM MEALS m" + 
					" left join food f on m.food_no=f.food_no" + 
					" left join FOOD_ALLERGE fa on f.FOOD_NO = fa.FOOD_NO" + 
					" WHERE m.meals_date = ? " + 
					" order by 1,2,4"
					
					; //빠른 날짜->food_no -> allerge_no (null first) 순 정리
					
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, searchdate);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Meals meals = new Meals();
				Food food = new Food();
				Date meals_date = rs.getDate("meals_date");
				int food_no = rs.getInt("food_no");
				
				if(older_food_no!=food_no) {
					//Meals m = new Meals() ;
					//m.setMeals_date(rs.getDate("meals_date"));
					
					meals.setMeals_date(meals_date);					
					
					//food.setFood_no(rs.getInt("food_no"));
					food.setFood_no(food_no);
					food.setFood_name(rs.getString("food_name"));
					meals.setFood(food);
					
					//새로 생성되는 List<Allerge>
					foodallerge = new ArrayList<>();				
					meals.setFoodallergeList(foodallerge);
					
					//listwithAllerge.add(meals);
					older_date = meals_date;
					older_food_no = food_no;
					
				}			
				
				meals.setMeals_date(meals_date);
				food.setFood_no(food_no);
				food.setFood_name(rs.getString("food_name"));
				meals.setFood(food);
				
				Allerge allerge = new Allerge();
				allerge.setAllerge_no(rs.getInt("allerge_no"));
				FoodAllerge fa = new FoodAllerge( );
				fa.setFood(food);
				fa.setAllerge(allerge);
				
				foodallerge.add(fa);				
				
				listwithAllerge.add(meals);
				
				
			}//while			
			System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
				//확인용
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			MyConnection.close(rs,pstmt,  con);
		}
		
		if(listwithAllerge.isEmpty()) {
			try {
				throw new NotFoundException("하루 식단이 없습니다");
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return listwithAllerge;*/
		
		
		
		
		
		
	}
	


	public void insertMeals(List<Meals> meal) throws DuplicatedException {
		SqlSession sqlSession = sqlSessionFatory.openSession();

		try {
			for(Meals m : meal ) {
				sqlSession.insert("MealsMapper.insert", m);
			}
			sqlSession.commit();      
		}catch(Exception e) {			
			e.printStackTrace();
			//sqlSession.rollback();			
		}finally {
			sqlSession.close();
		}

		/*
		Connection con = null;
		PreparedStatement pstmt = null;
		//impleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		//Date date;

		try {
			con = MyConnection.getConnection();
			String sql
				= "insert into meals(meals_date, food_no)"
				+ " values(?,?)"
				;
			for(Meals meals : meal) {
				pstmt = con.prepareStatement(sql);		
				java.util.Date u = meals.getMeals_date();
				java.sql.Date d = new java.sql.Date(u.getTime());
				pstmt.setDate(1, d);
				pstmt.setInt(2, meals.getFood().getFood_no());
				pstmt.executeUpdate();
			}			
			System.out.println("추가성공");//확인용
		} catch (SQLException e) {
			System.out.println("추가실패");//확인용
			e.printStackTrace();
		}finally {
			MyConnection.close(null,pstmt,  con);
		}
		*/
	}
	   
	public void deleteMeals(String meals_date) {
		SqlSession sqlSession = sqlSessionFatory.openSession();
		sqlSession.delete("MealsMapper.delete", meals_date);
		sqlSession.commit();
	
		/*
		Connection con = null;
		PreparedStatement pstmt = null;
		//			SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		//			Date date;

		try {
			con = MyConnection.getConnection();
			String sql= "delete from meals where meals_date = ? ";

			//				java.util.Date u = meals.getMeals_date();
			//				java.sql.Date d = new java.sql.Date(u.getTime());
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, meals_date);					
			pstmt.executeUpdate();

			System.out.println("삭제성공");//확인용
		} catch (SQLException e) {
			System.out.println("삭제실패");//확인용
			e.printStackTrace();
		}finally {
			MyConnection.close(null, pstmt, con);
		}*/
	}
	
	//update는 서비스에서
	
	
	@Override
	 public List<Food> selectAll_Food() {
		
		SqlSession sqlSession = sqlSessionFatory.openSession();
		List<Food> foodlist = new ArrayList<>();
		foodlist = sqlSession.selectList("MealsMapper.selectAll_Food");
		System.out.println("List<Food> selectAll_Food() - return size :"+foodlist.size());
		return foodlist;
		
	     
		/*Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<Food> foodlist = new ArrayList<>();
	      try {
	         con = MyConnection.getConnection();
	         String sql = "select * from food";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            Food food = new Food();
	            food.setFood_no(rs.getInt("food_no"));
	            food.setFood_name(rs.getString("food_name"));
	            food.setFood_cate(rs.getString("food_cate"));
	            food.setIngredient(rs.getString("ingredient"));
	            food.setKcal(rs.getInt("kcal"));
	            foodlist.add(food);
	         }
	         System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         MyConnection.close(rs, pstmt, con);
	      }
	      
	      return foodlist;
	      */
	   }


	   /*@Override
	   public List<Food> findAll_Food_Allerger() {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<Food> foodlist = new ArrayList<>();
	      try {
	         con = MyConnection.getConnection();
	         String sql = "select * from food join food_allerge"
	               + " using (food_no)";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            Food food = new Food();            
	            food.setFood_name(rs.getString("food_name"));
	            food.setFood_cate(rs.getString("food_cate"));
	            food.setIngredient(rs.getString("ingredient"));
	            food.setKcal(rs.getInt("kcal"));
	            FoodAllerge fa = new FoodAllerge();            
	            foodlist.add(food);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return null;
	   }*/


	   @Override
	   public Food selectByNo_Food(int food_no) throws NotFoundException {
	  
		   SqlSession sqlSession = sqlSessionFatory.openSession();
		   Food food = new Food();	
		   food = sqlSession.selectOne("MealsMapper.selectByNo_Food", food_no);
			
		   System.out.println("MealsDAOOracle.selectByNo_Food() : "+food);
		   return food;
		   
		  /*Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;      
	      try {
	         con = MyConnection.getConnection();
	         String sql = "select * from food where food_no=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, food_no);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            Food food = new Food();
	            food.setFood_no(rs.getInt("food_no"));
	            food.setFood_name(rs.getString("food_name"));
	            food.setFood_cate(rs.getString("food_cate"));
	            food.setIngredient(rs.getString("ingredient"));
	            food.setKcal(rs.getInt("kcal"));
	            return food;
	         }
	         System.out.println("검색된 결과 : "+rs.getRow()+"건 조회 완료\n");
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         MyConnection.close( rs, pstmt,con);
	      }      
	      throw new NotFoundException("검색된 음식이 없습니다");
	      */
		   
		   
	   }   //
	
	
}
