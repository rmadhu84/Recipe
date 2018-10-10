/**
 * 
 */
package com.madhu.recipe.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.Repositories.UnitOfMeasureRepository;;

/**
 * @author ramachandranm1
 *
 */
@Configuration
public class RecipeConfiguration {
	
	private final UnitOfMeasureRepository uomRepo;
	private final CategoryRepository categoryRepo;
	
	/**
	 * @param uomRepo
	 * @param categoryRepo
	 */
	public RecipeConfiguration(UnitOfMeasureRepository uomRepo, CategoryRepository categoryRepo) {
		this.uomRepo = uomRepo;
		this.categoryRepo = categoryRepo;
	}

	@Bean
	public HashMap<String, String> getTestMap1(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "Apple");
		return (HashMap<String, String>) map;		
	}
	
	@Bean
	public HashMap<String, String> getTestMap2(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("b", "Basket");
		return (HashMap<String, String>) map;		
	}
	
	@Bean
	public HashMap<String, UnitOfMeasure> getUomMap(){
		HashMap<String, UnitOfMeasure> map = new HashMap<String, UnitOfMeasure>();
		uomRepo.findAll().forEach(uom ->{
			map.put(uom.getDescription(), uom);
		});
		return map;
	}
	
	@Bean
	
	public HashMap<String, Category> getCategoryMap(){
		HashMap<String, Category> map = new HashMap<String, Category>();
		
		categoryRepo.findAll().forEach(category -> {
			map.put(category.getCategoryName(), category);
		});
		
		return map;
	}

}
