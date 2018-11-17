package com.codeChallange.connectedcities.interfaces;

import java.util.Map;
import java.util.Set;

public interface IGraph {
	public int getNumberOfVertices();
	public void addEdge(String src, String dest);
	public String toString();
	public Map<String, Set<String>> getAdjList();
}
