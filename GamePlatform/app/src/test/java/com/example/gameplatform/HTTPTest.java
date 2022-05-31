package com.example.gameplatform;

import androidx.annotation.NonNull;

import com.example.gameplatform.entities.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPTest {
    @Test
    public void getTest() {
//        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://127.0.0.1:5000/games").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("Fail");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                System.out.println(response.body());
            }
        });

    }
}
