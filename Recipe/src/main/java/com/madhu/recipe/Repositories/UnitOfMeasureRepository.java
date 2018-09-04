/**
 * 
 */
package com.madhu.recipe.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.madhu.recipe.Model.UnitOfMeasure;

/**
 * @author Madhu
 *
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);
}
