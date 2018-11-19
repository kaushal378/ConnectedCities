package com.codeChallange.connectedcities.dao;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.interfaces.IGraph;



@Component
public class Graph implements IGraph{
	
	private Map<String, Set<String>> hm;
	
	public Graph() {
		hm = new HashMap<String,Set<String>>();
	}
	
	/*public Graph(File inputFile, String delimiter) throws IOException {
		hm = new HashMap<String, Set<String>>();
		
		try {
			Scanner scanner = new Scanner(inputFile);
			while(scanner.hasNextLine()) {
				String[] inp = scanner.nextLine().split(delimiter);
				if(inp.length !=Constants.INPUT_LENGTH)
					continue;
				addEdge(inp[0].trim().toLowerCase(), inp[1].trim().toLowerCase());
			}
			scanner.close();
		}catch(IOException io) {
			throw new IOException();
		}
	}*/
	
	public void addEdge(String src, String dest) {
		src = src.toLowerCase();
		dest = dest.toLowerCase();
		if(!hasVertex(src))
			addVertex(src);
		if(!hasVertex(dest))
			addVertex(dest);
		hm.get(src).add(dest);
		hm.get(dest).add(src);
	}
	
	private void addVertex(String v) {
		if(!hasVertex(v)) 
			hm.put(v, new HashSet<String>());
	}
	
	public boolean hasVertex(String v) {
		return hm.containsKey(v);
	}
	
	public int getNumberOfVertices() {
		return hm.size();
	}
	
	public Map<String, Set<String>> getAdjList() {
		return hm;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String key:hm.keySet()) {
			sb.append(key+": ");
			for(String s:hm.get(key)) {
				sb.append(s+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
