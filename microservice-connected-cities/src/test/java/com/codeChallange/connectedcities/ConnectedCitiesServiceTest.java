package com.codeChallange.connectedcities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.services.ConnectedCitiesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConnectedCitiesApplication.class)
public class ConnectedCitiesServiceTest {
	@Autowired
	ConnectedCitiesService connectedCitiesService;
	
	@Test
	public void isConnected() {
		Graph graph = new Graph();
		graph.addEdge("new york", "Newark");
		graph.addEdge("new Jersey", "Newark");
		graph.addEdge("Austin", "Dallas");
		graph.addEdge("Dallas", "Houston");
		
		assertTrue(connectedCitiesService.findPath(graph, "Austin", "Dallas"));
		assertFalse(connectedCitiesService.findPath(graph, "New York", "Dallas"));
		assertFalse(connectedCitiesService.findPath(graph, "New York", ""));
	}
	

}
