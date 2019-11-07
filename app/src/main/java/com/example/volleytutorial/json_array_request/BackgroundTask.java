package com.example.volleytutorial.json_array_request;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackgroundTask {

    Context context;
    ArrayList<Contact> arrayList = new ArrayList<>();
    String server_url = Constants.contactInfo;

    public BackgroundTask(Context context)
    {
        this.context = context;
    }

    public void getList(final ReturnList returnList)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, server_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try
                        {
                            for(int i = 0; i < response.length(); i++)
                            {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Contact contact = new Contact(jsonObject.getString("NAME"),jsonObject.getString("EMAIL"));
                                arrayList.add(contact);
                            }
                            returnList.returnList(arrayList);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
        Mysingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);


    }
}
