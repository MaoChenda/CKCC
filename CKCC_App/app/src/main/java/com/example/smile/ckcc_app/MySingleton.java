package com.example.smile.ckcc_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Smile on 12/22/2017.
 */

public class MySingleton  {

    private static MySingleton singletonInstance;
    private static Context context;
    private RequestQueue requestQueue;

    private MySingleton(Context context){

        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public static synchronized MySingleton getSingletonInstance(Context context){
        if(singletonInstance == null)
            singletonInstance = new MySingleton(context);
        return singletonInstance;
    }

    public <T>void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }

}
