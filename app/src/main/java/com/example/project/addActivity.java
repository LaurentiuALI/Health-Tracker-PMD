package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addActivity extends AppCompatActivity {

    EditText condition, treatment;
    Button done;
    DatabaseHelperCondition dbCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbCondition = new DatabaseHelperCondition(addActivity.this);

//        Intent intent = getIntent();
//        String username = intent.getStringExtra("user");

        condition = findViewById(R.id.conditionEditText);
        treatment = findViewById(R.id.treatmentEditText);
        done = findViewById(R.id.doneButton);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("condition", condition.getText().toString());
                intent.putExtra("treatment", treatment.getText().toString());
//                dbCondition.addCondition(username, condition.getText().toString(), treatment.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}