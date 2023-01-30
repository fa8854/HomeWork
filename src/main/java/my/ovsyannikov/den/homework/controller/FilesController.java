package my.ovsyannikov.den.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.ovsyannikov.den.homework.service.FilesServiceIngredient;
import my.ovsyannikov.den.homework.service.FilesServiceRecipe;
import my.ovsyannikov.den.homework.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Файлы", description = "Операции по работе с файлами рецептов и ингредиентов")
@RequestMapping("files")
public class FilesController {
    private final FilesServiceRecipe filesServiceRecipe;
    private final FilesServiceIngredient filesServiceIngredient;

    private final RecipeService recipeService;

    public FilesController(FilesServiceRecipe filesServiceRecipe, FilesServiceIngredient filesServiceIngredient, RecipeService recipeService) {
        this.filesServiceRecipe = filesServiceRecipe;
        this.filesServiceIngredient = filesServiceIngredient;
        this.recipeService = recipeService;
    }

    @GetMapping("export/recipes")
    @Operation(summary = "Скачать рецепты в виде json-файла")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно скачался"),
            @ApiResponse(responseCode = "400", description = "некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesServiceRecipe.getRecipeFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {

            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/export/recipes/txt")
    @Operation(summary = "Скачивание рецептов в формате txt",
            description = "можно скачать рецепты файлом txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "все хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<InputStreamResource> downloadRecipeTxtFile(){
        File downloadedFile = recipeService.createRecipesTxtFile();
        try {
            InputStreamResource stream = new InputStreamResource(new FileInputStream(downloadedFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(downloadedFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                    .body(stream);
        } catch (IOException e) {
            return ResponseEntity.noContent().build();
        }
    }




    @GetMapping("import/recipes")
    @Operation(summary = "Загрузить файл с рецептами", description = "принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "плохой запрос, отправлен некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        filesServiceRecipe.cleanRecipeFile();
        File fileRecipe = filesServiceRecipe.getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(fileRecipe)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping("import/ingredients")
    @Operation(summary = "Загрузить файл с ингредиентами", description = "принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "плохой запрос, отправлен некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        filesServiceIngredient.cleanIngredientFile();
        File fileIngredient = filesServiceIngredient.getIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(fileIngredient)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
