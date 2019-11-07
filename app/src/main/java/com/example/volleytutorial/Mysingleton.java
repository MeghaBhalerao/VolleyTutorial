package com.example.volleytutorial;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton
{
    private static Mysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context contextData;

    private Mysingleton(Context context)
    {
        contextData = context;
        requestQueue = getRequestQueue();
    }
    //method for get instance of request queue
    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(contextData.getApplicationContext());
        return requestQueue;
    }
    //method for get instanace of this class
    public static  synchronized Mysingleton getInstance(Context context)
    {
        if(mInstance == null)
            mInstance = new Mysingleton(context);
        return mInstance;
    }
    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
