package my.ovsyannikov.den.homework.service;

import my.ovsyannikov.den.homework.model.Recipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe get(Long id);

    List<Recipe> getAll();

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);

    void addRecipesFromInputStream(InputStream inputStream) throws IOException;

    File createRecipesTxtFile();
}