package com.omnicoders.omnicoders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.omnicoders.omnicoders.Utils.ApplyDefaultLanguage;
import com.omnicoders.omnicoders.Utils.Utils;

public class SignupActivity extends AppCompatActivity {
    Button signUp, logIn;
    EditText edtName, edtPass, edtUsername;
    Spinner spLevel;
    Context mContext;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.onAppCompatActivityCreateSetTheme(this);
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();
        setContentView(R.layout.activity_signup);
        //get layout widgets by ids
        mContext = getApplicationContext();
        signUp = findViewById(R.id.btnSignup);
        logIn = findViewById(R.id.btnLogin);
        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        spLevel = findViewById(R.id.spLevel);

        //Write data in shared preferences

        sharedPref = getSharedPreferences("userPref", MODE_PRIVATE);
        editor = sharedPref.edit();
        //calling setSignup()
        setSignUp();
        login();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO to change team
        Utils.onAppCompatActivityCreateSetTheme(this);
        //TODO Language
        new ApplyDefaultLanguage(getApplicationContext()).applyDefaultLanguage();
    }

    //TODO create signUp method
    private void setSignUp() {

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String username = edtUsername.getText().toString();
                String level = spLevel.getSelectedItem().toString();
                //Check if text editor is empty or not

                if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(mContext, "Fill all required filds", Toast.LENGTH_SHORT).show();

                } else {
                    editor.putString("name", name);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putString("level", level);
                    editor.clear();
                    editor.commit();
                    Toast.makeText(mContext, "Successfully Signup!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, LoginActivity.class);
                    startActivity(i);
                    finish();

                }


            }
        });
    }

    private void login() {
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
