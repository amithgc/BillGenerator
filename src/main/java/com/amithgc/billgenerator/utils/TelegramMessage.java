package com.amithgc.billgenerator.utils;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class TelegramMessage {

    public static void sendMessage(String message) {
        String targetURL = "https://api.telegram.org/bot" + Constants.API_KEY + "/sendMessage?chat_id=" + Constants.USER_CHAT_ID + "&text=" + message + "&parse_mode=html";

        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url(targetURL)
                    .get()
                    .build();
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendFile(String file, String fileName) {
        String targetURL = "https://api.telegram.org/bot" + Constants.API_KEY + "/sendDocument?chat_id=" + Constants.USER_CHAT_ID;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("document", fileName,
                            RequestBody.create(MediaType.parse("application/octet-stream"),
                                    new File(file)))
                    .build();
            Request request = new Request.Builder()
                    .url(targetURL)
                    .method("POST", body)
                    .build();

            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
