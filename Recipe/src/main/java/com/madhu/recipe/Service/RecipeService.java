/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.Set;

import com.madhu.recipe.commands.RecipeCommand;

/**
 * @author Madhu
 *
 */
public interface RecipeService {
	Set<RecipeCommand> getRecipes();
	RecipeCommand getRecipesById(Long id);
	RecipeCommand saveRecipe(RecipeCommand recipe);
	boolean deleteRecipeById(Long id);
}
