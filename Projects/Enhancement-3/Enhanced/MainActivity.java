package com.example.weighttrackingoml;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// This is for the main screen, taking care of the button for sms notification
// display of the line graph
// and adding a weight and date to the database
public class MainActivity extends AppCompatActivity {
    // REMOVED OLD VARIABLES
    private WeightApi weightApi;
    private ListView lv_weights;
    private Button addButton;
    // removed delete button, add long press delete
    // private Button deleteButton;
    private EditText enteredWeight;
    private EditText enteredDate;
    private List<Weight> weightObjects = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.AddButton);
        enteredWeight = findViewById(R.id.enteredWeightAdd);
        enteredDate = findViewById(R.id.enteredDateAdd);
        lv_weights = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rows);
        lv_weights.setAdapter(adapter);

        addButton.setOnClickListener(v -> addWeightInfo());

        lv_weights.setOnItemLongClickListener(((parent, view, position, id) -> {
            Weight w = weightObjects.get(position);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Entry Deletion")
                    .setMessage("Deleting : " + w.getDate() + " @ " + w.getWeight() + w.getUnit())
                    .setPositiveButton("Delete", ((dialog, which) -> deleteWeight(w.getId())))
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        }));

        weightApi = ApiClient.getClient().create(WeightApi.class);

        fetchWeights();

    }

    private void fetchWeights() {
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = preferences.getString("token", null);

        if (token == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class ));
            finish();
            return;
        }

        String authHeader = "Bearer " + token;

        weightApi.getWeights(authHeader).enqueue(new Callback<List<Weight>>() {
            @Override
            public void onResponse(@NonNull Call<List<Weight>> call, @NonNull Response<List<Weight>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    weightsListView(response.body());

                } else if (response.code() == 401) {
                    clearToken();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class ));
                    finish();
                } else {
                    Log.e("MainActivity", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Weight>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void addWeightInfo() {
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = preferences.getString("token", null);

        if (token == null) {
            return;
        }

        String authHeader = "Bearer " + token;

        // TODO: Add more validation (Need date string formating)
        String weight1 = enteredWeight.getText().toString().trim();
        String date = enteredDate.getText().toString().trim();

        if (weight1.isEmpty() || date.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter weight and date.", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight2 = 0.0;

        try {
            weight2 = Double.parseDouble(weight1);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        Weight newInformation = new Weight(weight2, "lbs", date);

        weightApi.addWeight(authHeader, newInformation).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    enteredWeight.setText("");
                    enteredDate.setText("");
                    fetchWeights();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void deleteWeight(String weightId) {
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = preferences.getString("token", null);
        if (token == null) return;

        String authHeader = "Bearer " + token;

        weightApi.deleteWeight(authHeader, weightId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful() && response.code() == 204) {
                    fetchWeights();
                } else {
                    Toast.makeText(MainActivity.this, "Deletion Failed: " + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void weightsListView(List<Weight> weights) {
        weightObjects.clear();
        rows.clear();
        weightObjects.addAll(weights);

        for (Weight w : weights) {
            rows.add(w.getDate() + " @ " + w.getWeight() + w.getUnit());
        }

        adapter.notifyDataSetChanged();
    }

    private void clearToken() {
        getSharedPreferences("auth", MODE_PRIVATE)
                .edit()
                .remove("token")
                .apply();
    }

}