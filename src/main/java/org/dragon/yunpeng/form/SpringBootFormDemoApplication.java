package org.dragon.yunpeng.form;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.dragon.yunpeng.form" })
@EnableJpaRepositories(basePackages = "org.dragon.yunpeng.form.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "org.dragon.yunpeng.form.entities")
public class SpringBootFormDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFormDemoApplication.class, args);

		openHomePage();
	}

	// Launch browser and open home page after Spring Boot application starts.
	private static void openHomePage() {
		try {
			URI homepage = new URI("http://localhost:8081/SpringBoot_Form/forms");

			System.setProperty("java.awt.headless", "false");

			Desktop.getDesktop().browse(homepage);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
