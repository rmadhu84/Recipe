package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.commands.NoteCommand;
import com.madhu.recipe.commands.RecipeCommand;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

public class RecipeCmdToMdlConverterTest {

	RecipeCmdToMdlConverter converter;
	CategoryCmdToMdlConverter categoryConverter;
	NoteCmdToMdlConverter noteConverter;
	IngredientCmdToMdlConverter ingredientConverter;
	UnitOfMeasureCmdToMdlConverter uomConverter;
	
	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "Description";
	private static final Long LONG_VALUE2 = 2L;
	
	@Before
	public void setUp() throws Exception {
		categoryConverter = new CategoryCmdToMdlConverter();
		noteConverter = new NoteCmdToMdlConverter();
		uomConverter = new UnitOfMeasureCmdToMdlConverter();
		ingredientConverter = new IngredientCmdToMdlConverter(uomConverter);
	
		converter = new RecipeCmdToMdlConverter(categoryConverter, noteConverter, ingredientConverter);
	}
	@Test
	public final void testConvert() {
		//given
		UnitOfMeasureCommand uomCommand  = new UnitOfMeasureCommand();
		uomCommand.setId(LONG_VALUE);
		uomCommand.setDescription(DESCRIPTION);
		
		IngredientCommand ingredrientCommand1 = new  IngredientCommand();
		ingredrientCommand1.setId(LONG_VALUE);
		ingredrientCommand1.setDescription(DESCRIPTION);
		ingredrientCommand1.setUnitOfMeasure(uomCommand);
		ingredrientCommand1.setAmount(new BigDecimal(LONG_VALUE));
		
		IngredientCommand ingredrientCommand2 = new  IngredientCommand();
		ingredrientCommand2.setId(LONG_VALUE2);
		ingredrientCommand2.setDescription(DESCRIPTION);
		ingredrientCommand2.setUnitOfMeasure(uomCommand);
		ingredrientCommand2.setAmount(new BigDecimal(LONG_VALUE));
		
		CategoryCommand categoryCommand1 = new CategoryCommand();
		categoryCommand1.setId(LONG_VALUE);
		categoryCommand1.setCategoryName(DESCRIPTION);
		
		CategoryCommand categoryCommand2 = new CategoryCommand();
		categoryCommand2.setId(LONG_VALUE2);
		categoryCommand2.setCategoryName(DESCRIPTION);
		
		NoteCommand noteCommand = new NoteCommand();
		noteCommand.setId(LONG_VALUE);
		noteCommand.setRecipteNotes(DESCRIPTION);
		
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(LONG_VALUE);
		recipeCommand.setDescription(DESCRIPTION);
		recipeCommand.setCookTime(LONG_VALUE.intValue());
		recipeCommand.setPrepTime(LONG_VALUE2.intValue());
		recipeCommand.addCategory(categoryCommand1);
		recipeCommand.addCategory(categoryCommand2);
		recipeCommand.addIngredient(ingredrientCommand1);
		recipeCommand.addIngredient(ingredrientCommand2);
		recipeCommand.setNote(noteCommand);
		
		//when
		Recipe model= converter.convert(recipeCommand);
		
		//then
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(LONG_VALUE, model.getNote().getId());
		
		assertEquals(DESCRIPTION, model.getDescription());
		assertEquals(DESCRIPTION, model.getNote().getRecipteNotes());
		
		assertEquals(LONG_VALUE, new Long(model.getCookTime()));
		assertEquals(LONG_VALUE2, new Long(model.getPrepTime()));
		
		
		assertEquals(2, model.getCategories().size());
		assertEquals(2, model.getIngredients().size());
		
		List<Category> list = model.getCategories().stream().collect(Collectors.toList());
		Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
		
		assertEquals(LONG_VALUE, new Long(list.get(0).getId()));
		assertEquals(LONG_VALUE2, new Long(list.get(1).getId()));

		List<Ingredient> list1 = model.getIngredients().stream().collect(Collectors.toList());
		Collections.sort(list1, (o1, o2) -> o1.getId().compareTo(o2.getId()));
		
		assertEquals(LONG_VALUE, new Long(list1.get(0).getId()));
		assertEquals(LONG_VALUE2, new Long(list1.get(1).getId()));

		
	}

}
