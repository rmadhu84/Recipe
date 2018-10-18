/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Repositories.IngredientRepository;
import com.madhu.recipe.Repositories.RecipeRepository;
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
	private final RecipeRepository recipeRepository;

	private IngredientMdlToCmdConverter toCmdConverter;

	/**
	 * 
	 * @param ingredientRepo
	 * @param toCmdConverter
	 * @param recipeRepository
	 * 
	 */
	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepo, IngredientMdlToCmdConverter toCmdConverter,
			RecipeRepository recipeRepository) {
		this.ingredientRepo = ingredientRepo;
		this.toCmdConverter = toCmdConverter;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findById(Long id) {
		log.debug("Fetching ingredient by id : " + id);
		return toCmdConverter.convert(ingredientRepo.findById(id).get());
	}

	@Override
	@Transactional
	public IngredientCommand findIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent())
			log.error("Requested Recipe does not exist !!" + recipeId);

		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
				.filter(ingredient -> ingredientId.equals(ingredient.getId()))
				.map(ingredient -> toCmdConverter.convert(ingredient)).findFirst();

		if(!ingredientCommand.isPresent())
			log.error("Ingredient does not exist - " + ingredientId);
		
		return ingredientCommand.get();
	}

	@Override
	public void deleteIngredientById(Long id) {
		ingredientRepo.deleteById(id);
		log.debug("Deleted Ingredient: " + id);
	}

}
