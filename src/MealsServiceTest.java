import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sdf.exception.NotFoundException;
import com.sdf.service.MealsService;
import com.sdf.vo.Food;
import com.sdf.vo.FoodAllerge;
import com.sdf.vo.Meals;

public class MealsServiceTest {

	public static void main(String[] args) {
		/*System.out.println("[[[[[[[[[[[[[[[PRODUCT]]]]]]]]]]]]]]]]]]]]]");	
		
		*/
		String s = "18/09/16";
		SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		Date date;
		try { 
			date = df.parse(s);
			/*System.out.println(date.getDate()); //일
			System.out.println(date.getMonth()+1);
			*/
			MealsService mealsService = MealsService.getInstance();
			
			
			Collection<Meals> all;
		
			/*	all = mealsService.findByMonth(date);
				System.out.println(all.size());
			for(Meals m : all) {
				System.out.println(m);
			}
			
			*/
		
			/*all = mealsService.findByDay(date);
			
			for(Meals m : all) {
				System.out.println(m);
			}*/
		
			/*System.out.println(date.toString()
					+"\n"+date.toGMTString()
					+"\n"+date.toLocaleString() );*/
			System.out.println("================================");
			/*List<FoodAllerge> list = mealsService.findAllFoodAllerge();
			
			for(FoodAllerge fa : list){
				System.out.println("AllergeNo : "+fa.getAllerge().getAllerge_no()
						+", "+fa.getFood());
			}*/
			
			
			/*Map<Meals, Integer> map = mealsService.findByMonthWithAllerge(date);
			
			for(int i=0; i<map.size(); i++) {
				for(Meals m : map.keySet()) {
					int value = map.get(m);
					if(value ==0) {
						System.out.println("알러지 없음"+ "\t"+m );
					}					
					else{
						System.out.println(value + "\t" +m);
					}
				}	
			}*/
			s = "18/09/16";
			date = df.parse(s);
			
			/*	List<Meals> map = mealsService.findByMonthWithAllerge(date);
			
						
			for(Meals meals : map) {
			//	if(meals.getMeals_date() != null && meals.getFood() !=null)
					System.out.println(meals);
			}	
			*/

				
			
			/*
			map = mealsService.findByDayWithAllerge(date);
			
			for(Meals meals : map) {
				//if(meals.getMeals_date() != null && meals.getFood() !=null)
					System.out.println(meals);
			}	
		*/
			
			/*
			-week Test data-
			8/20 : 34 주차
			9/17 : 38주차
			*/
			/*
			List<Meals> map = mealsService.findByWeek(35);
			for(Meals meals : map) {				
					System.out.println(meals);
			}
			*/
			
			/*///////////update ///////////////////
			List<Meals> list = new ArrayList<>();
			//test?
			Meals meals = new Meals();
			meals.setMeals_date(date);
			Food food = new Food();
			food.setFood_no(3);
			meals.setFood(food);
			list.add(meals);
			
			//mealsService.addMeals(list);
			
			mealsService.updateMeals(date, list);		*/
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//food 테스트
		MealsService mealsService = MealsService.getInstance();
		
		/*	Collection<Food> foodall;
        foodall = mealsService.findAll_Food();
        for(Food f : foodall) {
           System.out.println(f);
        }
        
        */
		
		
		
        Food food;
        int food_no = 1;
        try {
			food = mealsService.findByNoFood(food_no);
			System.out.println(food);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/**/
		

	}
/*
 2. String -> Date 변환
String strTime = "2017-11-13 21:40:15"; 
SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
Date d2 = transFormat.parse(strTime); 

3. Date -> String 변환
Date d3 = new Date(); 
SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
strTime = transFormat.format(d3);

getMonth() : 월 (주의!! 0이 1월이다)
getDate() : 일 
getDay() : 요일. (1이 월요일이다)

*/
}
