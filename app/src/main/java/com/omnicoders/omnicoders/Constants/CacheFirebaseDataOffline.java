package com.omnicoders.omnicoders.Constants;

import android.support.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;
import com.google.firebase.database.FirebaseDatabase;

//TODO save into cache memory offline use
public class CacheFirebaseDataOffline extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
/*
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);*/
    }
}
