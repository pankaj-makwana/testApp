package com.example.pankajmakwana.photosearch;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
  private static VolleySingleton mInstance;
  private RequestQueue mRequestQueue;

  private VolleySingleton(Context context){
    mRequestQueue = Volley.newRequestQueue(context);
  }

  public static synchronized VolleySingleton getmInstance(Context context){
    if (mInstance==null){
      mInstance=new VolleySingleton(context);
    }
    return mInstance;
  }

}
