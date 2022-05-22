package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList user_id, user_name, user_condition, user_treatment;

    CustomAdapter(Context context, ArrayList user_id, ArrayList user_name, ArrayList user_condition, ArrayList user_treatment){
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_condition = user_condition;
        this.user_treatment = user_treatment;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.user_condition_txt.setText(String.valueOf(user_condition.get(position)));
        holder.user_treatment_txt.setText(String.valueOf(user_treatment.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_name_txt, user_condition_txt, user_treatment_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_condition_txt = itemView.findViewById(R.id.conditionTextView);
            user_treatment_txt = itemView.findViewById(R.id.treatmentTextView);
        }
    }
}
