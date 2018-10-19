/**
 * 
 */
package com.madhu.recipe.commands;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Madhu
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	private Long id;
	private Long recipeId;
	private String description;
	private BigDecimal amount;
	private RecipeCommand recipe;
	private UnitOfMeasureCommand unitOfMeasure;
	private Set<UnitOfMeasureCommand> allUoms = new HashSet<UnitOfMeasureCommand>();

}
