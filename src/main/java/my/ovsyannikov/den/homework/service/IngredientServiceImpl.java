package my.ovsyannikov.den.homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.ovsyannikov.den.homework.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    public long counter = 0;
    private final Path path;
    private final ObjectMapper objectMapper;

    public IngredientServiceImpl(@Value("${application.file.ingredients}") String path){
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
            Map<Long, Ingredient> mapFromFile = objectMapper.readValue(file, new TypeReference<>() {
            });
            ingredientMap.putAll(mapFromFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeDataToFile(){
        try {
        byte [] bytes = objectMapper.writeValueAsBytes(ingredientMap);
        Files.write(path, bytes);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public Ingredient add(Ingredient ingredient) {
        Ingredient newIngredient = ingredientMap.put(this.counter++, ingredient);
        writeDataToFile();
        return newIngredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            Ingredient newIngredient = ingredientMap.put(id, ingredient);
            writeDataToFile();
            return  newIngredient;
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        Ingredient ingredient = ingredientMap.remove(id);
        writeDataToFile();
        return ingredient;
    }


}
