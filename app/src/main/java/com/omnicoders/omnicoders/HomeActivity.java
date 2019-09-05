package com.omnicoders.omnicoders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

            //called showLangaugeDialog()
            showChangeLangDialog();

            //Toast.makeText(getApplicationContext(), "Language Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.changePass) {
            //Called changePassword()
            changePassword();

            //Toast.makeText(getApplicationContext(), "ChangePass Selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.theme) {
            Toast.makeText(getApplicationContext(), "Theme Selected", Toast.LENGTH_SHORT).show();
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

        //1. Build alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        // 2. Inflate custom layout
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialgogView = inflater.inflate(R.layout.activity_change_pass, null);
        builder.setView(dialgogView);
        //3. Create alert dialog
        final AlertDialog dialog = builder.create();
        //4. add action buttons
        builder.setPositiveButton(R.string.change, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO add some code here
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

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
