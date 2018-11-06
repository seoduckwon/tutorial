package com.sdf.dao;

import java.util.List;

import com.sdf.exception.DuplicatedException;
import com.sdf.exception.NotFoundException;
import com.sdf.vo.Food;
import com.sdf.vo.FoodAllerge;
import com.sdf.vo.Meals;

public interface MealsDAO {
	
	//Alt + Shift + J 
	/**
	 * 월간 식단 검색
	 * @param month 검색할 월
	 * @return 월간 식단 리스트
	 */
	List<Meals> selectByMonth(int month);
	

	/**
	 * 하루의 식단 검색
	 * @param day 검색할 날짜
	 * @return 하루 음식들
	 */
	List<Meals> selectByDay(String date);
	
	
	/**
	 * @param week 주차수 입력
	 * @return 해당 주차수 월-금 식단반환
	 */
	List<Meals>  selectByWeek(int week);

	
	
	/**
	 * @return 알러지가 포함된 음식들 이름으로 나열
	 */
	List<FoodAllerge> selectAllFoodAllerge();
	
	
	
	
	/** 알러지 포함여부를 알 수 있는 월간 식단 검색
	 * @param month 검색할 월
	 * @return 월간 식단 + 알러지여부(allerge_no 컬럼 추가)
	 */
	List<Meals> selectByMonthWithAllerge(int month);
	
	
	
	/** 알러지 포함여부를 알 수 있는 하루 식단 검색
	 * @param date 검색할 일(날짜)
	 * @return 하루식단 + 알러지여부(allerge_no 컬럼 추가)
	 */
	List<Meals> selectByDayWithAllerge(String date);
	
	
	/**
	 * @param meal 
	 * @throws DuplicatedException 이미 있는 Meals면 exception -> rollback
	 */
	void insertMeals(List<Meals> meal) throws DuplicatedException;
	
	/**
	 * @param date 해당일자 Meals 모두 삭제
	 */
	void deleteMeals(String date);
	
	
	/**
	 * @return 모든 음식
	 */
	List<Food> selectAll_Food();   
	
	Food selectByNo_Food(int food_no) throws NotFoundException;
	
}
