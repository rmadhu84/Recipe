package com.madhu.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.madhu.recipe.Model.Note;
import com.madhu.recipe.commands.NoteCommand;

public class NoteCmdToMdlConverterTest {

	NoteCmdToMdlConverter converter;
	
	private static final Long LONG_VALUE= 1L;
	private static final String ANY_STRING = "Any String";
	
	@Before
	public void setUp() throws Exception {
		converter = new NoteCmdToMdlConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new NoteCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		NoteCommand noteCommand = new NoteCommand();
		noteCommand.setId(LONG_VALUE);
		noteCommand.setRecipteNotes(ANY_STRING);
		
		//when
		Note model = converter.convert(noteCommand);
		
		//then
		assertEquals(LONG_VALUE, model.getId());
		assertEquals(ANY_STRING, model.getRecipteNotes());
		
	}

}
