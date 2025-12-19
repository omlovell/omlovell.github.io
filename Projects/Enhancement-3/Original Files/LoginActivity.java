package com.example.weighttrackingoml;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_NOTIFICATIONS = 99;
    EditText username;
    EditText password;
    Button createLogin;
    Button useLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);
        createLogin = findViewById(R.id.button2);
        useLogin = findViewById(R.id.button);
        
        requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, RC_NOTIFICATIONS);

        // for the login button
        useLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInformation dbUse = new UserInformation(LoginActivity.this);
                List<LoginModel> usersStuff = dbUse.getUsers();
                int z = 0;


                // checks the user and password based of of the login model
                for (LoginModel x : usersStuff) {
                    if (x.toString().equals(username.getText().toString())) {
                        if (x.getPassword().equals(password.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent myInt = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myInt);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password entered incorrectly", Toast.LENGTH_LONG).show();
                        }
                        break;
                    } else {
                        z = 1;
                    }
                }

                if (z != 0) { Toast.makeText(LoginActivity.this, "Login Failed! User doesn't exist", Toast.LENGTH_SHORT).show(); }

            }
        });


        // create user info after create button pressed
        createLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInformation db = new UserInformation(LoginActivity.this);
                List<LoginModel> users = db.getUsers();
                int checkY = 0;

                //this works to check if the user exits, doesn't check the password
                // password not checked when creating the user in this application
                for (LoginModel y : users) {
                    // if the username exists, or if the fields left blank do not create an a new userinformation
                    if (y.toString().equals(username.getText().toString()) && !username.getText().toString().isEmpty()) {
                        checkY = 1;
                        Toast.makeText(LoginActivity.this, "Creation Failed! User may exist", Toast.LENGTH_LONG).show();
                        break;
                    }
                }

                if (checkY == 0) {
                    //add user
                    LoginModel userLogins = new LoginModel(username.getText().toString(), password.getText().toString());

                    UserInformation dbHandler = new UserInformation(LoginActivity.this);

                    dbHandler.addUser(userLogins);
                    Intent myInt = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myInt);

                }

            }
        });
    }

    // permissions for notifications
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (requestCode == RC_NOTIFICATIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Allowed Notifications", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notifications Off", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
