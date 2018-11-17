package com.codeChallange.connectedcities.controller;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.Graph;
import com.codeChallange.connectedcities.interfaces.IConnectedCities;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConnectedCitiesController {

	private static Logger restLogger = LogManager.getLogger(Constants.REST_LOGGER_NAME);

	@Autowired
	private IConnectedCities connectedCityService;

	private static File inputFile = null;
	private static Graph graph = null;

	@ApiOperation(value = "Returns if two cities connected or not.", response = Boolean.class)
	@RequestMapping(method = RequestMethod.GET, value = "/connectedCities")
	public String connectedCities(@RequestParam("origin") String src, @RequestParam("destination") String dest)
			throws Exception {
		// throw bad request Exception if either of input cities not populated
		if (StringUtils.isEmpty(src) || StringUtils.isEmpty(dest)) {
			return Constants.NO_STRING;
		}

		//Load input file and populate graph only if it's already not populated
		if (inputFile == null || graph==null) {
			try {
				// Read Input file with city pairs from classpath
				inputFile = connectedCityService.loadInputFile();

				/*Populate graph adjacency list data structure so that it can be used further
				 * to find the path from src city to dest city
				 */
				graph = connectedCityService.populateGraph(inputFile);
			} catch (IOException ie) {
				ie.printStackTrace();
				return Constants.NO_STRING; // If no input file, that means no path between cities.
			}
		}

		// Call service to find path between two cities using Graph adjacency list
		// breadth-first search
		if(connectedCityService.findPath(graph, src, dest))
			return Constants.YES_STRING;
		return Constants.NO_STRING;
	}
}
