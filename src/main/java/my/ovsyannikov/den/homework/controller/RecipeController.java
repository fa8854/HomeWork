package my.ovsyannikov.den.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.ovsyannikov.den.homework.model.Recipe;
import my.ovsyannikov.den.homework.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;



@RestController
@Tag(name = "Рецепты", description = "CRUD")
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("{id}")
    @Operation(summary = "Поиск рецепта", description = "искать рецепт по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "рецепт найден")})
    public Recipe getRecipe(@PathVariable long id) {
        return this.recipeService.get(id);
    }


    @PostMapping
    @Operation(summary = "Добавление рецепта",
            description = "для добавления рецепта требуется названиие, время приготовления, список ингредиентов и шаги приготовления")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "рецепт был успешно добавлен",
            content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта",
            description = "можно редактировать по id как один параметр, так и несколько в том числе название, время приготовления, список ингредиентов и шаги приготовления")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "рецепт был отредактирован")})

    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта",
            description = "можно удалить рецепт только по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "рецепт успешно удален")})
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping
    @Operation(summary = "Получение  всех рецептов",
            description = "получение списка List всех рецептов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "рецепты были успешно получены",
            content = {@Content(mediaType = "формат List")})})
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

}
