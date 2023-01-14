package my.ovsyannikov.den.homework.service;

import my.ovsyannikov.den.homework.model.Ingredient;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    List<Ingredient> getAll();

    Ingredient update(long id, Ingredient ingredient);

    Ingredient remove(long id);
}
