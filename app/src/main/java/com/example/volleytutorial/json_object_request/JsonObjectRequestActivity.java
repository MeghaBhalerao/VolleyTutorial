package com.example.volleytutorial.json_object_request;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;
import com.example.volleytutorial.R;
import com.example.volleytutorial.image_request.ImageRequestActivity;

import org.json.JSONObject;

public class JsonObjectRequestActivity extends AppCompatActivity {

    TextView
            name_tv,
            email_tv,
            mobile_tv;

    String server_url = Constants.getInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_object_request);

        name_tv = (TextView)findViewById(R.id.name_tv);
        email_tv = (TextView)findViewById(R.id.email_tv);
        mobile_tv = (TextView)findViewById(R.id.mobile_tv);
    }

    public void getInfo(View view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, server_url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            name_tv.setText("Name : "+response.getString("NAME"));
                            email_tv.setText("Email : "+response.getString("EMAIL"));
                            mobile_tv.setText("Mobile : "+response.getString("MOBILE"));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JsonObjectRequestActivity.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        Mysingleton.getInstance(JsonObjectRequestActivity.this).addToRequestQueue(jsonObjectRequest);
    }
}
