package com.madhu.recipe.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeSerivce;

	@Mock
	RecipeRepository RecipeRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeSerivce = new RecipeServiceImpl(RecipeRepo);
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		Set<Recipe> recipeData = new HashSet<Recipe>();
		recipeData.add(recipe);

		when(recipeSerivce.getRecipes()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeSerivce.getRecipes();

		assertEquals(recipes.size(), 1);
		verify(RecipeRepo, times(1)).findAll();
	}

}
