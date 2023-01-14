package my.ovsyannikov.den.homework.model;
import java.util.*;

public class Recipe {

    private final String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe(String name, int cookingTime, List<Ingredient> ingredients, List<String> steps) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

}
