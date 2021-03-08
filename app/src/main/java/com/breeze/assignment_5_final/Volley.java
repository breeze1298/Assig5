package com.breeze.assignment_5_final;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

public class Volley extends AppCompatActivity {

    ProgressDialog progress;
    private static String url = "https://api.androidhive.info/contacts/";

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        recyclerView=findViewById(R.id.volleyRecyclerView);

        new jsonData().execute();

    }

    private class jsonData extends AsyncTask<String,Void,String>

    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //before the background process start, this is executed
            progress= new ProgressDialog(Volley.this);
            progress.setTitle("Loading Data from URL");
            progress.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            /*try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonData = jsonObject.getJSONArray("contacts");
                for (int i = 0; i < jsonData.length(); i++)
                {
                    JSONObject c = jsonData.getJSONObject(i);
                    String name = c.getString("name");
                    String email = c.getString("email");

                    HashMap<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("email", email);

                   // listData.add(data);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson=new Gson();
                    GsonResponse res=gson.fromJson(response, GsonResponse.class);
                    // Toast.makeText(Volley.this, response.toString(), Toast.LENGTH_SHORT).show();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Volley.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setHasFixedSize(true);

                    RecyclerViewAdapter adapter=new RecyclerViewAdapter(res.getContacts());
                    recyclerView.setAdapter(adapter);



                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            // Creating String Request Object.
            RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(Volley.this);
            // Passing String request into RequestQueue.
            requestQueue.add(stringRequest);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.dismiss();

        }

    }
}