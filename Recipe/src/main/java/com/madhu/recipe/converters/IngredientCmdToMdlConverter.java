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
public class IngredientCmdToMdlConverter implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCmdToMdlConverter uomConverter;

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {

		if (source == null)
			return null;

		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
		return ingredient;
	}

	/**
	 * @param uomConverter
	 */
	public IngredientCmdToMdlConverter(UnitOfMeasureCmdToMdlConverter uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

}
