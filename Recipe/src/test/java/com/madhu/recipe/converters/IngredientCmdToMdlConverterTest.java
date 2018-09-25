/**
 * 
 */
package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

/**
 * @author Madhu
 *
 */
public class IngredientCmdToMdlConverterTest {
	
	IngredientCmdToMdlConverter converter;
	UnitOfMeasureCmdToMdlConverter uomConverter;
	
	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "Description";
	
	@Before
	public void setUp() throws Exception {
		uomConverter = new UnitOfMeasureCmdToMdlConverter();
		
		converter  = new IngredientCmdToMdlConverter(uomConverter);
	}

	@Test
	public final void testConvert() {
		//given
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(LONG_VALUE);
		uomCommand.setDescription(DESCRIPTION);
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(LONG_VALUE);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setUnitOfMeasure(uomCommand);
		ingredientCommand.setAmount(new BigDecimal(LONG_VALUE));
		
		//when
		Ingredient model = converter.convert(ingredientCommand);
		//then
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(LONG_VALUE, model.getUnitOfMeasure().getId());
		assertEquals(new BigDecimal(LONG_VALUE), model.getAmount());
		assertEquals(DESCRIPTION, model.getDescription());
		assertEquals(DESCRIPTION, model.getUnitOfMeasure().getDescription());
	}

}
