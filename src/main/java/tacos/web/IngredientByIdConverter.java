package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;
import tacos.domain.IngredientUDT;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isEmpty()) {
            return null;
        } else {
            Ingredient ingredient = optionalIngredient.get();
            return new IngredientUDT(ingredient.getName(), ingredient.getType());
        }
    }

}
