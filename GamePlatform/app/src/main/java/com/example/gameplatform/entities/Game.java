package com.example.gameplatform.entities;

public class Game {
    private String index;
    private String name;
    private String describe;
    private String type;
    private String rating;
    private String icon;


    public Game() {
    }

    public Game(String index, String name, String describe, String type, String rating, String icon) {
        this.index = index;
        this.name = name;
        this.describe = describe;
        this.type = type;
        this.rating = rating;
        this.icon = icon;
    }

    public Game(Game game) {
        this.index = game.index;
        this.name = game.name;
        this.describe = game.describe;
        this.type = game.type;
        this.rating = game.rating;
        this.icon = game.icon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Game{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
