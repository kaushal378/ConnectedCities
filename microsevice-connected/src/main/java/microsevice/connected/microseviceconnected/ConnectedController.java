package microsevice.connected.microseviceconnected;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConnectedController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;

	
	@RequestMapping(method = RequestMethod.GET, value = "/connected")
	public String connected(@RequestParam("origin") String src, @RequestParam("destination") String dest) throws Exception{
		Map<String, String> uriVariables = new HashMap<>();
	    uriVariables.put("origin", src);
	    uriVariables.put("destination", dest);

	    ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://connected-cities/connectedCities?origin={origin}&destination={destination}", String.class,
	        uriVariables);
	    
	    return responseEntity.getBody();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
