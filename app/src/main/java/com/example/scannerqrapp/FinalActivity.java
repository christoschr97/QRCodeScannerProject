package com.example.scannerqrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    private TextView msg; //msg to display to the user
    private Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        msg = findViewById(R.id.successMsg);
        msg.setText(getIntent().getExtras().getString("com.example.scannerqrapp.FINAL_MESSAGE"));
        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(goHome);
            }
        });


    }
}
