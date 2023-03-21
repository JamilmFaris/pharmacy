package com.example.drugstry3.BottomNavigationPages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.drugstry3.Adapter.MyProfileAdapter;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.example.drugstry3.LoginAcitivity;
import com.example.drugstry3.Model.Bookmark;
import com.example.drugstry3.R;

import org.json.JSONException;

import java.util.ArrayList;

public class MyProfile extends Fragment {
    Context context;
    Resources resources;
    private Context mContext;
    ArrayList< Pair< Bookmark, String> >  data;

    RecyclerView recyclerView;
    String PID = "";
    public static String name , phone, telephone, password ;
    public static String pharmacyName, city, address, homeAddress ;

    public MyProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeLanguage("en");
        mContext = getContext();
        PID = LoginAcitivity.PID;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //Toast.makeText(getContext(), "PID " + PID, Toast.LENGTH_LONG).show();

        recyclerView = view.findViewById(R.id.myprofile_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        Bookmark personalTextBookmark = new Bookmark(0);
        Bookmark informationBookmark = new Bookmark(1);
        Bookmark passwordBookmark = new Bookmark(2);

        //dummy data
        name = "my name";
        phone = "my phone";
        telephone = "my other phone";
        password = "my password";


        pharmacyName = "my pharmacy name";
        city ="my cityName";
        address = "my address";
        homeAddress = "my homeAddress";
        data = new ArrayList<>();
        data.add(new Pair<>(personalTextBookmark, resources.getString(R.string.personal)));
        data.add(new Pair<>(informationBookmark, name));
        data.add(new Pair<>(informationBookmark, phone));
        data.add(new Pair<>(informationBookmark, telephone));
        data.add(new Pair<>(passwordBookmark, password) );

        data.add(new Pair<>(personalTextBookmark, resources.getString(R.string.pharmacy)));
        data.add(new Pair<>(informationBookmark, pharmacyName));
        data.add(new Pair<>(informationBookmark, city));
        data.add(new Pair<>(informationBookmark, address));
        data.add(new Pair<>(informationBookmark, homeAddress));

        recyclerView.setAdapter(new MyProfileAdapter(mContext, data));

        //get user info
//        String url = "http://durgs.robotic-mind.com/WebService.asmx/Pharmacy?level=selectOne&PID=" + PID;
//        JsonArrayRequest getUserInfo = new JsonArrayRequest(Request.Method.GET, url,
//                null,
//                response -> {
//                    try {
//                        name = response.getJSONObject(0).getString("name");
//                        phone = response.getJSONObject(0).getString("phone");
//                        telephone = response.getJSONObject(0).getString("phone2");
//                        password = response.getJSONObject(0).getString("password");
//
//
//                        pharmacyName = response.getJSONObject(0).getString("PName");
//                        city = response.getJSONObject(0).getString("cityName");
//                        address = response.getJSONObject(0).getString("address");
//                        homeAddress = response.getJSONObject(0).getString("homeAddress");
//                        data = new ArrayList<>();
//                        data.add(new Pair<>(personalTextBookmark, resources.getString(R.string.personal)));
//                        data.add(new Pair<>(informationBookmark, name));
//                        data.add(new Pair<>(informationBookmark, phone));
//                        data.add(new Pair<>(informationBookmark, telephone));
//                        data.add(new Pair<>(passwordBookmark, password) );
//
//                        data.add(new Pair<>(personalTextBookmark, resources.getString(R.string.pharmacy)));
//                        data.add(new Pair<>(informationBookmark, pharmacyName));
//                        data.add(new Pair<>(informationBookmark, city));
//                        data.add(new Pair<>(informationBookmark, address));
//                        data.add(new Pair<>(informationBookmark, homeAddress));
//
//                        recyclerView.setAdapter(new MyProfileAdapter(mContext, data));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                },
//                error -> Toast.makeText(getContext(),
//                        error.toString(), Toast.LENGTH_SHORT).show());
//        queue.add(getUserInfo);

    }

    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(getContext(), language);
        resources = context.getResources();
    }
}