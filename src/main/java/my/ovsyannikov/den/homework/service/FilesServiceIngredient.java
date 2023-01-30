package my.ovsyannikov.den.homework.service;
import java.io.File;

public interface FilesServiceIngredient {



    boolean saveToFile(String json);

    String readFromFile();


    File getIngredientFile();


    boolean cleanIngredientFile();
}
