package com.sdf.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.sdf.dao.MealsDAO;
import com.sdf.exception.DuplicatedException;
import com.sdf.exception.NotFoundException;
import com.sdf.vo.Food;
import com.sdf.vo.FoodAllerge;
import com.sdf.vo.Meals;

public class MealsService {
	
	private static MealsService service;
	private static String propertiesFileName;
	private MealsDAO dao;
	private MealsService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("service.properties"));
			env.load(new FileInputStream(propertiesFileName));		//리스너 만든 후
			String className = env.getProperty("mealsdao");		//dao.customer 처럼 . 을 써도 됨.
			dao = (MealsDAO)Class.forName(className).newInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static String getPropertiesFileName() {
		return propertiesFileName;
	}



	public static void setPropertiesFileName(String propertiesFileName) {
		MealsService.propertiesFileName = propertiesFileName;
	}



	public static synchronized MealsService getInstance() {
		if(service == null)
			service = new MealsService();
		return service;
	}
	
	
	/**
	 * @param date 월을 날짜로 받은경우, 날짜만 뽑아서 dao에 넘겨줌
	 * @return 월간 식단
	 */
	public Collection<Meals> findByMonth(Date date){
		
		return dao.selectByMonth(date.getMonth()+1);		
	}
	
	/**
	 * @param date 월을 숫자로 넘겨받은 경우, 바로 dao로 넘겨줌
	 * @return 월간 식단
	 */
	public Collection<Meals> findByMonth(int date){
		
		return dao.selectByMonth(date+1);		
	}
	
	
	/**
	 * @param date 만약 jsp나 servlet 등 윗단에서 Date타입으로 넘겨준경우, 형변환 후 dao로 넘겨줌
	 * @return 하루 식단
	 */
	public Collection<Meals> findByDay(Date date){
		//
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd"); 
		String strDate = transFormat.format(date);
		//System.out.println(strDate+"\n");
		
		return dao.selectByDay(strDate);
	}

	/**
	 * @param date 만약 jsp나 servlet 등 윗단에서 문자열타입으로 넘겨준경우, 바로 dao로 넘겨줌
	 * @return
	 */
	public Collection<Meals> findByDay(String date){
		
		return dao.selectByDay(date);
	}
	
	
	/**
	 * 주간 식단 검색
	 * @param week 검색할 주차수
	 * @return 검색한 주차의 일주일 식단
	 */
	public List<Meals> findByWeek(int week){
		
		return dao.selectByWeek(week);
	}
	
	
	/**
	 * @return  Select All _ FoodAllerge Table (food_no, allerge_no)
	 */
	public List<FoodAllerge> findAllFoodAllerge(){		
		return dao.selectAllFoodAllerge();		
	}
	
	
	
	/**
	 * @param date 검색할 월
	 * @return 월간 식단 + 알러지 여부( Meals.foodallergeList.getAllerge().getAllergeNo() ==0 인경우, 알러지가 없음). size==0하면 안됨
	 * 
	 */
	public List<Meals> findByMonthWithAllerge(Date date){
		return dao.selectByMonthWithAllerge(date.getMonth()+1);
	}
	
	/**
	 * @param date 검색할 월
	 * @return 월간 식단 + 알러지 여부( Meals.foodallergeList.getAllerge().getAllergeNo() ==0 인경우, 알러지가 없음). size==0하면 안됨
	 */
	public List<Meals> findByMonthWithAllerge(int date){
		
		return dao.selectByMonth(date+1);		
	}
	
	
	
	/**
	 * @param date 검색할 일(날짜)
	 * @return 하루 식단 + 알러지 여부( Meals.foodallergeList.getAllerge().getAllergeNo() ==0 인경우, 알러지가 없음). size==0하면 안됨
	 */
	public List<Meals> findByDayWithAllerge(Date date){
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd"); 
		String strDate = transFormat.format(date);		
		
		return dao.selectByDayWithAllerge(strDate);
		
	}
	
	
	/**
	 * @param date검색할 일(날짜)
	 * @return 하루 식단 + 알러지 여부( Meals.foodallergeList.getAllerge().getAllergeNo() ==0 인경우, 알러지가 없음). size==0하면 안됨
	 */
	public List<Meals> findByDayWithAllerge(String date){

		return dao.selectByDayWithAllerge(date);
	}
	

	/**
	 * @param meal
	 * @throws DuplicatedException
	 */
	public void addMeals(List<Meals> meal) throws DuplicatedException{
		dao.insertMeals(meal);
	}
	
	
	/**
	 * @param date 날짜 타입 -> String
	 */
	public void deleteMeals(Date date){
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd"); 
		String strDate = transFormat.format(date);
		dao.deleteMeals(strDate);
	}
	
	public void deleteMeals(String date){
	
		dao.deleteMeals(date);
	}
	
	
	/**
	 * @param date
	 * @param meal
	 * @throws DuplicatedException
	 */
	public void updateMeals(Date date, List<Meals> meal) throws DuplicatedException{
		Collection<Meals> list = MealsService.getInstance().findByDay(date);
		if(list != null) {
			MealsService.getInstance().deleteMeals(date);
		}
		MealsService.getInstance().addMeals(meal);
	}
	
	
	/**
	 * @return
	 */
	public Collection<Food> findAll_Food(){
		return dao.selectAll_Food();
	}

	
	/**
	 * @param food_no
	 * @return
	 * @throws NotFoundException
	 */
	public Food findByNoFood(int food_no) throws NotFoundException {
		return dao.selectByNo_Food(food_no);
	}
	
	
}
