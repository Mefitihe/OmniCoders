package com.omnicoders.omnicoders.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

public class ApplyDefaultLanguage {

    private final Context context;
    private final String lang;
    private final Configuration config;

    public ApplyDefaultLanguage(Context context) {
        this.context = context;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        this.config = context.getResources().getConfiguration();
        this.lang = settings.getString("LANG", "");
    }


    public void applyDefaultLanguage() {

        /**
         * method it will apply language changes to SharedPreferences
         * so that next time someone opens the app,
         * they will see the last selected language. or
         *  it will be called inside onResume method of every Activity class when the
         *  screen is rotated.
         */

        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        }
    }
}
