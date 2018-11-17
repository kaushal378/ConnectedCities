package com.codeChallange.connectedcities.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.codeChallange.connectedcities.dao.Graph;

public class GraphUtils {
	
	/****** This method traverses graph in breadth first search manner and returns true when dest city found 
	 * while traversing from src city. If dest city not found, it returns false. *******/
	public boolean isPathBetweenCities(Graph graph, String src, String dest) {
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
}
