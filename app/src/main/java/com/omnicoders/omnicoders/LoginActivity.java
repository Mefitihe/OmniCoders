package com.omnicoders.omnicoders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Declare widget variables
    Button btnLogin, btnSignUp;
    EditText edtUserName, edtPassword;
    //Member variables that holds demy username and password;
    String mUsername, mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //view widgets by id
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignup);

        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        login();
    }

    //method button login()

    private void login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = edtUserName.getText().toString();
                mPassword = edtPassword.getText().toString();

                if(mUsername.equals("omni") && mPassword.equals("1234")){
                    //navigate to home
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

                }else{
                    //Toast message
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
