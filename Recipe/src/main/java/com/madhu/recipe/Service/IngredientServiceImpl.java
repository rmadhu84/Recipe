/**
 * 
 */
package com.madhu.recipe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.recipe.Repositories.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ramachandranm1
 *
 */
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepo;
	
	/**
	 * @param ingredientRepo
	 */
	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public void deleteIngredientById(Long id) {
		ingredientRepo.deleteById(id);
		log.debug("Deleted Ingredient: " + id);
	}

}
