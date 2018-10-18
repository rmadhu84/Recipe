/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.commands.IngredientCommand;

import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class IngredientMdlToCmdConverter implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureMdlToCmdConverter uomConverter;

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {

		if (source == null)
			return null;

		final IngredientCommand ingredient = new IngredientCommand();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
		if (source.getRecipe() != null) {
            ingredient.setRecipeId(source.getRecipe().getId());
        }
		return ingredient;
	}

	/**
	 * @param uomConverter
	 */
	public IngredientMdlToCmdConverter(UnitOfMeasureMdlToCmdConverter uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

}
