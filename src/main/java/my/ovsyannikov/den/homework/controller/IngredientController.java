package my.ovsyannikov.den.homework.controller;

import my.ovsyannikov.den.homework.model.Ingredient;
import my.ovsyannikov.den.homework.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        return this.ingredientService.getIngredientById(id);
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }
}