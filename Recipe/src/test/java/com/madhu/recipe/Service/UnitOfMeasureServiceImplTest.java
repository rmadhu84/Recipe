package com.madhu.recipe.Service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.madhu.recipe.Model.UnitOfMeasure;
import com.madhu.recipe.Repositories.UnitOfMeasureRepository;
import com.madhu.recipe.commands.UnitOfMeasureCommand;
import com.madhu.recipe.converters.UnitOfMeasureMdlToCmdConverter;

public class UnitOfMeasureServiceImplTest {

	private static final Long LONG_VALUE = 1L;

	UnitOfMeasureServiceImpl service;
	
	UnitOfMeasureMdlToCmdConverter toCmdConverter;
	
	@Mock
	UnitOfMeasureRepository repo; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		toCmdConverter = new UnitOfMeasureMdlToCmdConverter();
		service = new UnitOfMeasureServiceImpl(toCmdConverter, repo); 
				
	}

	@Test
	public void testGetAllUoms() {
		//given
		UnitOfMeasure model = new UnitOfMeasure();
		model.setId(LONG_VALUE);
		
		Set<UnitOfMeasure> set = new HashSet<UnitOfMeasure>();
		set.add(model);
		
		//when
		when(repo.findAll()).thenReturn(set);
		Set<UnitOfMeasureCommand> commandData = service.getAllUoms();
		
		//then
		assertNotNull(commandData);
		verify(repo, times(1)).findAll();

	}
	

}
