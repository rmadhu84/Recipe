package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.commands.NoteCommand;
import com.madhu.recipe.commands.RecipeCommand;

public class RecipeCmdToMdlConverterTest {

	RecipeCmdToMdlConverter converter;
	CategoryCmdToMdlConverter categoryConverter;
	NoteCmdToMdlConverter noteConverter;
	IngredientCmdToMdlConverter ingredientConverter;
	UnitOfMeasureCmdToMdlConverter uomConverter;

	private static final Long LONG_VALUE = 1L;
	private static final String ANY_STRING = "Any String";
	
	NoteCommand note = new NoteCommand();
	
	CategoryCommand category = new CategoryCommand();
	
	IngredientCommand ingredient = new IngredientCommand(); 
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		ingredientConverter = new IngredientCmdToMdlConverter(uomConverter);

		converter = new RecipeCmdToMdlConverter(categoryConverter, noteConverter, ingredientConverter);
	}

	@Test
	public void test() {
		//given
		RecipeCommand command = new RecipeCommand();
		command.setId(LONG_VALUE);
		command.setDescription(ANY_STRING);
		command.getCategories().add(category);
		command.getIngredients().add(ingredient);
		note.setId(LONG_VALUE);
		note.setRecipteNotes(ANY_STRING);
		command.setNote(note);
	
		//when
		try {
		Recipe model = converter.convert(command);
		//then
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(ANY_STRING, model.getDescription());
		assertEquals(note.getId(), model.getNote().getId());
		assertNotNull(model.getIngredients());
		assertNotNull(model.getCategories()); 
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
