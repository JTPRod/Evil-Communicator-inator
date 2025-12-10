package evil.doofenshmirtz.evilcommunicatorinator;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvilCommunicatorInatorApplication {
	public static void main(String[] args) {
        Message.initialiseProfanity(); // pull profanity list from internet to avoid local storage
		SpringApplication.run(EvilCommunicatorInatorApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:8081").allowedMethods("*");
//
//		};
//	}

}