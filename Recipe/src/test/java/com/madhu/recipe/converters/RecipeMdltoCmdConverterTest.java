package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.Model.Note;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.commands.RecipeCommand;

public class RecipeMdltoCmdConverterTest {

	RecipeMdlToCmdConverter converter;
	CategoryMdlToCmdConverter categoryConverter;
	NoteMdlToCmdConverter noteConverter;
	IngredientMdlToCmdConverter ingredientConverter;
	UnitOfMeasureMdlToCmdConverter uomConverter;

	private static final Long LONG_VALUE = 1L;
	private static final String ANY_STRING = "Any String";
	
	@Mock
	Note note = new Note();
	
	@Mock
	Category category = new Category();
	
	@Mock
	Ingredient ingredient = new Ingredient(); 
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		categoryConverter = new CategoryMdlToCmdConverter();
		noteConverter = new NoteMdlToCmdConverter();
		uomConverter = new UnitOfMeasureMdlToCmdConverter();
		ingredientConverter = new IngredientMdlToCmdConverter(uomConverter);

		converter = new RecipeMdlToCmdConverter(categoryConverter, noteConverter, ingredientConverter);
	}

	@Test
	public void test() {
		//given
		Recipe model = new Recipe();
		model.setId(LONG_VALUE);
		model.setDescription(ANY_STRING);
		model.getCategories().add(category);
		model.getIngredients().add(ingredient);
		model.setNote(note);
	
		//when
		RecipeCommand command = converter.convert(model);
		
		//then
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(ANY_STRING, command.getDescription());
		assertEquals(note.getId(), command.getNote().getId());
		assertNotNull(command.getIngredients());
		assertNotNull(command.getCategories()); 
	}

}
