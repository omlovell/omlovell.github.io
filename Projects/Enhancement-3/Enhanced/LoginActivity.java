package com.example.weighttrackingoml;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_NOTIFICATIONS = 99;
    EditText userEmail;
    EditText password;
    Button loginButton;
    Button createButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userEmail = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);


        createButton = findViewById(R.id.button2);

        loginButton = findViewById(R.id.button);
        
        requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, RC_NOTIFICATIONS);

        // for the login button ( mongoDB and backend will handle)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOGIN", "Login Clicked");
                login();
            }
        });

        // for create button, creates new user
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CREATE", "CREATE Clicked");
                create();
            }
        });

    }

    private void create() {
        AuthenticationAPI authenticationAPI = ApiClient.getClient().create(AuthenticationAPI.class);
        String user = userEmail.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Enter email/username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(user, pass);

        authenticationAPI.register(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 201) {
                    Toast.makeText(LoginActivity.this, "Succesfully created...", Toast.LENGTH_LONG).show();
                    Toast.makeText(LoginActivity.this, "Login in...", Toast.LENGTH_LONG).show();
                    // after create login method called so user doesn't have to hit login after create
                    login();
                } else if (response.code() == 409) {
                    Toast.makeText(LoginActivity.this, "User exists... Try LOGIN.", Toast.LENGTH_LONG).show();
                } else {
                    String msg = "REGISTER FAILED: " + response.code();
                    try {
                        if (response.errorBody() != null) msg += " | " + response.errorBody().string();
                    } catch (Exception ignored) {}
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                    Log.e("REGISTER", msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void login() {
        AuthenticationAPI authenticationAPI = ApiClient.getClient().create(AuthenticationAPI.class);

        LoginRequest request = new LoginRequest(
                userEmail.getText().toString(),
                password.getText().toString()
        );

        authenticationAPI.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                // Invalid login. made toast to handle for now
                if (response.isSuccessful() && response.body() != null && response.body().getToken() != null) {
                    String token = response.body().getToken();
                    saveToken(token);

                    // move to main screen
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    String msg = "LOGIN FAILED: " + response.code();
                    try {
                        if (response.errorBody() != null) {
                            msg += " | " + response.errorBody().string();
                        }
                    } catch (Exception ignored) {}

                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                    Log.e("LOGIN", msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                // FIXME: add code for error
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void saveToken(String token) {
        SharedPreferences preference = getSharedPreferences("auth", MODE_PRIVATE);
        preference.edit().putString("token", token).apply();
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
