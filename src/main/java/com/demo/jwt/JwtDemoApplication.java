package com.demo.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
//@EnableSwagger2
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//
//				.apis(RequestHandlerSelectors.basePackage("com.demo.jwt")).paths(PathSelectors.any()).build();
//	}
//
//	@Bean
//	public ApiInfo apiInfo() {
//		final ApiInfoBuilder builder = new ApiInfoBuilder();
//		builder.title("My Application API through Swagger UI").version("1.0").license("(C) Copyright Test")
//				.description("List of all the APIs of My Application App through Swagger UI");
//		return builder.build();
//	}
}
