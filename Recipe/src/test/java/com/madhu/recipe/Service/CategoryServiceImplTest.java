package com.madhu.recipe.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.commands.CategoryCommand;
import com.madhu.recipe.converters.CategoryMdlToCmdConverter;

public class CategoryServiceImplTest {

	CategoryServiceImpl categoryService;
	
	CategoryMdlToCmdConverter converter;
	
	@Mock
	CategoryRepository catRepo;
	
	private static final Long LONG_VALUE = 1L;
	private static final String CATEGORY_NAME = "Category1";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		converter = new CategoryMdlToCmdConverter();
		categoryService = new CategoryServiceImpl(catRepo, converter);
	}

	@Test
	public final void testGetAllCategories() {
		//given
		Category category = new Category();
		category.setId(LONG_VALUE);
		category.setCategoryName(CATEGORY_NAME);
		Set<Category> categoryData = new HashSet<Category>();
		categoryData.add(category);
		
		//when
		when(catRepo.findAll()).thenReturn(categoryData);
		Set<CategoryCommand> commandData = categoryService.getAllCategories();
		
		//then
		assertEquals(commandData.size(), 1);
		
		verify(catRepo, times(1));
	}

	@Test
	public final void testGetCategoryByName() {
		//fail("Not yet implemented"); // TODO
	}

}
