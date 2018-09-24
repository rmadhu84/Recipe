package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.commands.CategoryCommand;

public class CategoryMdlToCmdToConverterTest {

	private static final Long LONG_VALUE= 1L;
	private static final String DESCRIPTION = "Any String";
	
	CategoryMdlToCmdConverter converter;  
	
	@Before
	public void setup() {
		converter = new CategoryMdlToCmdConverter();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void testConvert() {
		//given
		Category model = new Category();
		model.setCategoryName(DESCRIPTION);
		model.setId(LONG_VALUE);
		
		//when
		CategoryCommand command = converter.convert(model); 
		
		//then
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getCategoryName());
	}

}
