/**
 * 
 */
package com.madhu.recipe.Model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Madhu
 *
 */
@Data
@EqualsAndHashCode(exclude= {"recipe"})
@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal amount;

	/**
	 * Cascade is not used, since we do not need to delete Recipe when an ingredient is deleted.
	 */
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;

	public Ingredient() {
		
	}
	
	/**
	 * @param description
	 * @param amount
	 * @param recipe
	 * @param unitOfMeasure
	 */
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
		this.unitOfMeasure = unitOfMeasure;
	}


	/**
	 * @param description
	 * @param amount
	 * @param unitOfMeasure
	 */
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
		super();
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
	}
}
