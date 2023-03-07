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
    CommandLineRunner dataLoader(IngredientRepository repository) {
        return args -> {
            repository.save(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
            repository.save(new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
            repository.save(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
            repository.save(new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
            repository.save(new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES));
            repository.save(new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
            repository.save(new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
            repository.save(new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
            repository.save(new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
            repository.save(new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
        };
    }

}
