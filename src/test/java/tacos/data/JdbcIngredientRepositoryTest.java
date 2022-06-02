package tacos.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import tacos.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Sql({"classpath:schema.sql"})
public class JdbcIngredientRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private IngredientRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new JdbcIngredientRepository(jdbcTemplate);
    }

    @Test
    public void shouldReturnEmpty() {
        List<Ingredient> ingredients = new ArrayList<>();
        repository.findAll().forEach(ingredients::add);

        assertEquals(0, ingredients.size());
    }

    @Test
    public void shouldReturnOneIngredient() {
        repository.save(new Ingredient("1", "myIngredient", Ingredient.Type.CHEESE));
        List<Ingredient> ingredients = new ArrayList<>();
        repository.findAll().forEach(ingredients::add);

        assertEquals(0, ingredients.size());
    }
}
