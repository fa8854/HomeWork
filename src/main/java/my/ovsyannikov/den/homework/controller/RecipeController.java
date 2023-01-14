package my.ovsyannikov.den.homework.controller;

import my.ovsyannikov.den.homework.model.Recipe;
import my.ovsyannikov.den.homework.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return this.recipeService.get(id);
    }


    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.add(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

}
