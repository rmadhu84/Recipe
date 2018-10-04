/**
 * 
 */
package com.madhu.recipe.Service;

import static org.junit.Assert.assertNotNull;

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
	}

}
