/**
 * 
 */
package com.madhu.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.IngredientService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.RecipeCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ramachandranm1
 *
 */
@Controller
@Slf4j
@RequestMapping("/recipe")
public class IngredientController {
	
	private final RecipeService recipeService;
	
	private final IngredientService ingredientService;
	
	/**
	 * @param recipeService
	 * @param ingredientService
	 */
	@Autowired
	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	@RequestMapping("/{recipeId}/ingredients/list")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		log.debug("Getting Ingredients for Recipe: " + recipeId);
		RecipeCommand command = recipeService.getRecipesById(Long.valueOf(recipeId));
		model.addAttribute("recipe", command);
		return "recipe/ingredients/list";
		
	}
	
	@GetMapping
	@RequestMapping("/{recipeId}/ingredient/{ingredientId}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
		log.debug("Deleting Ingredient");
		ingredientService.deleteIngredientById(Long.valueOf(ingredientId));
		return "redirect:/recipe/"+recipeId+"/ingredients/list";
	}

}
