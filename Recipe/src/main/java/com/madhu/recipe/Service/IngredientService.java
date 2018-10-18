/**
 * 
 */
package com.madhu.recipe.Service;

import com.madhu.recipe.commands.IngredientCommand;

/**
 * @author ramachandranm1
 *
 */
public interface IngredientService {
	
	void deleteIngredientById(Long id);

	IngredientCommand findById(Long id);

}
