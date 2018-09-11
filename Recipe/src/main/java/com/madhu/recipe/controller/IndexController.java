/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.RecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Controller
@Slf4j
public class IndexController {

	private final RecipeService recipeService;

	// map1 and map2 are examples to wiring a HashMap Bean from Application context
	private final HashMap<String, String> map1;
	private final HashMap<String, String> map2;
	
	
	
	/**
	 * @param recipeService
	 * @param map1
	 * @param map2
	 */
	public IndexController(RecipeService recipeService, @Qualifier("getTestMap1") HashMap<String, String> map1, @Qualifier("getTestMap2") HashMap<String, String> map2) {
		this.recipeService = recipeService;
		this.map1 = map1;
		this.map2 = map2;
	}

	/**
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage(Model model) {
		log.debug("Loading index page");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
