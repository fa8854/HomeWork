package my.ovsyannikov.den.homework.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import my.ovsyannikov.den.homework.model.Ingredient;
import my.ovsyannikov.den.homework.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final Map<Long, Recipe> recipeMap = new HashMap<>();

    public long counter = 0;
    private final Path path;
    private final ObjectMapper objectMapper;

    public RecipeServiceImpl(@Value("${application.file.recipes}") String path){
        try{
            this.objectMapper = new ObjectMapper();
            this.path = Paths.get(path);
        }catch (InvalidPathException e){
            e.printStackTrace();
            throw e;
        }
    }


    @PostConstruct
    public void init(){
     readDataFromFile();
    }

    private void readDataFromFile(){
        try {
            byte [] file = Files.readAllBytes(path);
            Map<Long, Recipe> mapFromFile = objectMapper.readValue(file, new TypeReference<>() {
            });
            recipeMap.putAll(mapFromFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeDataToFile(Map<Long, Recipe> recipeMap){
        try {
            byte [] bytes = objectMapper.writeValueAsBytes(recipeMap);
            Files.write(path, bytes);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public Recipe add(Recipe recipe) {
        Recipe newRecipe = recipeMap.put(this.counter++, recipe);
        writeDataToFile(recipeMap);
        return newRecipe;
    }

    @Override
    public Recipe get(Long id) {
        return recipeMap.get(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipeMap.values());
    }

    @Override
    public Recipe update(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            Recipe newRecipe = recipeMap.put(id, recipe);
            writeDataToFile(recipeMap);
            return newRecipe;
        }
        return null;
    }

    @Override
    public Recipe remove(long id) {
        Recipe recipe = recipeMap.remove(id);
        writeDataToFile(recipeMap);
        return recipe;
    }

    @Override
    public byte[] getAllInByte() {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
             e.printStackTrace();
        }
        return  null;
    }

    @Override
    public void importRecipes(MultipartFile recipes) {
        try {
            Map<Long, Recipe> mapFromRequest = objectMapper.readValue(recipes.getBytes(),
                    new TypeReference<>() {
            });
            writeDataToFile(mapFromRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
