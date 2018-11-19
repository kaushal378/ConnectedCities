package com.codeChallange.connectedcities.interfaces;

import java.io.File;
import java.io.IOException;

import com.codeChallange.connectedcities.dao.Graph;

//Interface exposing the business logic of microservice
public interface IConnectedCitiesService {
	public void loadInputFile() throws IOException;
	public Graph populateGraph() throws IOException;
	public boolean findPath(Graph graph, String src, String dest);
}
