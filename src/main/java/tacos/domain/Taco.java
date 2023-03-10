package tacos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Taco {

    private Long id;

    private Date createdAt = new Date();

    private String name;

    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(IngredientRef ingredient) {
        this.ingredients.add(ingredient);
    }
}
