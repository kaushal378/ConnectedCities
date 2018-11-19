package com.codeChallange.connectedcities.services;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.interfaces.IGraphOperations;
import com.codeChallange.connectedcities.utils.StringUtils;

@Service
public class GraphOperations implements IGraphOperations{
	
	/****** This method traverses graph in breadth first search manner and returns true when dest city found 
	 * while traversing from src city. If dest city not found, it returns false. *******/
	public boolean isPathBetweenCities(Graph graph, String src, String dest) {
		if(!_validateInputs(graph, src, dest))
			return false;
		//System.out.println(graph.toString());
		if(graph.getNumberOfVertices()==0)
			return false;

		Map<String, Set<String>> hm = graph.getAdjList();
		Queue<String> q = new LinkedList<>();
		q.add(src.toLowerCase());
		HashSet<String> visited = new HashSet<>();
		while(!q.isEmpty()) {
			String tmp = q.poll();
			visited.add(tmp);

			Set<String> set = hm.get(tmp);
			for(String elm:set)
				if(set!=null && set.contains(dest.toLowerCase()))
					return true;

			if(set!=null) {
				for(String elm:set) {
					if(!visited.contains(elm.toLowerCase()))
						q.add(elm);
				}
			}
		}
		return false;
	}
	
	private boolean _validateInputs(Graph graph, String src, String dest) {
		if(StringUtils.isEmpty(src) || StringUtils.isEmpty(dest) || graph==null || !graph.hasVertex(src.toLowerCase()) ||  !graph.hasVertex(dest.toLowerCase()))
			return false;
		return true;
	}
	
	public Graph populateGraphFromFile(Graph graph, File inputFile) throws IOException{
		try {
			graph = new Graph();
			Scanner scanner = new Scanner(inputFile);
			while(scanner.hasNextLine()) {
				String[] inp = scanner.nextLine().split(Constants.FILE_DELIMITER);
				if(inp.length !=Constants.INPUT_LENGTH)
					continue;
				graph.addEdge(inp[0].trim().toLowerCase(), inp[1].trim().toLowerCase());
			}
			scanner.close();
		}catch(IOException io) {
			throw new IOException();
		}
		
		return graph;
	}
}
