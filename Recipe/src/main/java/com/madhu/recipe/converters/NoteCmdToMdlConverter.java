/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.Note;
import com.madhu.recipe.commands.NoteCommand;

import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class NoteCmdToMdlConverter implements Converter<NoteCommand, Note>{

	@Synchronized
	@Nullable
	@Override
	public Note convert(NoteCommand source) {

		if(source == null)
			return null;
		final Note note = new Note();
		note.setId(source.getId());
		note.setRecipteNotes(source.getRecipteNotes());
		//note.setRecipe(source.getRecipe());
		return note;
	}

}
