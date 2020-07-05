package com.example.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {
    public static String inputSteam2String(InputStream inputStream) {
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String data = null;
        try {
            if((data = read.readLine()) != null){
                response.append(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
