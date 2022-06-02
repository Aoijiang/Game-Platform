package com.example.gameplatform;

import com.example.gameplatform.entities.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

public class GsonTest {
//    @Test
//    public void gameFull() {
//        GsonBuilder builder=new GsonBuilder();
//        Gson gson= builder.create();
//        String str= gson.toJson(new Game("lee","des","syp","9.9","icon",1));
//        System.out.print(str);
//    }
@Test
public void jsonToGame() {
    String str="{\n" +
            "\"name\": \"ori\",\n" +
            "\"describe\": \"这是一款主机游戏，受到好评\",\n" +
            "\"type\": \"冒险 · 解密· 战斗\",\n" +
            "\"rating\": 9.8,\n" +
            "\"cover\": \"game_4\",\n" +
            "\"icon\": \"ori\",\n" +
            "\"available\": 1\n" +
            "}";
    Gson gson=new Gson();
    Game g=gson.fromJson(str,new TypeToken<Game>(){}.getType());
    System.out.print(g);
}

    @Test
    public void gameEmpty() {
        GsonBuilder builder=new GsonBuilder();
        Gson gson= builder.create();
        String str= gson.toJson(new Game());
        System.out.print(str);
    }
}
