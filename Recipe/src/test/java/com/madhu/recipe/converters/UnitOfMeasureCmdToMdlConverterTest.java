package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

public class UnitOfMeasureCmdToMdlConverterTest {

	UnitOfMeasureCmdToMdlConverter converter;
	
	private static final String DESCRIPTION = "DESCRTPTION";
	
	private static final Long LONG_VALUE = 1L;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCmdToMdlConverter();
	}

	@Test
	public void testNullParameter() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	public void testConvert() throws Exception{
		//given
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setDescription(DESCRIPTION);
		command.setId(LONG_VALUE);
		
		//when
		UnitOfMeasure model = converter.convert(command);
		
		//then
		assertNotNull(model);
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(DESCRIPTION, model.getDescription());
	
	}

}
