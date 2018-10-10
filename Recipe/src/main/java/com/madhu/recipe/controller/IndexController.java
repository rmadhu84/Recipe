/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.madhu.recipe.Service.CategoryService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.CategoryCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Controller
@SessionAttributes("CategorySet")
@Slf4j
public class IndexController {

	private final RecipeService recipeService;
	private final CategoryService categoryService;

	// map1 and map2 are examples to wiring a HashMap Bean from Application context
//	private final HashMap<String, String> map1;
//	private final HashMap<String, String> map2;
	
	
	
	/**
	 * @param recipeService
	 * @param map1
	 * @param map2
	 */
	public IndexController(RecipeService recipeService, CategoryService categoryService) {//, @Qualifier("getTestMap1") HashMap<String, String> map1, @Qualifier("getTestMap2") HashMap<String, String> map2) {
		this.recipeService = recipeService;
		this.categoryService = categoryService;
	//	this.map1 = map1;
	//	this.map2 = map2;
	}

	/**
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage(Model model) {
		log.info("Loading index page");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
	
	@ModelAttribute("CategorySet")
	public Set<CategoryCommand> getAllCategories() {
		return categoryService.getAllCategories();

	}

}
