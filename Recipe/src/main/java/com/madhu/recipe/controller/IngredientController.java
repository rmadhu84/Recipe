/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.IngredientService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.Service.UnitOfMeasureService;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.commands.RecipeCommand;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

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
	
	private final UnitOfMeasureService uomService;
	
	/**
	 * @param recipeService
	 * @param ingredientService
	 * @param unitOfMeasureService
	 */
	@Autowired
	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = unitOfMeasureService;
	}

	@GetMapping
	@RequestMapping("/{recipeId}/ingredients/")
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
		return "redirect:/recipe/"+recipeId+"/ingredients/";
	}
	
	@GetMapping
	@RequestMapping("/{recipeId}/ingredient/{ingredientId}/show")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		log.debug("Directing to ingredient.show page");
		
		/* First method to fetch Ingredient By ID. Fetches ingredient directly, using given Ingredient ID
		IngredientCommand ingredientCommand = ingredientService.findById(Long.valueOf(ingredientId));
		*/
		
		//Second method to fetch Ingredient by ID - Uses the Recipe to Fetch all Ingredients for recipe and filter by given Ingredient ID 
		IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		model.addAttribute("ingredient", ingredientCommand);
		return "recipe/ingredients/show";
	}
	
	@GetMapping
	@RequestMapping("/{recipeId}/ingredient/{ingredientId}/edit")
	public String editIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		IngredientCommand command = ingredientService.findIngredientByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		Set<UnitOfMeasureCommand> allUoms = uomService.getAllUoms();
		command.getAllUoms().addAll(allUoms);
		model.addAttribute("ingredient", command);
		return "/recipe/ingredients/edit";
	}
	
	@PostMapping
	@RequestMapping("/ingredient/update")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand command) {
		
		return "redirect:/recipe/"+command.getRecipeId()+"/ingredients/";
	}

}
