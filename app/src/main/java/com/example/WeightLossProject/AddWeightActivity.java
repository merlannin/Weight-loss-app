package com.example.WeightLossProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddWeightActivity extends AppCompatActivity {

    EditText weight_input;
    Button add_entry_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        weight_input = findViewById(R.id.weight_input);
        add_entry_button = findViewById(R.id.add_entry_button);
        add_entry_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddWeightActivity.this);
            myDB.addWeight(Double.parseDouble(weight_input.getText().toString()));
        });
    }
}