/**
 * 
 */
package com.madhu.recipe.converters;

import org.springframework.core.convert.converter.Converter;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.commands.RecipeCommand;

/**
 * @author Madhu
 *
 */
public class RecipeCmdToMdlConverter implements Converter<RecipeCommand, Recipe>{
	
	private final CategoryCmdToMdlConverter categoryConverter;
	private final NoteCmdToMdlConverter noteConverter;
	

	@Override
	public Recipe convert(RecipeCommand source) {
		if(source == null)
			return null;
		
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getCookTime());
		recipe.setNote(noteConverter.convert(source.getNote()));
		
		source.getCategories().forEach(catergory -> {
			recipe.addCategory(categoryConverter.convert(catergory));
		});
		return recipe;
	}
	/**
	 * @param categoryConverter
	 * @param noteConverter
	 */
	public RecipeCmdToMdlConverter(CategoryCmdToMdlConverter categoryConverter, NoteCmdToMdlConverter noteConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.noteConverter = noteConverter;
	}

}
