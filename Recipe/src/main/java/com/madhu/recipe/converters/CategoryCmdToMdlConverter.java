/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.commands.UnitOfMeasureCommand;

import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class CategoryCmdToMdlConverter implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {

		if(source == null)
			return null;
		
		final Category category = new Category();
		category.setId(source.getId());
		category.setCategoryName(source.getCategoryName());
		return category;
	}

}
