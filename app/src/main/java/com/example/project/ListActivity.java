package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    DatabaseHelperCondition conditionDB;
    ArrayList<String> user_id, user_name, user_condition, user_treatment;
    CustomAdapter customAdapter;
    String unique_user;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            conditionDB.addCondition(unique_user, data.getStringExtra("condition"), data.getStringExtra("treatment"));
            storeData();
            customAdapter.notifyItemInserted(user_condition.indexOf(data.getStringExtra("condition")));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        unique_user = intent.getStringExtra("user");

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.floatingActionButton);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, addActivity.class);
//                intent.putExtra("user", unique_user);
                startActivityForResult(intent, 0);
            }
        });

        conditionDB = new DatabaseHelperCondition(ListActivity.this);
        user_name = new ArrayList<>();
        user_condition = new ArrayList<>();
        user_treatment = new ArrayList<>();
        user_id = new ArrayList<>();


        storeData();

        System.out.println(user_name.size());



        customAdapter = new CustomAdapter(ListActivity.this, user_id, user_name, user_condition,user_treatment);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

    }

    void storeData(){
        user_id.clear();
        user_name.clear();
        user_condition.clear();
        user_treatment.clear();
        Cursor cursor = conditionDB.readAllDataFrom(unique_user);

        System.out.println();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_condition.add(cursor.getString(2));
                user_treatment.add(cursor.getString(3));
            }
        }
    }
}