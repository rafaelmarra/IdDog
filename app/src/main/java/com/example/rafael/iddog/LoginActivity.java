package com.example.rafael.iddog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    Bundle bundle = new Bundle();
    JSONObject userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editText);
    }

    public void clickTeste(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api-iddog.idwall.co/signup";


        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("email", email.getText().toString());

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            userObj = response.getJSONObject("user");
                            bundle.putString("_id", userObj.getString("_id"));
                            bundle.putString("email", userObj.getString("email"));
                            bundle.putString("token", userObj.getString("token"));
                            bundle.putString("createdAt", userObj.getString("createdAt"));
                            bundle.putString("updatedAt", userObj.getString("updatedAt"));
                            bundle.putString("_v", userObj.getString("_v"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                },

                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Email invalido", Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        queue.add(request);
    }
}
