package com.example.smile.ckcc_app.activities;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Smile on 12/18/2017.
 */

public class CKCCApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
