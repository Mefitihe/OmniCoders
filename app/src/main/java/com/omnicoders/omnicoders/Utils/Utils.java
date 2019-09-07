package com.omnicoders.omnicoders.Utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.omnicoders.omnicoders.R;

public class Utils {

    //Declares constants

    public final static int THEME_DEFAULT = 0;
    public final static int THEME_NIGHT = 1;
    public final static int THEME_DAY = 2;
    private static int mTheme;
    /*
     * Set the theme of the activity
     */

    public static void changeTheme(AppCompatActivity appCompatActivity, int theme) {
        mTheme = theme;
        appCompatActivity.finish();
        appCompatActivity.startActivity(new Intent(appCompatActivity, appCompatActivity.getClass()));


    }

    /*
     * Set the theme of the activity, according to the configuration
     */

    public static void onAppCompatActivityCreateSetTheme(AppCompatActivity appCompatActivity) {

        switch (mTheme) {

            default:
            case THEME_DEFAULT:
                appCompatActivity.setTheme(R.style.AppTheme);
                break;

            case THEME_NIGHT:
                appCompatActivity.setTheme(R.style.DayNight);
                break;

            case THEME_DAY:
                appCompatActivity.setTheme(R.style.DayLight);
                break;
        }
    }
}
