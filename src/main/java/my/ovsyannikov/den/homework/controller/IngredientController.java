package my.ovsyannikov.den.homework.controller;

import my.ovsyannikov.den.homework.model.Ingredient;
import my.ovsyannikov.den.homework.service.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;



import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;


    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    public Ingredient getIngredient(@PathVariable long id) {
        return this.ingredientService.get(id);
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.add(ingredient);
    }
    @GetMapping
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }
}
