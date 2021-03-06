package com.breeze.assignment_5_final;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHandler {
    public HTTPHandler() {
    }

    public String serviceCall(String requestURL)
    {
        String response=null;

        try{
            URL url=new URL(requestURL);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");






        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}


