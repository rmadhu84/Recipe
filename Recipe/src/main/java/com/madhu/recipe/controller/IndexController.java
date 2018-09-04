/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.Repositories.UnitOfMeasureRepository;

/**
 * @author Madhu
 *
 */
@Controller
public class IndexController {

	public CategoryRepository catRepo;
	public UnitOfMeasureRepository uomRepo;
	
	
	/**
	 * @param catRepo
	 * @param uomRepo
	 */
	@Autowired
	public IndexController(CategoryRepository catRepo, UnitOfMeasureRepository uomRepo) {
		super();
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
	}

	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage() {
		
		Optional<Category> category = catRepo.findByCategoryName("American");
		Optional<UnitOfMeasure> uom = uomRepo.findByDescription("Teaspoon");
		
		System.out.println("Category ID: " + category.get().getId());
		System.out.println("Unit of Measure ID: " + uom.get().getId());
		return "index";
	}
	
	@RequestMapping("/fetchAllCategories")
	public void getAllCategories() {
		
	}
}
