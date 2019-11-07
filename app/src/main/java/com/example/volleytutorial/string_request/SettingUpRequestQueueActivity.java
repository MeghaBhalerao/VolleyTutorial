package com.example.volleytutorial.string_request;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;
import com.example.volleytutorial.R;

public class SettingUpRequestQueueActivity extends AppCompatActivity {

    String server_url = Constants.volleyTutorial;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_up_request_queue);

        textView = (TextView)findViewById(R.id.textView);

        //disk base cache support class provide cashing
        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        //basic Network class provide network transactions
        Network network = new BasicNetwork(new HurlStack());
        //now we initialise request queue
        //requestQueue = new RequestQueue(cache,network);
       // requestQueue.start();
    }
    public void getResponseFromServer(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.toString());
                        error.printStackTrace();

                    }
                });
        Mysingleton.getInstance(SettingUpRequestQueueActivity.this).addToRequestQueue(stringRequest);
    }
}
