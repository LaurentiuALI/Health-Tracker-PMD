package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText user_input, pass_input;
    MaterialButton register_button, login_button;
    ArrayList<String> user_id, user_name, user_pass;
    DatabaseHelperLogin loginDB;
    DatabaseHelperCondition conditionDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDB = new DatabaseHelperLogin(MainActivity.this);
        conditionDB = new DatabaseHelperCondition(MainActivity.this);


        user_input = findViewById(R.id.usernameEditText);
        pass_input = findViewById(R.id.passwordEditText);
        register_button = findViewById(R.id.registerMaterialBttn);
        login_button = findViewById(R.id.loginMaterialBttn);

        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_pass = new ArrayList<>();

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeDataInArrays();
                loginDB.addUser(user_input.getText().toString().trim(),
                                pass_input.getText().toString().trim());
            }
        });



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDataInArrays();
                int vb = 0;
                for(int i = 0; i < user_name.size(); i++){
                    if(user_input.getText().toString().trim().equals(user_name.get(i)) && pass_input.getText().toString().trim().equals(user_pass.get(i)))
                    {
                        Toast.makeText(MainActivity.this, "Login succesfully with creditentials " + user_name.get(i) + " "+ user_pass.get(i), Toast.LENGTH_SHORT).show();
                        vb = 1;
                        Intent intent = new Intent(MainActivity.this, ListActivity.class);
                        intent.putExtra("user", user_input.getText().toString().trim());
                        startActivity(intent);
                        break;
                    }
                }
                    if (vb == 0){
                        Toast.makeText(MainActivity.this, "Login unsuccesfully with creditentials " + user_input.getText().toString() + pass_input.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
            }
        });

    }

    public void storeDataInArrays(){
        Cursor cursor = loginDB.readAllData();
        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_pass.add(cursor.getString(2));
            }
        }
    }

}