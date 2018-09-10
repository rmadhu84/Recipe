/**
 * 
 */
package com.madhu.recipe.DataLoad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.madhu.recipe.Enums.Difficulty;
import com.madhu.recipe.Model.Category;
import com.madhu.recipe.Model.Ingredient;
import com.madhu.recipe.Model.Note;
import com.madhu.recipe.Model.Recipe;
import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.Repositories.CategoryRepository;
import com.madhu.recipe.Repositories.RecipeRepository;
import com.madhu.recipe.Repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Madhu
 *
 */
@Component
@Transactional
@Slf4j
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepo;
	private CategoryRepository catRepo;
	private UnitOfMeasureRepository uomRepo;
	
	private final HashMap<String, UnitOfMeasure> uomMap;
	private final HashMap<String, Category> CatMap;

	
	

	/**
	 * @param recipeRepo
	 * @param catRepo
	 * @param uomRepo
	 * @param uomMap
	 * @param catMap
	 */
	public RecipeBootStrap(RecipeRepository recipeRepo, CategoryRepository catRepo, UnitOfMeasureRepository uomRepo,
			@Qualifier("getUomMap") HashMap<String, UnitOfMeasure> uomMap, @Qualifier("getCategoryMap") HashMap<String, Category> catMap) {
		this.recipeRepo = recipeRepo;
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
		this.uomMap = uomMap;
		CatMap = catMap;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		log.info(" *** Loading startup data *** ");
		recipeRepo.saveAll(loadData());
		log.info("*** Startup data Loaded *** ");
	}

	private List<Recipe> loadData() {

		List<Recipe> recipes = new ArrayList<Recipe>(2);
		
		UnitOfMeasure eachUom = uomMap.get("Each");
		UnitOfMeasure tablespoonUom =  uomMap.get("Tablespoon");
		UnitOfMeasure teaspoonUom =  uomMap.get("Teaspoon");
		UnitOfMeasure dashUom =  uomMap.get("Dash");
		UnitOfMeasure poundUom =  uomMap.get("Pound");
		UnitOfMeasure cupUom =  uomMap.get("Cup");
		UnitOfMeasure pintUom =  uomMap.get("Pint");

		// Perfect Guacamole Recipe.
		Recipe perfectGuacamole = new Recipe();
		perfectGuacamole.setDescription("Perfect Guacamole");
		perfectGuacamole.setDifficulty(Difficulty.Easy);
		perfectGuacamole.setCookTime(0);
		perfectGuacamole.setPrepTime(10);
		perfectGuacamole.setServings(4);

		StringBuilder directions = new StringBuilder();

		directions.append(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n");
		directions.append("\n");
		directions.append(
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n");
		directions.append("\n");
		directions.append(
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n");
		directions.append("\n");
		directions.append(
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n");
		directions.append("\n");
		directions.append(
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n");
		directions.append("\n");
		directions.append(
				"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n");
		directions.append("\n");
		directions.append(
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

		perfectGuacamole.setDirections(directions.toString());

		Note note = new Note();
		StringBuilder recipteNotes = new StringBuilder();
		recipteNotes.append(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n");
		recipteNotes.append("\n");
		recipteNotes.append(
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n");
		recipteNotes.append("\n");
		recipteNotes.append(
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n");
		recipteNotes.append("\n");
		recipteNotes.append(
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n");
		recipteNotes.append("\n");
		recipteNotes.append("For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

		note.setRecipe(perfectGuacamole);
		note.setRecipteNotes(recipteNotes.toString());

		perfectGuacamole.setNote(note);

		perfectGuacamole.addCategory(CatMap.get("Mexican"));
		perfectGuacamole.addCategory(CatMap.get("Mexican"));
		

		perfectGuacamole.addIngredient(new Ingredient("Fresh Ripe Avacados", new BigDecimal("2"), eachUom));
		perfectGuacamole.addIngredient(new Ingredient("Kosher Salt", new BigDecimal("0.5"), teaspoonUom));
		perfectGuacamole.addIngredient(new Ingredient("Fresh Lime\\Lemon juice", new BigDecimal("1"), tablespoonUom));
		perfectGuacamole.addIngredient(new Ingredient("Minced Red Onions or Thinly Sliced Green Onions",
				new BigDecimal("2"), tablespoonUom));
		perfectGuacamole.addIngredient(new Ingredient("Serrano Chillies", new BigDecimal("1"), eachUom));
		perfectGuacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal("2"), tablespoonUom));
		perfectGuacamole.addIngredient(new Ingredient("Freshly Grated Black Pepper", new BigDecimal("1"), dashUom));
		perfectGuacamole.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped",
				new BigDecimal("1"), eachUom));

		recipes.add(perfectGuacamole);

		// Spicy Chicken Tacos
		Recipe spicyTacos = new Recipe();
		spicyTacos.setCookTime(15);
		spicyTacos.setPrepTime(20);
		spicyTacos.setServings(4);
		spicyTacos.setDifficulty(Difficulty.Easy);
		spicyTacos.setDescription("Spicy Grilled Chicken Tacos");

		directions = new StringBuilder();
		directions.append("1 Prepare a gas or charcoal grill for medium-high, direct heat.\r\n");
		directions.append("\r\n");
		directions.append("2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n");
		directions.append("\r\n");
		directions.append("Set aside to marinate while the grill heats and you prepare the rest of the toppings.\r\n");
		directions.append("\r\n");
		directions.append("3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\r\n");
		directions.append("\r\n");
		directions.append("4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n");
		directions.append("\r\n");
		directions.append("Wrap warmed tortillas in a tea towel to keep them warm until serving.\r\n");
		directions.append("\r\n");
		directions.append("5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		
		spicyTacos.setDirections(directions.toString());
		
		spicyTacos.addIngredient(new Ingredient("Sugar", new BigDecimal("1"), teaspoonUom));
		spicyTacos.addIngredient(new Ingredient("Salt", new BigDecimal("0.5"), teaspoonUom));
		spicyTacos.addIngredient(new Ingredient("Garlic, finely chopped", new BigDecimal("1"), eachUom));
		spicyTacos.addIngredient(new Ingredient("Orange Zest", new BigDecimal("1"), tablespoonUom));
		spicyTacos.addIngredient(new Ingredient("Fresh Squeezed Orange Juice", new BigDecimal("3"), tablespoonUom));
		spicyTacos.addIngredient(new Ingredient("Olive Oil", new BigDecimal("1"), teaspoonUom));
		spicyTacos.addIngredient(new Ingredient("Boneless Skinless Chicken Thighs", new BigDecimal("1.25"), poundUom));
		spicyTacos.addIngredient(new Ingredient("Corn Tortillas - Small", new BigDecimal("8"), eachUom));
		spicyTacos.addIngredient(new Ingredient("Packed Baby Arugula", new BigDecimal("3"), cupUom));
		spicyTacos.addIngredient(new Ingredient("Ripe Avocados - Medium", new BigDecimal("2"), eachUom));
		spicyTacos.addIngredient(new Ingredient("Radish - Thinely sliced", new BigDecimal("4"), eachUom));
		spicyTacos.addIngredient(new Ingredient("Cherry Tomatoes - Halved", new BigDecimal("0.5"), pintUom));
		spicyTacos.addIngredient(new Ingredient("Cilantro - Roughly chopped", new BigDecimal("0.5"), cupUom));
		spicyTacos.addIngredient(new Ingredient("Sour cream", new BigDecimal("0.5"), cupUom));
		spicyTacos.addIngredient(new Ingredient("Milk", new BigDecimal("0.25"), cupUom));
		spicyTacos.addIngredient(new Ingredient("Lime - cut into wedges", new BigDecimal("1"), eachUom));
		
		note = new Note();
		note.setRecipteNotes("Thin the 0.5 cup sour cream with .25 cup of milk");
		note.setRecipe(spicyTacos);
		spicyTacos.setNote(note);
		
		spicyTacos.addCategory(CatMap.get("Mexican"));
		
		
		recipes.add(spicyTacos);
		
		return recipes;

	}

}
