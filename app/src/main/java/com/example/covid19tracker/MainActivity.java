package com.example.covid19tracker;


import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CoronaModelClass> coronaModelClassArrayList;
    private RequestQueue requestQueue;
    private TextView dailyDeaths, dailyConfirm, dailyReco, dateHeaders, totalDeath, totalConfirm,
            totalRecovered;
    private int INTERNET_PERMISSION_CODE = 1;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);





        dailyConfirm = findViewById(R.id.dailyConfirm);
        dailyDeaths = findViewById(R.id.dailyDeath);
        dailyReco = findViewById(R.id.dailyRecovered);
        dateHeaders = findViewById(R.id.dateHeader);

        totalRecovered = findViewById(R.id.totalRecovered);
        totalConfirm = findViewById(R.id.totalConfirm);
        totalDeath = findViewById(R.id.totalDeath);


        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coronaModelClassArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();





    }


    private void jsonParse() {

        String url = "https://api.covid19india.org/data.json";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    JSONArray todayAndTotalDataArray = response.getJSONArray("statewise");
                    JSONObject todayAndTotalDataJsonObject = todayAndTotalDataArray.getJSONObject(0);

                    String dailyConfirmed = "+" + todayAndTotalDataJsonObject.getString("deltaconfirmed");
                    String dailyDeath = "+" + todayAndTotalDataJsonObject.getString("deltadeaths");
                    String dailyRec = "+" + todayAndTotalDataJsonObject.getString("deltarecovered");
                    String dateHeader = todayAndTotalDataJsonObject.getString("lastupdatedtime").substring(0, 5);
                    dateHeader = getFormattedDate(dateHeader);

                    dailyConfirm.setText(dailyConfirmed);
                    dailyReco.setText(dailyRec);
                    dailyDeaths.setText(dailyDeath);
                    dateHeaders.setText(dateHeader);


                    String totalDeathsFetched = todayAndTotalDataJsonObject.getString("deaths");
                    String totalRecoverFetched = todayAndTotalDataJsonObject.getString("recovered");
                    String totalConfirmedFetched = todayAndTotalDataJsonObject.getString("confirmed");

                    totalConfirm.setText(totalConfirmedFetched);
                    totalDeath.setText(totalDeathsFetched);
                    totalRecovered.setText(totalRecoverFetched);



                    for (int i = 1; i < todayAndTotalDataArray.length(); i++) {
                        JSONObject stateWiseArrayJSONObject = todayAndTotalDataArray.getJSONObject(i);
                        Log.d("Praveen", "onResponse: "+stateWiseArrayJSONObject);
                        String active = stateWiseArrayJSONObject.getString("active");
                        String death = stateWiseArrayJSONObject.getString("deaths");
                        String recovered = stateWiseArrayJSONObject.getString("recovered");
                        String state = stateWiseArrayJSONObject.getString("state");
                        String confirmed = stateWiseArrayJSONObject.getString("confirmed");
                        String lastUpdated = stateWiseArrayJSONObject.getString("lastupdatedtime");


                        String todayActive = "+" + stateWiseArrayJSONObject.getString("deltaconfirmed");
                        String todayDeath = "+" + stateWiseArrayJSONObject.getString("deltadeaths");
                        String todayRecovered = "+" + stateWiseArrayJSONObject.getString("deltarecovered");


                        CoronaModelClass coronaItem = new CoronaModelClass(state, death, active, recovered, confirmed, lastUpdated, todayDeath, todayRecovered, todayActive);
                        coronaModelClassArrayList.add(coronaItem);
                    }


                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, coronaModelClassArrayList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);

    }



    private String getFormattedDate(String dateHeader) {
        Log.d("Prakash", "getFormattedDate: " + dateHeader.subSequence(3, 5));
        switch (dateHeader.substring(3, 5)) {
            case "01":
                return dateHeader.substring(0, 2) + " Jan";
            case "02":
                return dateHeader.substring(0, 2) + " Feb";
            case "03":
                return dateHeader.substring(0, 2) + " March";
            case "04":
                return dateHeader.substring(0, 2) + " April";
            case "05":
                return dateHeader.substring(0, 2) + " May";
            case "06":
                return dateHeader.substring(0, 2) + " June";
            case "07":
                return dateHeader.substring(0, 2) + " July";
            case "08":
                return dateHeader.substring(0, 2) + " Aug";
            case "09":
                return dateHeader.substring(0, 2) + " Sep";
            case "10":
                return dateHeader.substring(0, 2) + " Oct";
            case "11":
                return dateHeader.substring(0, 2) + " Nov";
            case "12":
                return dateHeader.substring(0, 2) + " Dec";
            default:
                return null;
        }
    }






}


