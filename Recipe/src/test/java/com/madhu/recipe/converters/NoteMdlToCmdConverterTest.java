package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Note;
import com.madhu.recipe.commands.NoteCommand;

public class NoteMdlToCmdConverterTest {

	NoteMdlToCmdConverter converter;
	
	private static final Long LONG_VALUE= 1L;
	private static final String ANY_STRING = "Any String";
	
	@Before
	public void setUp() throws Exception {
		converter = new NoteMdlToCmdConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Note()));
	}
	
	@Test
	public void testConvert() {
		//given
		Note model = new Note();
		model.setId(LONG_VALUE);
		model.setRecipteNotes(ANY_STRING);
		
		//when
		NoteCommand command = converter.convert(model);
		
		//then
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(ANY_STRING, command.getRecipteNotes());
		
	}

}
