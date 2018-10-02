package com.madhu.recipe.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhu.recipe.commands.RecipeCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplIT {
	
	private static final Long LONG_VALUE = 1L;
	private static final String NEW_DESCRIPTION = "New Recipe Description";
	
	@Autowired
	private RecipeService service;

	@Test
	@Transactional
	public void testSaveRecipe() {
		//given
		RecipeCommand testRecipe = service.getRecipesById(LONG_VALUE);
		testRecipe.setDescription(NEW_DESCRIPTION);
		
		//when
		RecipeCommand savedRecipe = service.saveRecipe(testRecipe);
				
		//then
		assertNotNull(savedRecipe);
		assertEquals(testRecipe.getId(), savedRecipe.getId());
		assertEquals(NEW_DESCRIPTION, savedRecipe.getDescription());
		assertEquals(testRecipe.getCategories().size(), savedRecipe.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipe.getIngredients().size());
	}

}
