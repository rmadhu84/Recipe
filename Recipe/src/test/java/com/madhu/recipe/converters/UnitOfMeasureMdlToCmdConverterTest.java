package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

public class UnitOfMeasureMdlToCmdConverterTest {

	private static final String DESCRIPTION = "DESCRIPTION";
	private static final Long LONG_VALUE = 1L;
	
	UnitOfMeasureMdlToCmdConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureMdlToCmdConverter();
	}
	
	@Test
	public void testNotNullParameter() throws Exception{
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}
	
	@Test
	public void testConvert() {
		//given
		UnitOfMeasure model = new UnitOfMeasure();
		model.setId(LONG_VALUE);
		model.setDescription(DESCRIPTION);
		
		
		//when
		UnitOfMeasureCommand command = converter.convert(model);
		
		//then
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		
	}

}
