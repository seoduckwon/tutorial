package com.sdf.vo;

public class FoodAllerge {
	
	private int seq;				//자동생성이므로 setter 사용 안 해야함
	private Food food;			//food_no만 사용
	private Allerge allerge;		//allerge_no만 사용
	

	public FoodAllerge() {
		
	}
	
	
	public FoodAllerge(int seq, Food food, Allerge allerge) {
		super();
		this.seq = seq;
		this.food = food;
		this.allerge = allerge;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public Food getFood() {
		return food;
	}


	public void setFood(Food food) {
		this.food = food;
	}


	public Allerge getAllerge() {
		return allerge;
	}


	public void setAllerge(Allerge allerge) {
		this.allerge = allerge;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seq;
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
		FoodAllerge other = (FoodAllerge) obj;
		if (seq != other.seq)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "FoodAllerge [seq=" + seq + ", food=" + food + ", allerge=" + allerge + "]";
	}
	

}
