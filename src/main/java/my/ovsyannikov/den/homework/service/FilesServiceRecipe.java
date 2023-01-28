package my.ovsyannikov.den.homework.service;
import java.io.File;
import java.nio.file.Path;

public interface FilesServiceRecipe {
    boolean saveRecipeToFile(String json);


    String readRecipeFromFile();

    File getRecipeFile();


    Path createTempFile(String suffix);


    boolean cleanRecipeFile();
}
