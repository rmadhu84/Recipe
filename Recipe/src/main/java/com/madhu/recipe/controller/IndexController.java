/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.Repositories.RecipeRepository;
import com.madhu.recipe.Repositories.UnitOfMeasureRepository;

/**
 * @author Madhu
 *
 */
@Controller
public class IndexController {

	public CategoryRepository catRepo;
	public UnitOfMeasureRepository uomRepo;
	public RecipeRepository recipeRepo;

	/**
	 * @param catRepo
	 * @param uomRepo
	 * @param recipeRepo
	 */
	@Autowired
	public IndexController(CategoryRepository catRepo, UnitOfMeasureRepository uomRepo, RecipeRepository recipeRepo) {
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
		this.recipeRepo = recipeRepo;
	}

	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeRepo.findAll());
		return "index";
	}
	
	@RequestMapping("/fetchAllCategories")
	public void getAllCategories() {
		
	}
}
