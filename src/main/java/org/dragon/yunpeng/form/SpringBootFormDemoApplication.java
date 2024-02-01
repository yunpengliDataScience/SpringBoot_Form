package org.dragon.yunpeng.form;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.dragon.yunpeng.form.services.ServerPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
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

	@Autowired
	private ServerPortService serverPortService;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootFormDemoApplication.class, args);

		// Access the Spring Boot main application instance
		// (SpringBootFormDemoApplication)
		SpringBootFormDemoApplication mainApplication = context.getBean(SpringBootFormDemoApplication.class);

		// Call method of the Spring Boot main application instance
		mainApplication.openHomePage();
	}

	// Launch browser and open home page after Spring Boot application starts.
	private void openHomePage() {
		try {
			int port = serverPortService.getPort();
			
			URI homepage = new URI("http://localhost:" + port + "/SpringBoot_Form");

			System.setProperty("java.awt.headless", "false");

			Desktop.getDesktop().browse(homepage);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
