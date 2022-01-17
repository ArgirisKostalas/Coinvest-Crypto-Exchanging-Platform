package core.CoinvestEE;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * Created with love by Giannis Anastasopoulos, Anargyros Kostalas, Markos Alexandros Veikos
 * <3 <3 <3 <3
 * 30/06/2021
 * **/
@SpringBootApplication
@EnableJSONDoc
public class CoinvestEeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinvestEeApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
