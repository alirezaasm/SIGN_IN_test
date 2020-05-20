package com.example.sign_in_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String url="http://192.168.42.60:8000/signup/";
   TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);

        OkHttpClient client=new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("first_name","admin")
                .add("last_name","admin")
                .add("username","admin43564")
                .add("phone_number","4343443433")
                .add("grades[0]","1")
                .add("gender","True")

                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("Accept","application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("failed in sign_in",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if(response.isSuccessful())
                {
                    final String myresponse=response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                tv.setText(myresponse);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }

            }
        });


    }
}
