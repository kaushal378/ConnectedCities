package com.codeChallange.connectedcities.interfaces;

import java.io.File;
import java.io.IOException;

import com.codeChallange.connectedcities.dao.Graph;

public interface IGraphOperations {
	public Graph populateGraphFromFile(Graph graph, File inputFile) throws IOException;
	public boolean isPathBetweenCities(Graph graph, String src, String dest);
}
