/**
 * 
 */
package com.madhu.recipe.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.madhu.recipe.Enums.Difficulty;

import lombok.Data;

/**
 * @author Madhu
 *
 */
@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	@Lob
	private String directions;

	@Lob
	private Byte[] image;

	/**
	 * The EnumType.STRING will use the String value of the Enum. The
	 * EnumType.ORDINAL will the use the index/Int value of the enum.
	 */
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	/*
	 * Preventing Lombok from creating a Setter for Note property. Created a setter
	 * manually for Note property
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Note note;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<Category>();

	/**
	 * @param note the note to set
	 */
	public void setNote(Note note) {
		this.note = note;
		note.setRecipe(this);
	}


	/**
	 * 
	 * @param Ingredient
	 * @return Recipe
	 */
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	/**
	 * 
	 * @param Category
	 * @return Recipe
	 */
	public Recipe addCategory(Category category) {
		this.categories.add(category);
		return this;
	}
	
}
