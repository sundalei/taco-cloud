package tacos.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.Ingredient;

@Component
@Slf4j
public class IngredientClient {

    private final RestTemplate rest;
    
    public IngredientClient(RestTemplate rest) {
        this.rest = rest;
    }

    public Ingredient getIngredientResponseObjectById(String ingredientId) {

        final String url = "http://localhost:8080/data-api/ingredients/{id}";
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI httpUrl = UriComponentsBuilder.fromHttpUrl(url).build(urlVariables);
        
        Ingredient ingredient = rest.getForObject(httpUrl, Ingredient.class);
        log.info("ingredient: " + ingredient);
        return ingredient;
    }

    public Ingredient getIngredientResponseEntityById(String ingredientId) {

        final String url = "http://localhost:8080/data-api/ingredients/{id}";
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI httpUrl = UriComponentsBuilder.fromHttpUrl(url).build(urlVariables);

        ResponseEntity<Ingredient> responseEntity = rest.getForEntity(httpUrl, Ingredient.class);
        log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }
}
