package com.sdf.dao;

import java.util.List;


import com.sdf.vo.Allerge;

public interface AllergeDAO {

	
	//void insert(Allerge a);
	List<Allerge> selectAll();
	
	
	Allerge selectByNo(int allerge_no); //throws NotFoundException;
	Allerge selectByName(String allerge_name);

	//void update(Allerge a);
	//void delete(int Allerge_no);
}
