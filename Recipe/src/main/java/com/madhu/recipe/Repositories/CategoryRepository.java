/**
 * 
 */
package com.madhu.recipe.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.madhu.recipe.Model.Category;

/**
 * @author Madhu
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Optional<Category> findByCategoryName(String categoryName);

}
