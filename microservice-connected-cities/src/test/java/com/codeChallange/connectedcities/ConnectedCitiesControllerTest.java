package com.codeChallange.connectedcities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeChallange.connectedcities.controller.ConnectedCitiesController;
import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.services.ConnectedCitiesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConnectedCitiesApplication.class)
public class ConnectedCitiesControllerTest {
	
	@Autowired
	ConnectedCitiesController connectecCitiesController;
	
	@Test
	public void connected() {
		assertEquals("no", connectecCitiesController.connectedCities("", "NewArk")); //if src city not snet in input
		assertEquals("no", connectecCitiesController.connectedCities("New York", "")); //if dest city not snet in input
	}
}
