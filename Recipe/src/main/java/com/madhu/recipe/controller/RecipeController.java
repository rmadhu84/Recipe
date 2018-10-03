/**
 * 
 */
package com.madhu.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.RecipeCommand;

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
	
	@RequestMapping("/new")
	public String createOrEditRecipe(Model model) {
		System.out.println("Action call works.. YAY !!!");
		log.info("Create new Recipe ...");
		RecipeCommand command = new RecipeCommand();
		//command.setCookTime(10);
		model.addAttribute("recipe", command);
		
		return "recipe/recipeform";
	}
	
	@PostMapping("/")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		System.out.println("Redirecting to Index Page.. YAY !!!" + command.getDifficulty());
		command = recipeService.saveRecipe(command);
		return "redirect:/recipe/show/" + command.getId();
	}
}
