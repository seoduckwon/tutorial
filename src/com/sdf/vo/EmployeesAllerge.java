package com.sdf.vo;

public class EmployeesAllerge {

	private int seq;
	private Employees employees; //String id;
	private Allerge allerge; //int allerge_no;
	public EmployeesAllerge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeesAllerge(int seq, Employees employees, Allerge allerge) {
		super();
		this.seq = seq;
		this.employees = employees;
		this.allerge = allerge;
	}
	
	
	@Override
	public String toString() {
		return "EmployeesAllerge [seq=" + seq + ", employees=" + employees + ", allerge=" + allerge + "]";
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
		EmployeesAllerge other = (EmployeesAllerge) obj;
		if (seq != other.seq)
			return false;
		return true;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	public Allerge getAllerge() {
		return allerge;
	}
	public void setAllerge(Allerge allerge) {
		this.allerge = allerge;
	}
	
	
	
}
