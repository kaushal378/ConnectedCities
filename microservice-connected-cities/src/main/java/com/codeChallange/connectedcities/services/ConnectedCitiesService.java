package com.codeChallange.connectedcities.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.interfaces.IConnectedCitiesService;
import com.codeChallange.connectedcities.interfaces.IGraphOperations;
import com.codeChallange.connectedcities.interfaces.IReadInput;

@Service
public class ConnectedCitiesService implements IConnectedCitiesService{
	
	@Autowired
	IReadInput inputReader;
	
	@Autowired
	IGraphOperations graphOperations;
	
	@Value("${read.inputs.from.file}")
	private String readFileIndicator;
	
	private static File inputFile = null;
	
	private static Graph graph = null;
	
	//This method loads input file from classpath and returns it
	@Override
	public void loadInputFile() throws IOException{
		if(inputFile==null)
			inputFile = inputReader.loadInputFile();
	}
	
	/**** Below method populates graph adjacency list where each city is the vertex and 
	 * the list contains the cities directly connected from that vertex.
	 */
	@Override
	public Graph populateGraph() throws IOException {
		if(inputFile!=null && graph==null && Constants.Y_STRING.equals(readFileIndicator)) {
			graph = graphOperations.populateGraphFromFile(graph, inputFile);
		}
		return graph;
	}
	
	@Override
	public boolean findPath(Graph graph, String src, String dest) {
		if(graph==null)
			return false;
		graph.toString();
		
		//Call util method isPathBetweenCities to traverse graph adjacency list and find out path; 
		return graphOperations.isPathBetweenCities(graph, src, dest);
	}
	
	
}
