package com.madhu.recipe.controller;

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

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		rc = new RecipeController(recipeService, categoryService);
	}

	@Test
	public void testGetRecipeById() throws Exception {
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(1L);

		MockMvc mvc = MockMvcBuilders.standaloneSetup(rc).build();

		when(recipeService.getRecipesById(Mockito.anyLong())).thenReturn(recipe);

		mvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));

	}
}