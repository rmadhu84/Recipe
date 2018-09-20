/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.commands.CategoryCommand;

import lombok.Synchronized;

/**
 * @author Madhu
 *
 */
@Component
public class CategoryMdlToCmdConverter implements Converter<Category, CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {

		if(source == null)
			return null;
		
		final CategoryCommand category = new CategoryCommand();
		category.setId(source.getId());
		category.setCategoryName(source.getCategoryName());
		return category;
	}

}
