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
import com.madhu.recipe.Service.RecipeService;

/**
 * @author Madhu
 *
 */
@Controller
public class IndexController {

	private final RecipeService recipeService;
	
	

	/**
	 * @param recipeService
	 */
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
	
	@RequestMapping("/fetchAllCategories")
	public void getAllCategories() {
		
	}
}
