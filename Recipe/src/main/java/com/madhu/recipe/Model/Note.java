/**
 * 
 */
package com.madhu.recipe.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Madhu
 *
 */
@Getter
@Setter
@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/** 
	 * Cascade is not used, since we do not need to delete Recipe when a note is deleted.
	 * Removed Recipe, to maked the relationship one directional
	 **/
	//@OneToOne 
	//private Recipe recipe;
 
	@Lob
	private String recipteNotes;

}
