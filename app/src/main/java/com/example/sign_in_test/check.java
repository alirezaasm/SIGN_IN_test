package com.example.sign_in_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class check extends AppCompatActivity
{
    String url="http://192.168.1.7:8000/api/token/";
    TextView tv,share;
    EditText pass, user;
    HashMap<String,String> hashMap;
    ProgressBar progressBar;
    String result="54444";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        tv=findViewById(R.id.tv);
        user=findViewById(R.id.user);
        share=findViewById(R.id.tv3);
        pass=findViewById(R.id.pass);
        hashMap=new HashMap<>();
        progressBar=findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient httpClient=new OkHttpClient();
                hashMap.put("username",user.getText().toString().trim());
                hashMap.put("password",pass.getText().toString().trim());
                hashMap.put("nameOfheader","Accept");
                hashMap.put("valueOfheader","application/json");


                RequestforServer requestforServer=new RequestforServer(httpClient,url,hashMap);

                progressBar.setVisibility(View.VISIBLE);
                try {

                    httpClient.newCall(requestforServer.PostMethod()).enqueue(new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {
                            tv.setText("null");

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            if(response.isSuccessful()) {
                                check.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        tv.setText(response.body().string());
                                        JSONObject jsonObject=new JSONObject(tv.getText().toString());
                                        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
                                        SharedPreferences prefs = getSharedPreferences("token", MODE_PRIVATE);
                                        editor.putString("token",jsonObject.getString("access"));
                                        String sh=prefs.getString("access","null");
                                        share.setText(jsonObject.getString("access"));


                                    } catch (IOException | JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });}
                            else if(response.code()==401)
                            {
                                check.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText("401");
                                    }
                                });
                            }
                            else {Log.i("aaa","ناموفق");}

                        }
                    });



                } catch (IOException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.INVISIBLE);


            }
        });




    }
}
