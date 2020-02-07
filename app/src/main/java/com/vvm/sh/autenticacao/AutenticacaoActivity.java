package com.vvm.sh.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vvm.sh.R;

public class AutenticacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);
    }


    private void iniciarApp(){


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", "string value");
        editor.commit();
    }
}
