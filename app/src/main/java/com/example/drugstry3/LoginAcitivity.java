package com.example.drugstry3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginAcitivity extends AppCompatActivity {

    public static final String myPreferences = "myPreferences";
    public static final String PID = "PID";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;

    EditText editTextPhone;
    EditText editTextPassword;
    Button loginButton;
    RequestQueue queue;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);

        ///
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editTextPhone = findViewById(R.id.phone);
        editTextPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        queue = Volley.newRequestQueue(LoginAcitivity.this);



        ///
         url = "http://durgs.robotic-mind.com/WebService.asmx/loginPharma?phone=";


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();

                if(phoneNumber.isEmpty()|| password.isEmpty()){
                    Toast.makeText(LoginAcitivity.this, "Enter phone and password",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    url = url + phoneNumber + "&password=" + password;
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                            null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                String res;
                                JSONObject jsonObject = response.getJSONObject(0);

                                if(jsonObject.get("message").equals("Success")){
                                    JSONObject jsonObject1 = response.getJSONObject(1);
                                    res ="PID = " + jsonObject1.get("PID");
                                    sharedPreferences.getString("PID", jsonObject1.get("PID").toString());
                                }
                                else{
                                    res ="phone and password are not compatible";
                                }
                                Toast.makeText(LoginAcitivity.this, res, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();Toast.makeText(LoginAcitivity.this, "catched", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginAcitivity.this, "onErrorResponse", Toast.LENGTH_SHORT).show();
                        }
                    });


                    queue.add(jsonArrayRequest);
                }
            }
        });

    }
}