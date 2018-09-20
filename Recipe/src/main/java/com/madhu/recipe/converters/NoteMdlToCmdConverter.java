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
public class NoteMdlToCmdConverter implements Converter<Note, NoteCommand>{

	@Synchronized
	@Nullable
	@Override
	public NoteCommand convert(Note source) {

		if(source == null)
			return null;
		final NoteCommand note = new NoteCommand();
		note.setId(source.getId());
		note.setRecipteNotes(source.getRecipteNotes());
		return note;
	}

}
