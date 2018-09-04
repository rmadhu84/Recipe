/**
 * 
 */
package com.madhu.recipe.DataLoad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author Madhu
 *
 */
@Component
@Transactional
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepo;
	private CategoryRepository catRepo;
	private UnitOfMeasureRepository uomRepo;

	/**
	 * @param recipeRepo
	 * @param catRepo
	 * @param uomRepo
	 */
	@Autowired
	public RecipeBootStrap(RecipeRepository recipeRepo, CategoryRepository catRepo, UnitOfMeasureRepository uomRepo) {
		super();
		this.recipeRepo = recipeRepo;
		this.catRepo = catRepo;
		this.uomRepo = uomRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadData();
	}

	private void loadData() {

		List<Recipe> recipes = new ArrayList<Recipe>(2);

		Optional<UnitOfMeasure> uom = uomRepo.findByDescription("Each");
		UnitOfMeasure eachUom = uom.get();

		uom = uomRepo.findByDescription("Tablespoon");
		UnitOfMeasure tablespoonUom = uom.get();

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

		note.setRecipteNotes(recipteNotes.toString());

		perfectGuacamole.setNote(note);

		perfectGuacamole.getCategories().add(catRepo.findByCategoryName("American").get());
		perfectGuacamole.getCategories().add(catRepo.findByCategoryName("Mexican").get());

		// perfectGuacamole.getIngredients().add(new Ingredient("Avacados", new
		// BigDecimal("2"), eachUom);
		perfectGuacamole.getIngredients().add(new Ingredient("Fresh Ripe Avacados", new BigDecimal("2"), eachUom));

		recipes.add(perfectGuacamole);
		recipeRepo.saveAll(recipes);

	}

}
