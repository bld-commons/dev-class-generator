package bld.commons.yaml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bld.example.Typological;

@SpringBootApplication(scanBasePackages = "bld.commons")
public class UsePluginApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(UsePluginApplication.class, args);
		Typological<Number, String>test=new Typological<>();
	}


	
}
