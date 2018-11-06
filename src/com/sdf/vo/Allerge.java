package com.sdf.vo;

public class Allerge {


	private int	allerge_no;
	private String allerge_name;
	
	public Allerge() {
		
	}	

	public Allerge(int allerge_no, String allerge_name) {		
		this.allerge_no = allerge_no;
		this.allerge_name = allerge_name;
	}	
	
	public int getAllerge_no() {
		return allerge_no;
	}
	public void setAllerge_no(int allerge_no) {
		this.allerge_no = allerge_no;
	}
	public String getAllerge_name() {
		return allerge_name;
	}
	public void setAllerge_name(String allerge_name) {
		this.allerge_name = allerge_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allerge_no;
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
		Allerge other = (Allerge) obj;
		if (allerge_no != other.allerge_no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Allerge [allerge_no=" + allerge_no + ", allerge_name=" + allerge_name + "]";
	}



}
