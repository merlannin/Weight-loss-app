package com.example.WeightLossProject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_entry_button;
    MyDatabaseHelper myDB;
    ArrayList<String> record_id, weight_in_pounds, datetime;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Date CURRENT_TIME = Calendar.getInstance().getTime();

        // video 4

        recyclerView = findViewById(R.id.recyclerview);
        add_entry_button = findViewById(R.id.add_entry_button);
        add_entry_button.setOnClickListener(view -> {

            //An Intent is a messaging object you can use to request an action from another app component
            //Although intents facilitate communication between components in several ways, there are three fundamental use cases:
            //Starting an activity
            //Starting a service
            //Delivering a broadcast
            //https://developer.android.com/guide/components/intents-filters
            Intent intent = new Intent(MainActivity.this, AddWeightActivity.class);
            startActivity(intent);
        });

        //initialize our dbHelper, making arrays to store our data
        myDB = new MyDatabaseHelper(MainActivity.this);
        record_id = new ArrayList<>();
        weight_in_pounds = new ArrayList<>();
        datetime = new ArrayList<>();

        //initializes the stored data in arrays function.
        storeDataInArrays();

        //initialize custom adapter object
        customAdapter = new CustomAdapter(MainActivity.this, this,record_id, weight_in_pounds, datetime);
        recyclerView.setAdapter(customAdapter);
        // sets the layoutManager to the current recyclerview which is set to the custom adapter layout
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //fills our arraylists with the data from the db table
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();

        // check if the DB is empty. if not empty, move the cursor and get the data for each entry.
        if (cursor.getCount() == 0){
            Toast.makeText(this, "The database is empty, please add an entry.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                record_id.add(cursor.getString(0));
                datetime.add(cursor.getString(1));
                weight_in_pounds.add(cursor.getString(2));
            }
        }

    }

}