package model.entity.enumeration;

import java.util.Objects;

public enum Color {
    BLACK("black"), RED("red"), YELLOW("yellow"), GREEN("green"),
    BLUE("blue"), WHITE("white"), BROWN("brown"), CYAN("cyan");

    private String color;

    Color(String color){
        this.color = color;
    }

    Color(){}

    public String getColorName(){
        return color;
    }

    public static Color getColors(String value){
        for(Color color : values())
            if(color.getColorName().equals(value)){
                return color;
            }
        throw new IllegalArgumentException();
    }
}