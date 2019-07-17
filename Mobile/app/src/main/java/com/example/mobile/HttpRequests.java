package com.example.mobile;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequests extends Thread {
    public OkHttpClient client;
    public String resp;

    public String action = "get";

    public Info info;

    public HttpRequests() {
        client = new OkHttpClient();
    }

    public HttpRequests(Info info) {
        action = "post";
        this.info = info;
    }

    @Override
    public void run() {
        client = new OkHttpClient();
        try {
            if (action.equals("get")){
                Request request = new Request.Builder()
                        .url("http://193.111.0.203:81/getHrefs.php")
                        .build();
                Response response = client.newCall(request).execute();
                String serverAnswer = response.body().string();
                resp = serverAnswer;
            }else {
                RequestBody formBody = new FormBody.Builder()
                        .add("href",info.getHref())
                        .build();
                Request request = new Request.Builder()
                        .url("http://193.111.0.203:81/read.php")
                        .post(formBody)
                        .build();
                client.newCall(request).execute();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
