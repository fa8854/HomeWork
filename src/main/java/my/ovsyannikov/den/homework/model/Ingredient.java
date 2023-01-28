package my.ovsyannikov.den.homework.model;


import lombok.Data;

@Data
public class Ingredient {

    private String name;

    private int count;

    private String measurement;

    @Override
    public String toString(){
        return name + " - " + count + " - " + measurement;
    }





}
