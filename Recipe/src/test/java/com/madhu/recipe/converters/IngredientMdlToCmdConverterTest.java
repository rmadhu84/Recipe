/**
 * 
 */
package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.IngredientCommand;

/**
 * @author Madhu
 *
 */
public class IngredientMdlToCmdConverterTest {
	
	IngredientMdlToCmdConverter converter;
	UnitOfMeasureMdlToCmdConverter uomConverter;
	
	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "Description";
	
	@Before
	public void setUp() throws Exception {
		uomConverter = new UnitOfMeasureMdlToCmdConverter();
		
		converter  = new IngredientMdlToCmdConverter(uomConverter);
	}

	@Test
	public final void testConvert() {
		//given
		UnitOfMeasure uomModel = new UnitOfMeasure();
		uomModel.setId(LONG_VALUE);
		uomModel.setDescription(DESCRIPTION);
		Ingredient ingredientModel = new Ingredient();
		ingredientModel.setId(LONG_VALUE);
		ingredientModel.setDescription(DESCRIPTION);
		ingredientModel.setUnitOfMeasure(uomModel);
		ingredientModel.setAmount(new BigDecimal(LONG_VALUE));
		
		//when
		IngredientCommand command = converter.convert(ingredientModel);
		//then
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(LONG_VALUE, command.getUnitOfMeasure().getId());
		assertEquals(new BigDecimal(LONG_VALUE), command.getAmount());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(DESCRIPTION, command.getUnitOfMeasure().getDescription());
	}

}
