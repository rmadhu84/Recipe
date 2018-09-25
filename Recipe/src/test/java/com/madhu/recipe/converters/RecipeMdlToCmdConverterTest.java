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
import com.madhu.recipe.Model.Note;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.commands.RecipeCommand;

public class RecipeMdlToCmdConverterTest {

	RecipeMdlToCmdConverter converter;
	CategoryMdlToCmdConverter categoryConverter;
	NoteMdlToCmdConverter noteConverter;
	IngredientMdlToCmdConverter ingredientConverter;
	UnitOfMeasureMdlToCmdConverter uomConverter;
	
	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "Description";
	private static final Long LONG_VALUE2 = 2L;
	
	@Before
	public void setUp() throws Exception {
		categoryConverter = new CategoryMdlToCmdConverter();
		noteConverter = new NoteMdlToCmdConverter();
		uomConverter = new UnitOfMeasureMdlToCmdConverter();
		ingredientConverter = new IngredientMdlToCmdConverter(uomConverter);
	
		converter = new RecipeMdlToCmdConverter(categoryConverter, noteConverter, ingredientConverter);
	}
	@Test
	public final void testConvert() {
		//given
		UnitOfMeasure uomModel  = new UnitOfMeasure();
		uomModel.setId(LONG_VALUE);
		uomModel.setDescription(DESCRIPTION);
		
		Ingredient ingredrientModel1 = new  Ingredient();
		ingredrientModel1.setId(LONG_VALUE);
		ingredrientModel1.setDescription(DESCRIPTION);
		ingredrientModel1.setUnitOfMeasure(uomModel);
		ingredrientModel1.setAmount(new BigDecimal(LONG_VALUE));
		
		Ingredient ingredrientModel2 = new  Ingredient();
		ingredrientModel2.setId(LONG_VALUE2);
		ingredrientModel2.setDescription(DESCRIPTION);
		ingredrientModel2.setUnitOfMeasure(uomModel);
		ingredrientModel2.setAmount(new BigDecimal(LONG_VALUE));
		
		Category categoryModel1 = new Category();
		categoryModel1.setId(LONG_VALUE);
		categoryModel1.setCategoryName(DESCRIPTION);
		
		Category category2 = new Category();
		category2.setId(LONG_VALUE2);
		category2.setCategoryName(DESCRIPTION);
		
		Note noteModel = new Note();
		noteModel.setId(LONG_VALUE);
		noteModel.setRecipteNotes(DESCRIPTION);
		
		Recipe recipeModel = new Recipe();
		recipeModel.setId(LONG_VALUE);
		recipeModel.setDescription(DESCRIPTION);
		recipeModel.setCookTime(LONG_VALUE.intValue());
		recipeModel.setPrepTime(LONG_VALUE2.intValue());
		recipeModel.addCategory(categoryModel1);
		recipeModel.addCategory(category2);
		recipeModel.addIngredient(ingredrientModel1);
		recipeModel.addIngredient(ingredrientModel2);
		recipeModel.setNote(noteModel);
		
		//when
		RecipeCommand command= converter.convert(recipeModel);
		
		//then
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(LONG_VALUE, command.getNote().getId());
		
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(DESCRIPTION, command.getNote().getRecipteNotes());
		
		assertEquals(LONG_VALUE, new Long(command.getCookTime()));
		assertEquals(LONG_VALUE2, new Long(command.getPrepTime()));
		
		
		assertEquals(2, command.getCategories().size());
		assertEquals(2, command.getIngredients().size());
		
		List<CategoryCommand> list = command.getCategories().stream().collect(Collectors.toList());
		Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
		
		assertEquals(LONG_VALUE, new Long(list.get(0).getId()));
		assertEquals(LONG_VALUE2, new Long(list.get(1).getId()));

		List<IngredientCommand> list1 = command.getIngredients().stream().collect(Collectors.toList());
		Collections.sort(list1, (o1, o2) -> o1.getId().compareTo(o2.getId()));
		
		assertEquals(LONG_VALUE, new Long(list1.get(0).getId()));
		assertEquals(LONG_VALUE2, new Long(list1.get(1).getId()));

		
	}

}
