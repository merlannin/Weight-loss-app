package com.example.WeightLossProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText update_weight_input;
    Button update_entry_button;

    String record_id, weight_in_pounds, datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_weight_input = findViewById(R.id.update_weight_input);
        update_entry_button = findViewById(R.id.update_entry_button);

        //first we call this function before updating
        //gets and sets data to view in update page
        getAndSetIntentData();
        update_entry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //then we call these to update
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            myDB.updateData(record_id, weight_in_pounds, datetime);
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("record_id") && getIntent().hasExtra("weight_in_pounds") && getIntent().hasExtra("datetime")){

            //getting data from intent
            record_id = getIntent().getStringExtra("record_id");
            weight_in_pounds = getIntent().getStringExtra("weight_in_pounds");
            datetime = getIntent().getStringExtra("datetime");

            //setting intent data into text field on update page for reference
            update_weight_input.setText(weight_in_pounds);

        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}