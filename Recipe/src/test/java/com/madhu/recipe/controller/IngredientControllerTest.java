package com.madhu.recipe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.madhu.recipe.Service.IngredientService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.Service.UnitOfMeasureService;

public class IngredientControllerTest {

	IngredientController ic;

	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;

	private static final Long LONG_VALUE = 1L;


	MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ic = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
		mvc = MockMvcBuilders.standaloneSetup(ic).build();
	}

	@Test
	public void testListIngredients() throws Exception {
		mvc.perform(get("/recipe/" + LONG_VALUE + "/ingredients/")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredients/list"));
	}

}
