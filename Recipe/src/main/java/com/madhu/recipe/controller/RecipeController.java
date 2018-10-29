/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madhu.recipe.Service.CategoryService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.CategoryCommand;
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

	private HttpSession session;

	private HttpServletRequest request;

	/**
	 * @param recipeService
	 * @param catergoryService
	 */
	public RecipeController(RecipeService recipeService, CategoryService categoryService, HttpSession session,
			HttpServletRequest request) {

		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.request = request;
		this.session = session;

	}
	
	@GetMapping
	@RequestMapping("/{id}/show/")
	public String getRecipeById(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipesById(new Long(id)));
		return "recipe/show";

	}

	@SuppressWarnings("unchecked")
	@GetMapping
	@RequestMapping("/new")
	public String createRecipe(Model model) {

		log.info("Create new Recipe ...");
		RecipeCommand command = new RecipeCommand();

		Set<CategoryCommand> category = (Set<CategoryCommand>) session.getAttribute("CategorySet");
		category.forEach(c -> {
			command.addCategory(c);
		});

		model.addAttribute("recipe", command);

		return "recipe/recipeform";

	}

	@SuppressWarnings("unchecked")
	@GetMapping
	@RequestMapping("/{id}/edit/")
	public String editRecipe(@PathVariable String id, Model model) {

		log.info("Edit Recipe ...");
		
		Set<CategoryCommand> categories = (Set<CategoryCommand>) session.getAttribute("CategorySet");
		RecipeCommand command = recipeService.getRecipesByIdForEdit(Long.valueOf(id), categories);
		model.addAttribute("recipe", command);
		return "recipe/recipeform";

	}

	@PostMapping
	@RequestMapping("/")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {

		log.info("*** Saving recipe *** " + command.getId());
		command = recipeService.saveRecipe(command);
		log.info("*** Redirecting to Home Page *** ");
		return "redirect:/recipe/" + command.getId() + "/show/";

	}

	@GetMapping
	@RequestMapping("/{id}/delete/")
	public String deleteRecipe(@PathVariable String id) {

		log.info("*** Deleting Recipe *** " + id);
		recipeService.deleteRecipeById(new Long(id));
		return "redirect:/index";

	}

}
