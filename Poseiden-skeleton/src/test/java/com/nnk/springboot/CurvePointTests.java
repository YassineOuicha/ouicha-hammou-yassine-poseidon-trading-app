package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		Integer generatedId = curvePoint.getId();

		Assert.assertNotNull(generatedId);
		Assert.assertTrue(generatedId > 0);
		Assert.assertEquals(10, curvePoint.getCurveId());

		// Update
		curvePoint.setCurveId(20);
		CurvePoint updatedCurvePoint = curvePointRepository.save(curvePoint);
		Assert.assertEquals(20, updatedCurvePoint.getCurveId());

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// FindById
		Optional<CurvePoint> foundCurvePoint = curvePointRepository.findById(generatedId);
		Assert.assertTrue(foundCurvePoint.isPresent());

		// Delete
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> deletedCurvePoint = curvePointRepository.findById(generatedId);
		Assert.assertFalse(deletedCurvePoint.isPresent());
	}
}