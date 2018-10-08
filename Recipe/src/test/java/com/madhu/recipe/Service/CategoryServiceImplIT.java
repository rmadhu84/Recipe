/**
 * 
 */
package com.madhu.recipe.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhu.recipe.commands.CategoryCommand;

/**
 * @author ramachandranm1
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplIT {

	@Autowired
	private CategoryService service;
	
	
	private static final String AMERICAN = "American";
	private static final String ITALIAN = "Italian";

	private static final List<String> LIST_OF_NAMES = new ArrayList<String>() {{
	add(AMERICAN);
	add(ITALIAN);
	}};
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllCategories() {
		//fail("Not yet implemented");
		Set<CategoryCommand> categories = service.getAllCategories();
		assertNotNull(categories);
	}

	@Test
	public void testGetCategoryByName() {
		//fail("Not yet implemented");
		CategoryCommand command = service.getCategoryByName(AMERICAN);
		assertEquals(AMERICAN, command.getCategoryName());
	}
	
	@Test
	public void tesGetListOfCategoriesByNames() {
		Set<CategoryCommand> commandSet = service.getCategoriesByNames(LIST_OF_NAMES);
		assertEquals(2, commandSet.size());
	}

}
