package com.example.sign_in_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String url="http://192.168.1.4:8000/signup/";
    TextView tv;
    EditText pass, user;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        pb=findViewById(R.id.progressBar2);
        pb.setVisibility(View.VISIBLE);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client=new OkHttpClient();
                HashMap<String,String> header =new HashMap<>();
                header.put("nameOfheader","Accept");
                header.put("valueOfheader","application/json");
                RequestforServer request=new RequestforServer(client,url,header);
                try {
                    client.newCall(request.GetMethod()).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                          if (response.isSuccessful()) {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            tv.setText(response.body().string());
                                        } catch (IOException e) {
                                            Log.i("bardia1",e.getMessage());
                                        }
                                    }
                                });
                          }


                          }
                    });

                } catch (IOException e) {
                   Log.i("bardia2",e.getMessage());
                }
            }
        });




    }
}
