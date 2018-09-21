package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.commands.CategoryCommand;

public class CategoryCmdToMdlConverterTest {

	private static final Long LONG_VALUE= 1L;
	private static final String DESCRIPTION = Mockito.anyString();
	
	CategoryCmdToMdlConverter converter;  
	
	@Before
	public void setup() {
		converter = new CategoryCmdToMdlConverter();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		CategoryCommand command = new CategoryCommand();
		command.setCategoryName(DESCRIPTION);
		command.setId(LONG_VALUE);
		
		//when
		Category model = converter.convert(command); 
		
		//then
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(DESCRIPTION, model.getCategoryName());
	}

}
