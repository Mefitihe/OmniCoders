package com.omnicoders.omnicoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            Toast.makeText(getApplicationContext(), "Language Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.changePass) {
            Toast.makeText(getApplicationContext(), "ChangePass Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.theme) {
            Toast.makeText(getApplicationContext(), "Theme Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            System.exit(0);
        }
        return true;
    }
}
