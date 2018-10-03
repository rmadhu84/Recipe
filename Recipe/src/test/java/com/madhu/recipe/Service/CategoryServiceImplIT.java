/**
 * 
 */
package com.madhu.recipe.Service;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ramachandranm1
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplIT {

	@Autowired
	private RecipeService service;

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllCategories() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCategoryByName() {
		fail("Not yet implemented");
	}

}
