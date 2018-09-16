package com.madhu.recipe.Repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.madhu.recipe.Model.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository uom;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Optional<UnitOfMeasure> unit = uom.findByDescription("Teaspoon");
		assertEquals("Teaspoon", unit.get().getDescription());
	}
}
