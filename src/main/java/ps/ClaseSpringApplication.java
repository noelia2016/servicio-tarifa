package ps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClaseSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaseSpringApplication.class, args);
	}

//	/**
//	 * Este metodo es un bean para los servicios
//	 * @return RestTemplate
//	 */
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

}
