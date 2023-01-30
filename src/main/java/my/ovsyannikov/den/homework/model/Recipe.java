package my.ovsyannikov.den.homework.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;


    public Recipe(String s, Integer valueOf, String s1) {
    }


    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(name).append("\n");
        buffer.append("Время: " + cookingTime + " мин.").append("\n");
        buffer.append("Ингредиенты: ").append("\n");
        for (Ingredient ingredient : ingredients) {
            buffer.append("\t").append(ingredient).append("\n");
        }
        buffer.append("Инструкция: ").append("\n");
        for (int i = 0; i < steps.size(); i++) {
            buffer.append(i + 1).append(". ").append(steps.get(i)).append("\n");
        }
        return buffer.toString();
    }
}
