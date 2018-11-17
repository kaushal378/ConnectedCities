package com.codeChallange.connectedcities.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.interfaces.IConnectedCities;
import com.codeChallange.connectedcities.utils.GraphUtils;

@Service
public class ConnectedCitiesService implements IConnectedCities{
	
	@Value("${input.file}")
	private String fileName;
	
	//This method loads input file from classpath and returns it
	@Override
	public File loadInputFile() throws IOException{
		File file = File.createTempFile("city", ".txt");
		try {
			System.out.println(fileName);
			ClassPathResource classPathResource = new ClassPathResource(fileName);
			InputStream in = classPathResource.getInputStream();
			
			file.deleteOnExit();
			
			FileOutputStream out = new FileOutputStream(file);
			IOUtils.copy(in, out);
			
			out.close();
		}catch(IOException ie) {
			ie.printStackTrace();
			throw ie;
		}
		
		return file;
	}
	
	/**** Below method populates graph adjacency list where each city is the vertex and 
	 * the list contains the cities directly connected from that vertex.
	 */
	@Override
	public Graph populateGraph(File inputFile) throws IOException {
		Graph graph = new Graph();
		try {
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
		//System.out.println(graph.toString());
		return graph;
	}
	
	@Override
	public boolean findPath(Graph graph, String src, String dest) {
		GraphUtils graphUtils = new GraphUtils();
		
		//Call util method isPathBetweenCities to traverse graph adjacency list and find out path; 
		return graphUtils.isPathBetweenCities(graph, src, dest);
	}
	
	
}
