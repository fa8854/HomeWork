package my.ovsyannikov.den.homework.model;
import java.util.*;

public class Recipe {

    private final Integer id;
    private final String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe(Integer id, String name, int cookingTime, List<Ingredient> ingredients, List<String> steps) {
        this.id = id;
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

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getId() {
        return id;
    }

}