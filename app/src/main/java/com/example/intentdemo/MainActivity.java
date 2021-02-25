package com.example.intentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    TextView fullName;
    Button mainOk, mainSend, google, dial, call, openApp, openMyApp, sendBroadcast;
    final int rCode=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.et_firstname);
        fullName = findViewById(R.id.tv_fullname);
        mainOk = findViewById(R.id.main_ok);
        mainSend=findViewById(R.id.main_send);
        google=findViewById(R.id.google);
        dial=findViewById(R.id.dial);
        call=findViewById(R.id.call);
        openApp=findViewById(R.id.openapp);
        openMyApp=findViewById(R.id.open_my_app);
        sendBroadcast=findViewById(R.id.send_broadcast);

        getSupportActionBar().hide();

        mainOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName=firstName.getText().toString();
                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("fname", fName);
                startActivityForResult(intent, rCode);
            }
        });

        mainSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName=firstName.getText().toString();
                Intent sendIntent=new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, fullName);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.kh"));
                startActivity(intent);
            }
        });

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9807402437"));
                startActivity(intent);
            }
        });

        openApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(intent);
            }
        });
        openMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getPackageManager().getLaunchIntentForPackage("com.example.calculator");
                startActivity(intent);
            }
        });
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("com.example.intentdemo");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fName=firstName.getText().toString();
        String lName=data.getStringExtra("lname");
        if(requestCode==rCode){
            if(resultCode==RESULT_OK){
                fullName.setText(fName+" "+lName);
            }
        }
    }
}