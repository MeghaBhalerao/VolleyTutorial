package com.example.volleytutorial.string_request;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleytutorial.R;

public class SettingUpRequestQueueActivity extends AppCompatActivity {

    String server_url = "http://192.168.0.8/demo/volley_tutorial.php";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_up_request_queue);

        textView = (TextView)findViewById(R.id.textView);

        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
    }
    public void getResponseFromServer(View view) {

        final RequestQueue requestQueue = Volley.newRequestQueue(SettingUpRequestQueueActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                        requestQueue.stop();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.toString());
                        requestQueue.stop();
                    }
                });
        requestQueue.add(stringRequest);
    }
}
