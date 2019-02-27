package com.example.vollyserver;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

    private static Mysingleton minstance;
    private RequestQueue requestQueue;
    private Context mtx;

    private Mysingleton(Context context) {
        this.mtx = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Mysingleton addinstance(Context context) {
        if (minstance == null) {
            minstance = new Mysingleton(context);
        }
        return minstance;
    }

    public <T> void addrequest(Request<T> request) {
        requestQueue.add(request);
    }
}
