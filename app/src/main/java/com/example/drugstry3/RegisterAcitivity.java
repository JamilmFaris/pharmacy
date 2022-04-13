package com.example.drugstry3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.drugstry3.LanguageChange.LocaleHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegisterAcitivity extends AppCompatActivity {
    Spinner dropDownCities;
    RequestQueue queue;
    Button nextButton;
    Button registerButton;
    Button backButton;
    ViewSwitcher viewSwitcher;
    EditText name;
    EditText phoneNumber;
    EditText telephoneNumber;
    EditText password;

    EditText pharmacyName;
    String cityId;
    EditText address;
    EditText additionalAddress;

    Map<String , String>citiesIds = new HashMap<>();

    String nameString;
    String PharmacyNameString;
    String passwordString;
    String addressString;
    String additionalAddressString;
    String phoneNumberString;
    String telephoneNumberString;
    String selectedCity;

    List<String> cities;

    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitivity);

        changeLanguage("ar");

        queue = Volley.newRequestQueue(this);
        viewSwitcher = findViewById(R.id.viewSwitcher);
        Animation inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phone_number);
        telephoneNumber = findViewById(R.id.telephone_number);
        password = findViewById(R.id.password);
        nextButton = findViewById(R.id.next_button);

        pharmacyName = findViewById(R.id.pharmacyname);
        address = findViewById(R.id.address);
        additionalAddress = findViewById(R.id.additionalAddress);
        dropDownCities = findViewById(R.id.cities);
        cities = new ArrayList<>();
        selectedCity = "اللاذقية";
        registerButton = findViewById(R.id.register_button);
        backButton = findViewById(R.id.back_button);

        //naming
        name.setText(resources.getString(R.string.name));
        phoneNumber.setText(resources.getString(R.string.phone_number));
        telephoneNumber.setText(resources.getString(R.string.telephone_number));
        password.setText(resources.getString(R.string.password));
        nextButton.setText(resources.getString(R.string.next));
        pharmacyName.setText(resources.getString(R.string.pharmacy_name));
        address.setText(resources.getString(R.string.address));
        additionalAddress.setText(resources.getString(R.string.additional_address));
        registerButton.setText(resources.getString(R.string.register));
        backButton.setText(resources.getString(R.string.back));


        ///
        viewSwitcher.setInAnimation(inAnimation);
        viewSwitcher.setOutAnimation(outAnimation);

        dropDownCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCity = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCities();

                /// edit texts strings

                if(name.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()
                        || telephoneNumber.getText().toString().isEmpty() || password.getText().toString().isEmpty() ){
                    Toast.makeText(RegisterAcitivity.this, "Enter some data",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    nameString = name.getText().toString();
                    phoneNumberString = phoneNumber.getText().toString();
                    telephoneNumberString = telephoneNumber.getText().toString();
                    passwordString = password.getText().toString();

                    viewSwitcher.showNext();

                }

            }
        });

        /// back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcher.showPrevious();
            }
        });

        /// register button

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pharmacyName.getText().toString().isEmpty() || address.getText().toString().isEmpty()
                        || additionalAddress.getText().toString().isEmpty() ){
                    Toast.makeText(RegisterAcitivity.this, "Enter some data",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    PharmacyNameString = pharmacyName.getText().toString();
                    addressString = address.getText().toString();
                    additionalAddressString = additionalAddress.getText().toString();
                    cityId = citiesIds.get(selectedCity);
                    postData();
                }
            }
        });



    }
    public void postData(){


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("level", "insert");
            jsonObject.put("id", "0");
        jsonObject.put("name",  nameString);//
        jsonObject.put("PName", PharmacyNameString);//
        jsonObject.put("notes", "");
        jsonObject.put("state", "");
        jsonObject.put("password",passwordString );//
        jsonObject.put("address", addressString );//
        jsonObject.put("homeAddress", additionalAddressString);//
        jsonObject.put("phone", phoneNumberString);//
        jsonObject.put("phone2", telephoneNumberString);//
        jsonObject.put("image", "k");
        jsonObject.put("CityID", cityId);//
        } catch (JSONException e) {
            e.printStackTrace();
        }
            String url = "http://durgs.robotic-mind.com/WebService.asmx/initPharma";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(RegisterAcitivity.this, "Registered", Toast.LENGTH_SHORT ).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterAcitivity.this, "error " + error.toString(), Toast.LENGTH_SHORT ).show();
                        }
                    });
            queue.add(jsonObjectRequest);


    }
    public void loadCities(){
        /// loading cities

        String url = "http://durgs.robotic-mind.com/WebService.asmx/City?level=select&id=";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0;i < response.length();i++){
                            try {
                                cities.add(response.getJSONObject(i).get("CtName").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(RegisterAcitivity.this,
                                        "could not load cities " + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(RegisterAcitivity.this,
                                android.R.layout.simple_spinner_item, cities);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dropDownCities.setAdapter(arrayAdapter);

                        ///get cities' ids
                        for(int i = 0;i < response.length();i++){
                            try {
                                citiesIds.put(response.getJSONObject(i).getString("CtName")
                                        , response.getJSONObject(i).getString("CityID"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterAcitivity.this
                                , "error when loading cities id" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(RegisterAcitivity.this, language);
        resources = context.getResources();
    }
}