package my.ovsyannikov.den.homework.service;

import my.ovsyannikov.den.homework.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private final Map<Integer, Recipe> recipes = new HashMap<>();

    public Recipe addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getId())) {
            throw new RuntimeException("Такой элемент уже существует");
        } else {
            recipes.put(recipe.getId(), recipe);
        }
        return recipe;
    }

    public Recipe getRecipeById(Integer id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }



}