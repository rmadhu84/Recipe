/**
 * 
 */
package com.madhu.recipe.Service;

import java.util.Set;

import com.madhu.recipe.commands.UnitOfMeasureCommand;

/**
 * @author ramachandranm1
 *
 */
public interface UnitOfMeasureService {
	Set<UnitOfMeasureCommand> getAllUoms();
}
