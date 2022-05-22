package pl.polsl.ProjektTab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class ProjektTabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektTabApplication.class, args);
	}
}
