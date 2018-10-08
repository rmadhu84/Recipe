/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Repositories.RecipeRepository;
import com.madhu.recipe.commands.RecipeCommand;
import com.madhu.recipe.converters.RecipeCmdToMdlConverter;
import com.madhu.recipe.converters.RecipeMdlToCmdConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	public final RecipeRepository recipeRepo;

	public final RecipeCmdToMdlConverter toMdlConverter;

	public final RecipeMdlToCmdConverter toCmdConverter;

	/**
	 * @param recipeRepo
	 * @param toMdlConverter
	 * @param toCmdConverter
	 */
	public RecipeServiceImpl(RecipeRepository recipeRepo, RecipeCmdToMdlConverter toMdlConverter,
			RecipeMdlToCmdConverter toCmdConverter) {
		super();
		log.info("Recipe Service Implementation");
		this.recipeRepo = recipeRepo;
		this.toMdlConverter = toMdlConverter;
		this.toCmdConverter = toCmdConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madhu.recipe.Service.RecipeService#getRecipes()
	 */
	@Override
	public Set<RecipeCommand> getRecipes() {

		// Set<Recipe> recipeSet = new HashSet<Recipe>();
		Set<RecipeCommand> result = new HashSet<RecipeCommand>();
		/*
		 * recipeRepo.findAll().iterator().forEachRemaining(recipeSet::add);
		 * recipeSet.forEach(recipe ->{ result.add(toCmdConverter.convert(recipe)); });
		 */

		recipeRepo.findAll().iterator().forEachRemaining(recipe -> {
			result.add(toCmdConverter.convert(recipe));
		});
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madhu.recipe.Service.RecipeService#findById(java.lang.Long)
	 */
	@Override
	public RecipeCommand getRecipesById(Long id) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipe = recipeRepo.findById(id);
		if (!recipe.isPresent()) {
			throw new RuntimeException("Could not find recipe.");
		}

		return toCmdConverter.convert(recipe.get());
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipe(RecipeCommand recipe) {
		log.debug("Recipe saved");
		Recipe unSavedRecipe = toMdlConverter.convert(recipe);
		RecipeCommand savedRecipe = toCmdConverter.convert(recipeRepo.save(unSavedRecipe));
		return savedRecipe;
	}

	@Override
	public boolean deleteRecipeById(Long id) {
		log.info("Deleting Recipe");
		recipeRepo.deleteById(id);
		log.info("Recipe deleted !!!");
		return true;
	}

}
