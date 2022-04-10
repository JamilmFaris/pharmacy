package com.example.drugstry3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.drugstry3.LanguageChange.LocaleHelper;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.go_login);
        registerButton = findViewById(R.id.go_register);
        //remove


        Intent intent = new Intent(this, doingStuff.class);

        //startActivity(intent);
        //remove

        Intent goRegister = new Intent(MainActivity.this, RegisterAcitivity.class);
        Intent goLogin = new Intent(MainActivity.this, LoginAcitivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goLogin);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goRegister);
            }
        });
    }

    private void setAppLocale(String localeCode){
        String languageToLoad  = localeCode; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}