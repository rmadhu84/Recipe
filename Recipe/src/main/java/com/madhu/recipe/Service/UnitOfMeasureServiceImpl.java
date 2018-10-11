package com.madhu.recipe.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.madhu.recipe.Repositories.UnitOfMeasureRepository;
import com.madhu.recipe.commands.UnitOfMeasureCommand;
import com.madhu.recipe.converters.UnitOfMeasureMdlToCmdConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository uomRepo;
	private final UnitOfMeasureMdlToCmdConverter toCmdConverter;
	
	
	/**
	 * @param toCmdConverter
	 */
	public UnitOfMeasureServiceImpl(UnitOfMeasureMdlToCmdConverter toCmdConverter, UnitOfMeasureRepository uomRepo) {
		this.toCmdConverter = toCmdConverter;
		this.uomRepo = uomRepo;
	}


	@Override
	public Set<UnitOfMeasureCommand> getAllUoms() {
		// TODO Auto-generated method stub
		log.info("Fetching all Units of Measure");
		Set<UnitOfMeasureCommand> uoms = new HashSet<UnitOfMeasureCommand>();
		
		uomRepo.findAll().forEach(uom ->{
			uoms.add(toCmdConverter.convert(uom));
		});
		
		return uoms;
	}

}
