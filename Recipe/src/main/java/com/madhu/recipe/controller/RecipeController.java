/**
 * 
 */
package com.madhu.recipe.controller;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/**
	 * @param recipeService
	 * @param catergoryService
	 */
	public RecipeController(RecipeService recipeService, CategoryService categoryService) {

		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;

	}

	@ModelAttribute("CategorySet")
	public Set<CategoryCommand> getAllCategories() {
		return categoryService.getAllCategories();

	}

	@RequestMapping("/{id}/show/")
	public String getRecipeById(@PathVariable String id, Model model) {

		model.addAttribute("recipe", recipeService.getRecipesById(new Long(id)));
		return "recipe/show";

	}

	@RequestMapping("/new1")
	public String createRecipe(Model model, HttpServletRequest request, HttpSession session) {

		log.info("Create new Recipe ...");
		RecipeCommand command = new RecipeCommand();

		categoryService.getAllCategories().forEach(category -> {
			command.addCategory(category);
		});
		System.out.println("Inside of dosomething handler method");

		System.out.println("*** Session data ***");
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
			System.out.println("**" + session.getAttribute(s));
		}

		model.addAttribute("recipe", command);

		return "recipe/recipeform";

	}

	@RequestMapping("/{id}/edit/")
	public String editRecipe(@PathVariable String id, Model model) {

		log.info("Edit Recipe ...");
		RecipeCommand command = recipeService.getRecipesByIdForEdit(Long.valueOf(id));
		model.addAttribute("recipe", command);
		return "recipe/recipeform";

	}

	@PostMapping("/")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {

		log.info("*** Saving recipe *** " + command.getId());
		command = recipeService.saveRecipe(command);
		log.info("*** Redirecting to Home Page *** ");
		return "redirect:/recipe/" + command.getId() + "/show/";

	}

	@RequestMapping("/{id}/delete/")
	public String deleteRecipe(@PathVariable String id) {

		log.info("*** Deleting Recipe *** " + id);
		recipeService.deleteRecipeById(new Long(id));
		return "redirect:/index";

	}

	@RequestMapping("/new")
	public String loosuMethod(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("Inside of dosomething handler method");

		System.out.println("*** Session data ***");
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
			System.out.println("**" + session.getAttribute(s));
		}

		return "recipe/recipeform";
	}

}
