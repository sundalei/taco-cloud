package tacos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    private String name;

    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(IngredientRef ingredient) {
        this.ingredients.add(ingredient);
    }
}
