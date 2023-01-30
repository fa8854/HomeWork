package my.ovsyannikov.den.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceRecipeImpl implements FilesServiceRecipe{
    @Value("${path.to.recipe.file}")
    private String recipeFilePath;
    @Value("${name.of.recipe.file}")
    private String recipeFileName;

    @Override
    public boolean saveRecipeToFile(String json) {
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readRecipeFromFile() {
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new ReadRecipeException();
        }
    }

    @Override
    public File getRecipeFile() {
        return new File(recipeFilePath + "/" + recipeFileName);
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(recipeFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new SaveRecipeException();
        }
    }


    @Override
    public boolean cleanRecipeFile() {
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
