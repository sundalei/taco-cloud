package tacos.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {
	
	private final IngredientRepository repo;

	public IngredientController(IngredientRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return repo.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
		return repo.save(ingredient);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngredient(@PathVariable("id") String ingredientId) {
		repo.deleteById(ingredientId);
	}
}
