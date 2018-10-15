/**
 * 
 */
package com.madhu.recipe.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.madhu.recipe.Model.Ingredient;

/**
 * @author ramachandranm1
 *
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
