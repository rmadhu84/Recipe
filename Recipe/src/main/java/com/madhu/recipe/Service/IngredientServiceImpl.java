/**
 * 
 */
package com.madhu.recipe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.recipe.Repositories.IngredientRepository;
import com.madhu.recipe.commands.IngredientCommand;
import com.madhu.recipe.converters.IngredientMdlToCmdConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ramachandranm1
 *
 */
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepo;
	private IngredientMdlToCmdConverter toCmdConverter;

	/**
	 * @param ingredientRepo
	 * @param toCmdConverter
	 */
	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepo, IngredientMdlToCmdConverter toCmdConverter) {
		this.ingredientRepo = ingredientRepo;
		this.toCmdConverter = toCmdConverter;
	}

	@Override
	public IngredientCommand findById(Long id) {
		log.debug("Fetching ingredient by id : " + id);
		return toCmdConverter.convert(ingredientRepo.findById(id).get());
	}

	
	@Override
	public void deleteIngredientById(Long id) {
		ingredientRepo.deleteById(id);
		log.debug("Deleted Ingredient: " + id);
	}

}
