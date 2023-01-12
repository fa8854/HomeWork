package my.ovsyannikov.den.homework.controller;

import my.ovsyannikov.den.homework.model.Recipe;
import my.ovsyannikov.den.homework.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return this.recipeService.getRecipeById(id);
    }

    @GetMapping
    public Collection<Recipe> getAllRecipes() {
        return this.recipeService.getAllRecipes();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

}