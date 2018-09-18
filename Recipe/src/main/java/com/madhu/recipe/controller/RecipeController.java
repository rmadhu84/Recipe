/**
 * 
 */
package com.madhu.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.RecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

	private RecipeService recipeService;

	/**
	 * @param recipeService
	 */
	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	 
	@RequestMapping("/show/{id}")
	public String getRecipeById(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe",recipeService.getRecipesById(new Long(id)));
		return "recipe/show";
	}
}
