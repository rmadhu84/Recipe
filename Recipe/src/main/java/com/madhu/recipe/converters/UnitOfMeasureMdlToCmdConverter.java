/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class UnitOfMeasureMdlToCmdConverter implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {

		if(source == null)
			return null;
		
		final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		return uom;
	}

}
