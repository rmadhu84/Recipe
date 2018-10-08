package com.madhu.recipe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.madhu.recipe.Service.CategoryService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.commands.RecipeCommand;

public class RecipeControllerTest {

	RecipeController rc;

	@Mock
	RecipeService recipeService;
	
	@Mock
	CategoryService categoryService;
	
	MockMvc mvc ;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rc = new RecipeController(recipeService, categoryService);
		mvc  = MockMvcBuilders.standaloneSetup(rc).build();
	}

	@Test
	public void testGetRecipeById() throws Exception {
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(1L);

		

		when(recipeService.getRecipesById(Mockito.anyLong())).thenReturn(recipe);

		mvc.perform(get("/recipe/1/show/")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));

	}
	
	public void testGetNewRecipeForm() throws Exception{
		RecipeCommand command = new RecipeCommand();
		
		mvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testPostNewRecipeForm() throws Exception{
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(2L);
		when(recipeService.saveRecipe(any())).thenReturn(recipe);
		
		
	}
}