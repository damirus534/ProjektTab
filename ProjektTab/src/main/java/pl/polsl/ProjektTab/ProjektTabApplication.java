package pl.polsl.ProjektTab;

import Model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class ProjektTabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektTabApplication.class, args);
	}

	@GetMapping
	public List<Product> products(){
		return List.of(
				new Product(1L, (short) 1, 32.3, 42.6, "Spodnie", "M", "Spodnie koloru zielonego", 1L)
				);
	}

}
