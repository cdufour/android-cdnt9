package com.cdnt9.demo;

public class Fruit {
    private String name;
    private boolean isSuperFruit;
    private int calories;

    public Fruit(String name, boolean isSuperFruit, int calories) {
        this.name = name;
        this.isSuperFruit = isSuperFruit;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuperFruit() {
        return isSuperFruit;
    }

    public void setSuperFruit(boolean superFruit) {
        isSuperFruit = superFruit;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
