package my.ovsyannikov.den.homework.service;
import my.ovsyannikov.den.homework.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final Map<Long, Recipe> recipeMap = new HashMap<>();

    public long counter = 0;


    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++, recipe);
        return recipe;
    }

    @Override
    public Recipe get(Long id) {
        return recipeMap.get(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipeMap.values());
    }
    @Override
    public Recipe update(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.put(id, recipe);
        }
        return null;
    }

    @Override
    public Recipe remove(long id) {
        return recipeMap.remove(id);
    }
}
