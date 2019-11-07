package com.example.volleytutorial.send_data_toserver;

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
import com.android.volley.toolbox.StringRequest;
import com.example.volleytutorial.Constants;
import com.example.volleytutorial.Mysingleton;
import com.example.volleytutorial.R;

import java.util.HashMap;
import java.util.Map;

public class SendDataToServerActivity extends AppCompatActivity
{
    EditText
            name_et,
            email_et,
            mobile_et;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_server);

        name_et = (EditText)findViewById(R.id.name_et);
        email_et = (EditText)findViewById(R.id.email_et);
        mobile_et = (EditText)findViewById(R.id.mobile_et);
    }

    public void sendDataToServer(View view)
    {

        final String  name = name_et.getText().toString();
        final String  email = email_et.getText().toString();
        final String  mobile = mobile_et.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.update_info,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("DATAAAA : ","RESPONSE : "+response);

                        Toast.makeText(SendDataToServerActivity.this,response,Toast.LENGTH_LONG).show();
                        name_et.setText("");
                        email_et.setText("");
                        mobile_et.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("DATAAAA : ","error : "+error);
                        Toast.makeText(SendDataToServerActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("email",email);
                map.put("mobile",mobile);

                return map;
            }
        };
        Mysingleton.getInstance(SendDataToServerActivity.this).addToRequestQueue(stringRequest);
    }
}
