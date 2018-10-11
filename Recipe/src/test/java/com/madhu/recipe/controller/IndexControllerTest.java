package com.madhu.recipe.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Service.CategoryService;
import com.madhu.recipe.Service.RecipeService;
import com.madhu.recipe.Service.UnitOfMeasureService;
import com.madhu.recipe.commands.RecipeCommand;

public class IndexControllerTest {

	IndexController ic;

	@Mock
	Model model;

	@Mock
	RecipeService recipeService;

	@Mock
	CategoryService categoryService;
	
	@Mock
	UnitOfMeasureService uomService;
	
	@Mock
	HttpSession session;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ic = new IndexController(recipeService, categoryService, uomService, session);
	}

	@Test
	public void testMvc() throws Exception {
		MockMvc mvc = MockMvcBuilders.standaloneSetup(ic).build();
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	public void testGetIndexPage() {
		final String getRecipeOutput = "index";

		// given
		Set<RecipeCommand> recipes = new HashSet<RecipeCommand>();
		RecipeCommand recipe1 = new RecipeCommand();
		recipe1.setId(1L);

		recipes.add(recipe1);
		RecipeCommand recipe2 = new RecipeCommand();
		recipe2.setId(2L);

		recipes.add(recipe2);

		// when
		when(recipeService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// then
		assertEquals(ic.getIndexPage(model), getRecipeOutput);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setIc = argumentCaptor.getValue();
		assertEquals(2, setIc.size());

	}

}
