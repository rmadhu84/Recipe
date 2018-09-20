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
public class UnitOfMeasureCmdToMdlConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {

		if(source == null)
			return null;
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		return uom;
	}

}
