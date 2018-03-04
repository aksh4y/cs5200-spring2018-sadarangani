package edu.northeastern.cs5200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import edu.northeastern.cs5200.model.ExecutableCommands;

@SpringBootApplication
public class Cs5200Spring2018SadaranganiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Cs5200Spring2018SadaranganiApplication.class);
	 }
	public static void main(String[] args) {
		SpringApplication.run(Cs5200Spring2018SadaranganiApplication.class, args);
		ExecutableCommands exec = new ExecutableCommands();
		exec.execute();			
	}
}
