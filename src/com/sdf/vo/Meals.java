package com.sdf.vo;

import java.util.Date;
import java.util.List;

public class Meals {	

	private Date meals_date; 
	private Food food; //food_no
	
	private List<FoodAllerge> foodallergeList;		//allerge_no 연결을 위해서
	
	public Meals() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Meals(Date meals_date, Food food, List<FoodAllerge> foodallergeList) {
		super();
		this.meals_date = meals_date;
		this.food = food;
		this.foodallergeList = foodallergeList;
	}

	public Date getMeals_date() {
		return meals_date;
	}
	public void setMeals_date(Date meals_date) {
		this.meals_date = meals_date;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}

	public List<FoodAllerge> getFoodallergeList() {
		return foodallergeList;
	}

	public void setFoodallergeList(List<FoodAllerge> foodallergeList) {
		this.foodallergeList = foodallergeList;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((meals_date == null) ? 0 : meals_date.hashCode());
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
		Meals other = (Meals) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (meals_date == null) {
			if (other.meals_date != null)
				return false;
		} else if (!meals_date.equals(other.meals_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meals [meals_date=" + meals_date + ", food=" + food + ", foodallergeList=" + foodallergeList + "]";
	}



}