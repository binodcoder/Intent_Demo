package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView firstName;
    EditText lastName;
    Button secondOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firstName=findViewById(R.id.tv_first_name);
        lastName=findViewById(R.id.et_last_name);
        secondOk=findViewById(R.id.second_ok);

        firstName.setText(getIntent().getStringExtra("fname"));

        secondOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lName=lastName.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("lname", lName);
                setResult(RESULT_OK, intent);
                SecondActivity.this.finish();
            }
        });
    }
}