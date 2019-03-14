package main;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SyncGet {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

}
