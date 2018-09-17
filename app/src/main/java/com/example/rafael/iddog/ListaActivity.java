package com.example.rafael.iddog;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String token;
    public static ArrayList<String> listaHusky = new ArrayList<>();
    public static ArrayList<String> listaHound = new ArrayList<>();
    public static ArrayList<String> listaPug = new ArrayList<>();
    public static ArrayList<String> listaLabrador = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        token = bundle.getString("token");

        getImageList("husky");
        getImageList("hound");
        getImageList("pug");
        getImageList("labrador");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void getImageList (final String category){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api-iddog.idwall.co/feed";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_LONG).show();

                        try {
                            JSONArray respostaLista = response.getJSONArray("list");
                            int i = 0;
                            while (i<respostaLista.length()&&category.equals("husky")){
                                String link = (String) respostaLista.get(i);
                                listaHusky.add(link);
                                i++;
                            }
                            while (i<respostaLista.length()&&category.equals("hound")){
                                String link = (String) respostaLista.get(i);
                                listaHound.add(link);
                                i++;
                            }
                            while (i<respostaLista.length()&&category.equals("pug")){
                                String link = (String) respostaLista.get(i);
                                listaPug.add(link);
                                i++;
                            }
                            while (i<respostaLista.length()&&category.equals("labrador")){
                                String link = (String) respostaLista.get(i);
                                listaLabrador.add(link);
                                i++;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", category);
                return params;
        }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", token);

                return params;
            }

        };
        queue.add(request);
    }


}
