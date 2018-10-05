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

import com.madhu.recipe.Service.CategoryService;
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
	
	private CategoryService categoryService;

	/**
	 * @param recipeService
	 * @param catergoryService
	 */
	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
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
		
		categoryService.getAllCategories().forEach(category ->{
			command.addCategory(category);
		});
		
		model.addAttribute("recipe", command);
		
		return "recipe/recipeform";
	}
	
	@PostMapping("/")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		System.out.println("Redirecting to Index Page.. YAY !!!" + command.getDifficulty());
		
		command.getSelectedCategories().forEach(category ->{
			System.out.println(category.toString());
			System.out.println(categoryService.getCategoryByName(category));
		});
		
		command = recipeService.saveRecipe(command);
		return "redirect:/recipe/show/" + command.getId();
	}
}
