/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	public final RecipeRepository recipeRepo;

	/**
	 * @param recipeRepo
	 */
	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepo) {
		super();
		log.info("Recipe Service Implementation");
		this.recipeRepo = recipeRepo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madhu.recipe.Service.RecipeService#getRecipes()
	 */
	@Override
	public Set<Recipe> getRecipes() {

		Set<Recipe> recipeSet = new HashSet<Recipe>();
		recipeRepo.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	/* (non-Javadoc)
	 * @see com.madhu.recipe.Service.RecipeService#findById(java.lang.Long)
	 */
	@Override
	public Recipe getRecipesById(Long id) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipe = recipeRepo.findById(id);
		if(!recipe.isPresent()) {
			throw new RuntimeException("Could not find recipe.");
		}
		
		return recipe.get();
	}
	
	

}
