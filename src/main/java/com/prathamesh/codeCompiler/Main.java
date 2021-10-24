package com.prathamesh.codeCompiler;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private void compile(String code, String language,String input) throws Exception{

       JSONObject jsonObject = new JSONObject();
       jsonObject.put("code",code);
       jsonObject.put("language",language);
       jsonObject.put("input",input);

        System.out.println(jsonObject);
        RequestBody body = RequestBody.create(String.valueOf(jsonObject),MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://codexweb.netlify.app/.netlify/functions/enforceCode")
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        JSONObject op = new JSONObject(response.body().string());
        System.out.println("Output ->"+"\n"+op.getString("output"));
    }

    public static void main(String[] args)throws Exception {

//        String code = "print(\"Hello\") \n a=input(\"Enter a value\") \n print(a)";
        String lan = "java";
        String input = "Prathamesh Adate";

        String fileName = "D:\\Java-Code-Compiler\\src\\main\\java\\com\\prathamesh\\codeCompiler\\sample.java";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println(content);

        Main main = new Main();
        main.compile(content,lan,input);

    }
}
