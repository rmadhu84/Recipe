/**
 * 
 */
package com.madhu.recipe.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.madhu.recipe.Enums.Difficulty;

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
public class RecipeCommand {

	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Byte[] image;
	private Difficulty difficulty;
	private NoteCommand note;
	private Set<IngredientCommand> ingredients = new HashSet<IngredientCommand>();
	private Set<CategoryCommand> categories = new HashSet<CategoryCommand>();
	private List<String> selectedCategories = new ArrayList<String>();
	
	/**
	 * 
	 * @param ingredient
	 * @return
	 */
	public RecipeCommand addIngredient(IngredientCommand ingredient) {
		this.ingredients.add(ingredient);
		return this;
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	public RecipeCommand addCategory(CategoryCommand category) {
		// TODO Auto-generated method stub
		this.categories.add(category);
		return this;
	}

}
