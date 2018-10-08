/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.List;
import java.util.Set;

import com.madhu.recipe.commands.CategoryCommand;

/**
 * @author ramachandranm1
 *
 */
public interface CategoryService {
	Set<CategoryCommand> getAllCategories();
	CategoryCommand getCategoryByName(String categoryName);
	Set<CategoryCommand> getCategoriesByNames(List<String> categoryNames);

}
