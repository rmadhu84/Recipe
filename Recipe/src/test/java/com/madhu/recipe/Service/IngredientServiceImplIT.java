package com.madhu.recipe.Service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhu.recipe.commands.IngredientCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceImplIT {

	@Autowired
	private IngredientService service;
	
	private static final Long RECIPE_ID = 1L;
	private static final Long INGREDIENT_ID = 1L;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindIngredientByRecipeIdAndIngredientId() {
		IngredientCommand command = service.findIngredientByRecipeIdAndIngredientId(RECIPE_ID, INGREDIENT_ID);
		assertNotNull(command);
		
	}
	
	@Test
	public void testFindById() {
		IngredientCommand command = service.findById(INGREDIENT_ID);
		assertNotNull(command);
	}

}
