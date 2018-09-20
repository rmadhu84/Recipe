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
public class CategoryCommand {
	private Long id;
	private String categoryName;
//	private Set<Recipe> recipes;
	
}
