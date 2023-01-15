package my.ovsyannikov.den.homework.service;

import my.ovsyannikov.den.homework.model.Ingredient;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    public long counter = 0;



    @Override
    public Ingredient add(Ingredient ingredient) {
        ingredientMap.put(this.counter++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.put(id, ingredient);
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }


}
