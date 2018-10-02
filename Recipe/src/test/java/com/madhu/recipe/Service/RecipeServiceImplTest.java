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

import com.madhu.recipe.Model.Note;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Repositories.RecipeRepository;
import com.madhu.recipe.commands.RecipeCommand;
import com.madhu.recipe.converters.CategoryCmdToMdlConverter;
import com.madhu.recipe.converters.CategoryMdlToCmdConverter;
import com.madhu.recipe.converters.IngredientCmdToMdlConverter;
import com.madhu.recipe.converters.IngredientMdlToCmdConverter;
import com.madhu.recipe.converters.NoteCmdToMdlConverter;
import com.madhu.recipe.converters.NoteMdlToCmdConverter;
import com.madhu.recipe.converters.RecipeCmdToMdlConverter;
import com.madhu.recipe.converters.RecipeMdlToCmdConverter;
import com.madhu.recipe.converters.UnitOfMeasureCmdToMdlConverter;
import com.madhu.recipe.converters.UnitOfMeasureMdlToCmdConverter;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeSerivce;

	RecipeCmdToMdlConverter toMdlConverter;

	RecipeMdlToCmdConverter toCmdConverter;

	@Mock
	RecipeRepository RecipeRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		toMdlConverter = new RecipeCmdToMdlConverter(new CategoryCmdToMdlConverter(), new NoteCmdToMdlConverter(),
				new IngredientCmdToMdlConverter(new UnitOfMeasureCmdToMdlConverter()));

		toCmdConverter = new RecipeMdlToCmdConverter(new CategoryMdlToCmdConverter(), new NoteMdlToCmdConverter(),
				new IngredientMdlToCmdConverter(new UnitOfMeasureMdlToCmdConverter()));

		recipeSerivce = new RecipeServiceImpl(RecipeRepo, toMdlConverter, toCmdConverter);
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		Set<Recipe> recipeData = new HashSet<Recipe>();
		recipeData.add(recipe);

		//when(recipeSerivce.getRecipes()).thenReturn(recipeData);
		when(RecipeRepo.findAll()).thenReturn(recipeData);
		Set<RecipeCommand> recipes = recipeSerivce.getRecipes();

		assertEquals(recipes.size(), 1);
		verify(RecipeRepo, times(1)).findAll();
	}

	@Test
	public void getRecipeByIdTest() {
		
		Note note = new Note();
		note.setId(1L);
		
		Recipe recipe = new Recipe();
		
		recipe.setId(1L);
		recipe.setNote(note);
		
		Optional<Recipe> recipeoptional = Optional.of(recipe);

		when(RecipeRepo.findById(Mockito.anyLong())).thenReturn(recipeoptional);
		// when(recipeSerivce.getRecipesById(Mockito.anyLong())).thenReturn(recipeoptional);

		Recipe recipeOutput = toMdlConverter.convert(recipeSerivce.getRecipesById(1L));

		assertNotNull("Null Object Returned", recipeOutput);
		verify(RecipeRepo, times(1)).findById(1L);
		verify(RecipeRepo, never()).findAll();

	}
}
