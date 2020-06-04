package com.example.sign_in_test;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestforServer {

    //require params
    private OkHttpClient client;
    private String url_path;
    private HashMap<String, String> info;


        //constructors
        public RequestforServer() {
        }

        public RequestforServer(OkHttpClient client, String url_path) {
        //set params
            this.client = client;
            this.url_path = url_path;
        }

        public RequestforServer(OkHttpClient client,String url_path, HashMap<String, String> info) {
        //set params
        this.client = client;
        this.url_path = url_path;
        this.info = info;
    }


                //use post method in okhttpclient
                public Request PostMethod() throws IOException
                {


                            client=new OkHttpClient();
                            RequestBody requestBody;
                            FormBody.Builder fromBuilder=new FormBody.Builder();
                            //set values to sending map
                            for (Map.Entry<String, String> entry:info.entrySet())
                            {
                                if(entry.getKey()=="nameOfheader"||entry.getKey()=="valueOfheader") continue;
                                fromBuilder.add(entry.getKey(),entry.getValue());
                            }
                            //create post builder
                            requestBody=fromBuilder.build();
                            //send request to server
                            final Request request=new Request.Builder()
                                    .url(url_path)
                                    .post(requestBody)
                                    .addHeader(info.get("nameOfheader"),info.get("valueOfheader"))
                                    .build();

                //return request for getting response in main class
                return request;
                }

                //get method in okhttpclient
                public Request GetMethod() throws IOException
                {

                            client=new OkHttpClient();
                             //send request to server
                            Request request=new Request.Builder()
                                    .addHeader(info.get("nameOfheader"),info.get("valueOfheader"))
                                    .url(url_path)
                                    .build();

                            //return request for getting response in main class

                            return request;
                }



                        //Observe the clean code
                        public OkHttpClient getClient() {
                            return client;
                        }

                        public String getUrl_path() {
                            return url_path;
                        }

                        public HashMap<String, String> getInfo() {
                            return info;
                        }
}
