package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;
import tacos.domain.IngredientType;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	CommandLineRunner dataLoader(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
		};
	}

}
