package com.andrei.datacore.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.andrei.datacore.model.FileInformation;
import com.andrei.datacore.service.FileInformationService;

public class FileInformationServiceImplTest {

	FileInformationService fileInformationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		fileInformationService = new FileInformationServiceImpl();
	}

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void createFileInformationTest() {
		// given
		String name = "teste";
		Date now = new Date();

		// when
		FileInformation fileInformation = fileInformationService.createFileInformation(name, now);

		// then
		assertEquals(name, fileInformation.getName());
		assertEquals(now, fileInformation.getCreatedAt());

	}
	
	
	

}
