package com.example.weighttrackingoml;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


// This is for the main screen, taking care of the button for sms notification
// display of the line graph
// and adding a weight and date to the database
public class MainActivity extends AppCompatActivity {
    EditText weight1;
    EditText date1;
    Button addWeight;
    Button removeWeight;

    ListView lv_weights;

    ArrayAdapter userWeightArrAdap;

    UserWeights dbWeightHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWeight = findViewById(R.id.AddButton);
        removeWeight = findViewById(R.id.deleteButton);
        weight1 = findViewById(R.id.enteredWeightAdd);
        date1 = findViewById(R.id.enteredDateAdd);

        lv_weights = findViewById(R.id.listView);

        dbWeightHandler = new UserWeights(MainActivity.this);
        displayweights();

        //button listeners
        addWeight.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Adding weight...", Toast.LENGTH_LONG).show();
            UserWeights dbw = new UserWeights(MainActivity.this);
            List<WeightModel> dates = dbw.getUsersWeight();
            int temp = 0;

            // make sure date isnt already in use.. assuming MM/DD/YYYY format
            // if it is update the date
            // this makes it a little 'unfriendly' for the user but given the data can still be updated it should be fine for now
            for (WeightModel z : dates) {
                if (z.getDate().equals(date1.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Date in use, updating...", Toast.LENGTH_LONG).show();
                    dbw.updateOneWeight(weight1.getText().toString(),date1.getText().toString());
                    displayweights();
                    temp = temp + 1;
                    break;
                }
            }

            if (temp == 0) {
                dbWeightHandler.addWeightsDB(weight1.getText().toString(), date1.getText().toString());
                displayweights();
            }

        });

        removeWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Removing weight...", Toast.LENGTH_LONG).show();
                dbWeightHandler.deleteOne(date1.getText().toString());
                displayweights();
            }
        });


    }

    private void displayweights() {
        userWeightArrAdap= new ArrayAdapter<WeightModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbWeightHandler.getUsersWeight());
        lv_weights.setAdapter(userWeightArrAdap);
    }

}