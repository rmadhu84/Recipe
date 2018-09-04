/**
 * 
 */
package com.madhu.recipe.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.madhu.recipe.Model.Recipe;


/**
 * @author Madhu
 *
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long>	{

}
