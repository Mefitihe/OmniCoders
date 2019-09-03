package com.omnicoders.omnicoders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
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
    // Notification ID to allow for future updates
    private static final int MY_NOTIFICATION_ID = 1;
    final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        createNotificationChannel();
        //calling login() inside onCreate()
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

                    //calling statusNotification() inside login button
                    statusNotification();

                }
                else{
                    Toast.makeText(mContext, "Invalid username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void statusNotification() {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Login notification")
                .setContentText("Successfully Login!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Hey, you are successfully login into OmniCoders App.\nTab to go home"))

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(MY_NOTIFICATION_ID, builder.build());


    }

    //create notificationChannel and set its importance
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.log_in);
            String description = getString(R.string.logo);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
