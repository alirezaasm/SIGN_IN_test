package com.example.sign_in_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    String url="https://tabeshma.000webhostapp.com/mysites/showparams.php";
   TextView tv;
   EditText pass, user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client=new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("username", user.getText().toString().trim())
                        .add("password",pass.getText().toString().trim())
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
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
        });




    }
}
