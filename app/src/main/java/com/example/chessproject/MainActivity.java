package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button Scan_Button;
    Button Prev_Button;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scan_Button = (Button) findViewById(R.id.scan_game);
        Scan_Button.setOnClickListener(this);
        Prev_Button = (Button) findViewById(R.id.prev_game);
        Prev_Button.setOnClickListener(this);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        System.out.println("Main Activity(On Click)");
        System.out.println(email);
        System.out.println(password);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan_game) {
            Intent intent = new Intent(this, ScanActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.prev_game) {
            Intent intent = new Intent(this, PrevActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
        }
    }
}