package microsevice.connected.microseviceconnected;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class MicroseviceConnectedApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroseviceConnectedApplication.class, args);
	}
}
