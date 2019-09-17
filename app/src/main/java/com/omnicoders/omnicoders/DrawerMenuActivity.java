package com.omnicoders.omnicoders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.omnicoders.omnicoders.Utils.ApplyDefaultLanguage;

import java.util.Locale;

public class DrawerMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO called language class here before onCreate() is called
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();

        setContentView(R.layout.activity_drawer_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //get passed data from LoginActivity class

        intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String level = intent.getStringExtra("level");

        //ArrayList

        String[] myDataset = {"Java for Android", "Android development for beginners",
                "Advanced Android Development", "Web Development",
                "Scratch Programming for beginners", "Python for beginners", "Advanced Python"};


        recyclerView = findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        //get sharedPreferences
        sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //set name and email on header
        TextView txtName = findViewById(R.id.txtName);
        TextView txtEmail = findViewById(R.id.txtEmail);


        //txtName.setText(name);
        //txtEmail.setText(email + "\n" + level);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO called language class here before when the screen is rotated
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.lang) {

            //called showLangaugeDialog()
            showChangeLangDialog();

            //Toast.makeText(getApplicationContext(), "Language Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.changePass) {
            //Called changePassword()
            changePassword();

            //Toast.makeText(getApplicationContext(), "ChangePass Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.theme) {
            //Call activity as Dialog
            Intent theme = new Intent(DrawerMenuActivity.this, ThemeActivity.class);
            startActivity(theme);

            //Toast.makeText(getApplicationContext(), "Theme Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            System.exit(0);
        }
        return true;
    }

    /*
     * a method changePassword() to show password change alert dialog
     */
    private void changePassword() {

        //1. Build alertDialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(DrawerMenuActivity.this);
        // 2. Inflate custom layout
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialgogView = inflater.inflate(R.layout.activity_change_pass, null);
        builder.setView(dialgogView);

        //3. add action buttons
        builder.setPositiveButton(R.string.change, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //TODO add some code here
                EditText edtPass = dialgogView.findViewById(R.id.edtChangePass);
                if (edtPass.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Password not changed!. Enter New Password!", Toast.LENGTH_LONG).show();
                } else {

                    //apply to sharedPreference
                    editor.putString("password", edtPass.getText().toString());
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Password Changed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int wichButton) {
                dialog.dismiss();
            }
        });
        //4. Create alert dialog
        final AlertDialog dialog = builder.create();
        //5.show dialog
        dialog.show();

    }

    /**
     * method showChangeLangDialog to display and change language setting
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

    //TODO method to recreate the language on android system
    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_java) {

        } else if (id == R.id.nav_android) {

        } else if (id == R.id.nav_web) {

        } else if (id == R.id.nav_scratch) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
