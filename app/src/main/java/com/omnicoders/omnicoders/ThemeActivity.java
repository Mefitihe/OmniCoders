package com.omnicoders.omnicoders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

import com.omnicoders.omnicoders.Utils.Utils;

public class ThemeActivity extends AppCompatActivity implements View.OnClickListener {

    //Toggle button widgets
    ToggleButton toggleDefaultTheme, toggleDayLight, toggleDayNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Called the theme before the layout view
        Utils.onAppCompatActivityCreateSetTheme(this);

        setContentView(R.layout.activity_theme);

        //view widgets by ides

        toggleDefaultTheme = findViewById(R.id.toggleDefalutTheme);
        toggleDayLight = findViewById(R.id.toggleDayLight);
        toggleDayNight = findViewById(R.id.toggleDayNight);

        //Add action listeners
        toggleDefaultTheme.setOnClickListener(this);
        toggleDayNight.setOnClickListener(this);
        toggleDayLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toggleDefalutTheme:
                if (toggleDefaultTheme.isChecked()) {
                    toggleDefaultTheme.setChecked(true);
                    Utils.changeTheme(this, Utils.THEME_DEFAULT);
                } else {
                    toggleDefaultTheme.setChecked(false);

                }
                break;
            case R.id.toggleDayLight:
                if (toggleDayLight.isChecked()) {
                    toggleDayLight.setChecked(true);
                    Utils.changeTheme(this, Utils.THEME_DAY);
                } else {
                    toggleDayLight.setChecked(false);

                }
                break;
            case R.id.toggleDayNight:
                if (toggleDayNight.isChecked()) {
                    toggleDayNight.setChecked(true);
                    Utils.changeTheme(this, Utils.THEME_NIGHT);
                } else {
                    toggleDayNight.setChecked(false);

                }
                break;
        }
    }
}
