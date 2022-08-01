package com.example.rajendracarrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rajendracarrental.R;
import com.example.rajendracarrental.Volleylib;
import com.example.rajendracarrental.adapter.Newsadapter;
import com.example.rajendracarrental.container.Newscontainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News extends AppCompatActivity {

    RecyclerView news_rec_view;
    private List<Newscontainer> newsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        news_rec_view = (RecyclerView) findViewById(R.id.news_rec_view);

        getNewsList();
        LinearLayoutManager llm_news = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        news_rec_view.setLayoutManager(llm_news);

    }

    private void getNewsList() {
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                Log.d("data response",response);
                                JSONArray DATA = obj.getJSONArray("articles");
                                // Creating JSON Object.
                                JSONObject jsonObject;
                                for (int i = 0; i < DATA.length(); i++) {
                                    jsonObject = DATA.getJSONObject(i);
                                    String title = jsonObject.getString("title");
                                    String urlToImage = jsonObject.getString("urlToImage");
                                    Newscontainer newscontainer = new Newscontainer(title,urlToImage);
                                    newsList.add(newscontainer);
                                }
                                //creating custom adapter object
                                Newsadapter adapter = new Newsadapter(newsList);
                                news_rec_view.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = "Something went wrong with API or Netword, Internet";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }) { };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setShouldCache(false);
        Volleylib.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}