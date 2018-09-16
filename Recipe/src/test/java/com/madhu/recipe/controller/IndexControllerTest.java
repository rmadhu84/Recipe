package com.madhu.recipe.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.madhu.recipe.Service.RecipeService;

public class IndexControllerTest {
	
	IndexController ic;

	@Mock
	Model model;
	
	@Mock
	RecipeService  recipeService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ic = new IndexController(recipeService);
	}

	@Test
	public void testGetIndexPage() {
		final String getRecipeOutput = "index";
		assertEquals(ic.getIndexPage(model), getRecipeOutput);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
		
	}

}
