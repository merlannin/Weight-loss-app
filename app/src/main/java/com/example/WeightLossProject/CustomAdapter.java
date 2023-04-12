package com.example.WeightLossProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//USED FOR MAKING CUSTOM VIEW LAYOUT PORTION

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList record_id, weight_in_pounds, datetime;

    //constructor for our customAdapter layout
    CustomAdapter(Activity activity, Context context, ArrayList record_id, ArrayList weight_in_pounds, ArrayList datetime){
        this.activity = activity;
        this.context = context;
        this.record_id = record_id;
        this.weight_in_pounds = weight_in_pounds;
        this.datetime = datetime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //sets the layout to the visual look of layout/my_row.xml
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //assigns the values from the current cursor position to their respective text values
        holder.record_id_text.setText("record # " + String.valueOf(record_id.get(position)));
        holder.weight_in_pounds_text.setText(String.valueOf(weight_in_pounds.get(position)));
        holder.datetime_text.setText(String.valueOf(datetime.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //provides the info about the item selected in the update activity page.
                //pass everything even if we don't modify it?
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("record_id", String.valueOf(record_id.get(position)));
                intent.putExtra("weight_in_pounds", String.valueOf(weight_in_pounds.get(position)));
                intent.putExtra("datetime", String.valueOf(datetime.get(position)));
                activity.startActivityForResult(intent, 1);


            }
        });
    }

    @Override
    public int getItemCount() {
        //returns the count of items in the table, based off record id.
        return record_id.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {

        TextView record_id_text, weight_in_pounds_text, datetime_text;
        LinearLayout mainLayout;

        //sets the text view variables from out textview items.
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            record_id_text =  itemView.findViewById(R.id.record_id_text);
            weight_in_pounds_text =  itemView.findViewById(R.id.weight_in_pounds_text);
            datetime_text =  itemView.findViewById(R.id.datetime_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
