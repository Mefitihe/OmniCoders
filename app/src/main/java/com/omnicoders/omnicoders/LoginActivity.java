package com.omnicoders.omnicoders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //widget varibales
    Button btnLogin, btnSignup;
    EditText edtUsername, edtPassword;
    String mUsername, mPassword;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        login();

    }

    private void login(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = edtUsername.getText().toString();
                mPassword = edtPassword.getText().toString();

                if(mPassword.equals("1234") && mUsername.equals("omni")){

                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(mContext, "Invalid username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
