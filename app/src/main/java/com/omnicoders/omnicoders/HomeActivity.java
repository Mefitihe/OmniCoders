package com.omnicoders.omnicoders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.omnicoders.omnicoders.Utils.Utils;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Called the theme before the layout view
        Utils.onAppCompatActivityCreateSetTheme(this);
        setContentView(R.layout.activity_home);

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
        } else if (id == R.id.theme) {
            //Call activity as Dialog
            Intent theme = new Intent(HomeActivity.this, ThemeActivity.class);
            startActivity(theme);

            //Toast.makeText(getApplicationContext(), "Theme Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            System.exit(0);
        }
        return true;
    }

    /**
     * method showChangeLangDialog to display and change language setting
     */
    public void showChangeLangDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_language, null);
        dialogBuilder.setView(dialogView);


        //dialogBuilder.setTitle(getResources().getString(R.string.language_option));
        //dialogBuilder.setMessage(getResources().getString(R.string.select_lang));
        dialogBuilder.setPositiveButton(R.string.change, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /*TODO add some code here. The code you write here must perform user action when you select the language and click on change button
                 */
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

}
