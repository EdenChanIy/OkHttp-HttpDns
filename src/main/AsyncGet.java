package main;

import okhttp3.*;

import java.io.IOException;

public class AsyncGet {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();


        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {

            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });



    }
}
