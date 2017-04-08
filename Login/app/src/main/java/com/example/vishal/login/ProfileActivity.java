package com.example.vishal.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    // View Objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        // Getting the current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Initializing views
        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button)findViewById(R.id.buttonLogout);

        // Displayed Loggedin User Name
        textViewUserEmail.setText("Welcome! " + user.getEmail());

        // Adding Listener to button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logging out the user
                firebaseAuth.signOut();

                //closing activity
                finish();

                // Starting Login Activity
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });
    }
}
