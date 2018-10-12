package com.madhu.recipe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.madhu.recipe.Service.CategoryService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.RecipeCommand;

public class RecipeControllerTest {

	RecipeController rc;

	@Mock
	RecipeService recipeService;

	@Mock
	CategoryService categoryService;

	@Mock
	HttpSession session;

	@Mock
	HttpServletRequest request;

	MockMvc mvc;

	private static final String STRING_VALUE = "Any String";
	private static final Long LONG_VALUE = 1L;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		rc = new RecipeController(recipeService, categoryService, session, request);
		mvc = MockMvcBuilders.standaloneSetup(rc).build();
	}

	@Test
	public void testGetRecipeById() throws Exception {
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(1L);
		when(recipeService.getRecipesById(Mockito.anyLong())).thenReturn(recipe);
		mvc.perform(get("/recipe/1/show/")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));

	}

	@Test
	public void testGetNewRecipeForm() throws Exception {
		// given
		CategoryCommand category = new CategoryCommand();
		category.setId(LONG_VALUE);
		category.setCategoryName(STRING_VALUE);
		Set<CategoryCommand> catSet = new HashSet<CategoryCommand>();
		catSet.add(category);

		// when
		when(session.getAttribute(Mockito.anyString())).thenReturn(catSet);

		// then
		mvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetEditRecipeFrom() throws Exception {
		// given
		CategoryCommand category = new CategoryCommand();
		RecipeCommand recipe = new RecipeCommand();
		category.setId(LONG_VALUE);
		category.setCategoryName(STRING_VALUE);
		Set<CategoryCommand> catSet = new HashSet<CategoryCommand>();
		catSet.add(category);

		// when
		when(session.getAttribute(Mockito.anyString())).thenReturn(catSet);
		when(recipeService.getRecipesByIdForEdit(LONG_VALUE, catSet)).thenReturn(recipe);

		// then
		mvc.perform(get("/recipe/1/edit/")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));

	}

	@Test
	public void testPostNewRecipeForm() throws Exception {
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(2L);
		when(recipeService.saveRecipe(any())).thenReturn(recipe);

		mvc.perform(post("/recipe/").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
				.param("description", "some string")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show/"));

	}

}