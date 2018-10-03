package com.madhu.recipe.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.converters.CategoryMdlToCmdConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository catRepo;
	CategoryMdlToCmdConverter converter;
	
	@Override
	public Set<CategoryCommand> getAllCategories() {
		log.info("Fetching All Categories");
		Set<CategoryCommand> categories = new HashSet<CategoryCommand>();
		
		catRepo.findAll().forEach(s->{
			categories.add(converter.convert(s));
		});;
		return categories;
	}

	/**
	 * @param catRepo
	 * @param converter
	 */
	@Autowired
	public CategoryServiceImpl(CategoryRepository catRepo, CategoryMdlToCmdConverter converter) {
		this.catRepo = catRepo;
		this.converter = converter;
	}

	@Override
	public CategoryCommand getCategoryByName(String categoryName) {
		log.info("Fetching: " + categoryName);
		
		return null;
	}

}
