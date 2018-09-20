/**
 * 
 */
package com.madhu.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Madhu
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {
	
	private Long id;
	private RecipeCommand recipe;
	private String recipteNotes;

}
