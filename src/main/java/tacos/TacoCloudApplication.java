package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import tacos.client.IngredientClient;
import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;
import tacos.domain.IngredientType;

@SpringBootApplication
@Slf4j
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

    @Bean
    @Profile("!prod")
    CommandLineRunner dataLoader(IngredientRepository repository) {
        log.info("dataLoader bean is created.");
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

    @Bean
    CommandLineRunner ingredientClientTester(IngredientClient client) {
        return args -> {
            // get ingredient response object by id
            Ingredient ingredient = client.getIngredientResponseObjectById("FLTO");
            log.info(ingredient.toString());

            // get ingredient response entity by id
            ingredient = client.getIngredientResponseEntityById("SRCR");
            log.info(ingredient.toString());
        };
    }

}
