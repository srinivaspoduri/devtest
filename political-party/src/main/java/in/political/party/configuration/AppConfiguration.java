package in.political.party.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
	
	@Override
	public  void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
		.allowedMethods("GET","POST","DELETE","PUT");
	}

}
