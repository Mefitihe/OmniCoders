package com.omnicoders.omnicoders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.omnicoders.omnicoders.Utils.ApplyDefaultLanguage;

import java.util.Locale;

public class NavigationHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,

        JavaFragment.OnFragmentInteractionListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    new NavigationHomeActivity();
                    return true;
                case R.id.navigation_about:
                    Toast.makeText(getApplicationContext(), "Add Operation to be performed here", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_share:
                    Toast.makeText(getApplicationContext(), "Add Operation to be performed here", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_rate:
                    Toast.makeText(getApplicationContext(), "Add Operation to be performed here", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();
        super.onCreate(savedInstanceState);
        //TODO called language class here before onCreate() is called
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();

        setContentView(R.layout.activity_drawer_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //TODO setting header text Programatically
        View navView = navigationView.inflateHeaderView(R.layout.nav_header_drawer_menu);
        TextView textViewHeaderName = navView.findViewById(R.id.txtName);
        TextView textViewHeaderLevel = navView.findViewById(R.id.txtLevel);

        //TODO VIEW BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        // mTextMessage = findViewById(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //TODO get passed data from LoginActivity class

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String level = intent.getStringExtra("level");

        textViewHeaderName.setText(name.toUpperCase());
        textViewHeaderLevel.setText(level.toUpperCase());

        //TODO add the default Fragment
        //NOTE:  Open JavaFragment initially.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.MyFragmentLayout, new JavaFragment());
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*use AppCompatActivity method getMenuInflater() to get a handle on the menu inflater*/

        MenuInflater inflater = getMenuInflater();
        /* use the inflater's inflate method to inflate our home_option layout to this menu */
        inflater.inflate(R.menu.home_option, menu);


        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO called language class here before when the screen is rotated
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.lang) {

            //called showLangaugeDialog()
            showChangeLangDialog();

            //Toast.makeText(getApplicationContext(), "Language Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.theme) {
            //Call activity as Dialog
            Intent theme = new Intent(NavigationHomeActivity.this, ThemeActivity.class);
            startActivity(theme);

            //Toast.makeText(getApplicationContext(), "Theme Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            System.exit(0);
        }
        return true;
    }

    //TODO method to recreate the language on android system
    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    /**
     * TODO method showChangeLangDialog to display and change language setting
     */
    public void showChangeLangDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_language, null);
        dialogBuilder.setView(dialogView);
        //get RadioGroup ID
        final RadioGroup languageGroup = dialogView.findViewById(R.id.radioLang);

        dialogBuilder.setPositiveButton(R.string.change, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /*TODO add some code here. The code you write here must perform user action when you select the language and click on change button
                 */

                int radioButtonId = languageGroup.getCheckedRadioButtonId();
                //int langpos = spinner.getSelectedItemPosition();
                String language;
                switch (radioButtonId) {
                    case R.id.radioAm://Set to Amharic

                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "am").commit();
                        setLangRecreate("am");
                        break;

                    case R.id.radioEn://Set to English
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                        setLangRecreate("en");
                        break;

                    default: //By default set to Amharic
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "am").commit();
                        setLangRecreate("am");
                        break;
                }
            }


        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_java) {
            fragment = new JavaFragment();

        } else if (id == R.id.nav_android) {

        } else if (id == R.id.nav_web) {

        } else if (id == R.id.nav_scratch) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //TODO: NOTE: Fragment changing code
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.MyFragmentLayout, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
