package com.madhu.recipe.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	
	@Test
	public void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeoptional = Optional.of(recipe);
		
		when(RecipeRepo.findById(Mockito.anyLong())).thenReturn(recipeoptional);
		//when(recipeSerivce.getRecipesById(Mockito.anyLong())).thenReturn(recipeoptional);
		
		Recipe recipeOutput = recipeSerivce.getRecipesById(1L);
		
		assertNotNull("Null Object Returned", recipeOutput);
		verify(RecipeRepo, times(1)).findById(1L);
		verify(RecipeRepo, never()).findAll();
				
				
	}
}
