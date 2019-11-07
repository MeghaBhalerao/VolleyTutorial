package com.example.volleytutorial.json_as_input_param;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;
import com.example.volleytutorial.R;
import com.example.volleytutorial.json_object_request.JsonObjectRequestActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonAsInputParamActivity extends AppCompatActivity {

    EditText
            name_et,
            email_et,
            mobile_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_as_input_param);

        name_et = (EditText)findViewById(R.id.name_et);
        email_et = (EditText)findViewById(R.id.email_et);
        mobile_et = (EditText)findViewById(R.id.mobile_et);
    }
    public void sendDataToServer(View view)
    {
        try
        {
            final String  name = name_et.getText().toString();
            final String  email = email_et.getText().toString();
            final String  mobile = mobile_et.getText().toString();

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("name",name);
            jsonObject.put("mobile",mobile);
            jsonObject.put("email",email);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.jsonInputParam,jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(JsonAsInputParamActivity.this,""+response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(JsonAsInputParamActivity.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
            Mysingleton.getInstance(JsonAsInputParamActivity.this).addToRequestQueue(jsonObjectRequest);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
