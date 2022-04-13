package com.example.drugstry3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.drugstry3.LanguageChange.LocaleHelper;
public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.go_login);
        registerButton = findViewById(R.id.go_register);
        changeLanguage("en");
        //remove

        loginButton.setText(resources.getString(R.string.login));
        registerButton.setText(resources.getString(R.string.register));

        Intent intent = new Intent(this, doingStuff.class);


        startActivity(intent);
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
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(MainActivity.this, language);
        resources = context.getResources();
    }
}