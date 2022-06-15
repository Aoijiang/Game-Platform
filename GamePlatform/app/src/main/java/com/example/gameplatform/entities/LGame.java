package com.example.gameplatform.entities;

public class LGame {
    private String index;
    private String name;
    private String describe;
    private String type;
    private String rating;
    private String icon;
    private String packagename;


    public LGame() {
    }

    public LGame(String index, String name, String describe, String type, String rating, String icon, String packagename) {
        this.index = index;
        this.name = name;
        this.describe = describe;
        this.type = type;
        this.rating = rating;
        this.icon = icon;
        this.packagename = packagename;
    }

    public LGame(LGame game) {
        this.index = game.index;
        this.name = game.name;
        this.describe = game.describe;
        this.type = game.type;
        this.rating = game.rating;
        this.icon = game.icon;
        this.packagename = game.packagename;
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

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    @Override
    public String toString() {
        return "LGame{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                ", icon='" + icon + '\'' +
                ", packagename='" + packagename + '\'' +
                '}';
    }
}
