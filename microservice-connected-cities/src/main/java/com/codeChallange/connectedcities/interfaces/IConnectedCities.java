package com.codeChallange.connectedcities.interfaces;

import java.io.File;
import java.io.IOException;

import com.codeChallange.connectedcities.dao.Graph;

public interface IConnectedCities {
	public File loadInputFile() throws IOException;
	public Graph populateGraph(File file) throws IOException;
	public boolean findPath(Graph graph, String src, String dest);
}
