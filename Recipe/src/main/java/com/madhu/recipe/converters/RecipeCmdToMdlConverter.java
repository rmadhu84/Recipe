/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.commands.RecipeCommand;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class RecipeCmdToMdlConverter implements Converter<RecipeCommand, Recipe> {

	private final CategoryCmdToMdlConverter categoryConverter;
	private final NoteCmdToMdlConverter noteConverter;
	private final IngredientCmdToMdlConverter ingredientConverter;

	@Override
	@Synchronized
	@Nullable
	public Recipe convert(RecipeCommand source) {
		
		if (source == null)
			return null;

		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setNote(noteConverter.convert(source.getNote()));

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(catergory -> {
				recipe.addCategory(categoryConverter.convert(catergory));
			});
		}

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach(ingredient -> {
				recipe.addIngredient(ingredientConverter.convert(ingredient));
			});
		}

		return recipe;
	}

	/**
	 * @param categoryConverter
	 * @param noteConverter
	 * @param ingredientConverter
	 */
	public RecipeCmdToMdlConverter(CategoryCmdToMdlConverter categoryConverter, NoteCmdToMdlConverter noteConverter,
			IngredientCmdToMdlConverter ingredientConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.noteConverter = noteConverter;
		this.ingredientConverter = ingredientConverter;
	}

}
