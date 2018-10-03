/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.Set;

import com.madhu.recipe.commands.CategoryCommand;

/**
 * @author ramachandranm1
 *
 */
public interface CategoryService {
	Set<CategoryCommand> getAllCategories();
	CategoryCommand getCategoryByName(String categoryName);

}
