/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.Set;

import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.RecipeCommand;

/**
 * @author Madhu
 *
 */
public interface RecipeService {
	Set<RecipeCommand> getRecipes();
	RecipeCommand getRecipesById(Long id);
	RecipeCommand getRecipesByIdForEdit(Long id, Set<CategoryCommand> categories);
	RecipeCommand saveRecipe(RecipeCommand recipe);
	boolean deleteRecipeById(Long id);
}
