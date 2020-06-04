package com.example.sign_in_test;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharePref {

   private String key;
   private String value;
   Context context;

        public SharePref(String key, String value, Context context) {
            this.key = key;
            this.value = value;
            this.context=context;
        }

        public SharePref() {
        }

        public SharePref(String key,Context context) {
            this.key = key;
            this.context=context;
        }



                public boolean Save()
                        {
       SharedPreferences.Editor f=context.getSharedPreferences("kos",MODE_PRIVATE).edit();

       f.putString(key,value);

        f.apply();

return true;
                        }

                        public Object Load()
                        {
                            SharedPreferences pt=context.getSharedPreferences("kos",MODE_PRIVATE);


                            return  pt.getString(key,"fucku");
                        }

}
