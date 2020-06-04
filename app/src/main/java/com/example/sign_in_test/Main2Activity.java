package com.example.sign_in_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.internal.cache.DiskLruCache;

public class Main2Activity extends AppCompatActivity {
   EditText key,value;
   TextView editText;
   Button button;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button=findViewById(R.id.save);
        key=findViewById(R.id.key);
        value=findViewById(R.id.value);
        editText=findViewById(R.id.tvg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePref sharePref=new SharePref(key.getText().toString(),value.getText().toString(),getApplicationContext());
                sharePref.Save();
                Toast.makeText(getApplicationContext(),"ute",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePref sharePref=new SharePref(key.getText().toString(),getApplicationContext());
                editText.setText(sharePref.Load().toString());


            }
        });
    }
}
