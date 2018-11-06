package com.sdf.vo; //ValueObject

public class Food {
	private int food_no;
	private String food_name;
	private String food_cate;
	private String ingredient;
	private int kcal;
	
	public Food() {
		super();
	}	
	
	public Food(int food_no, String food_name, String food_cate, String ingredient, int kcal) {
		super();		 
		this.food_no = food_no;
		this.food_name = food_name;
		this.food_cate = food_cate;
		this.ingredient = ingredient;
		this.kcal = kcal;
	}	

	@Override
	public String toString() {
		return "Food [food_no=" + food_no + ", food_name=" + food_name + ", food_cate=" + food_cate + ", ingredient="
				+ ingredient + ", kcal=" + kcal + "]";
	}
	public void print() {
		System.out.println(this); //this.toString()
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + food_no;
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
		Food other = (Food) obj;
		if (food_no != other.food_no)
			return false;
		return true;
	}
	public int getFood_no() {
		return food_no;
	}
	public void setFood_no(int food_no) {
		this.food_no = food_no;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_cate() {
		return food_cate;
	}
	public void setFood_cate(String food_cate) {
		this.food_cate = food_cate;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	
}
	
	


