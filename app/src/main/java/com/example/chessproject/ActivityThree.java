package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityThree extends AppCompatActivity implements View.OnClickListener {

    Button game1;
    Button game2;
    Button game3;
    Button game4;
    Button game5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        game1 = (Button) findViewById(R.id.button1);
        game1.setOnClickListener(this);
        game2 = (Button) findViewById(R.id.button2);
        game2.setOnClickListener(this);
        game3 = (Button) findViewById(R.id.button3);
        game3.setOnClickListener(this);
        game4 = (Button) findViewById(R.id.button4);
        game4.setOnClickListener(this);
        game5 = (Button) findViewById(R.id.button5);
        game5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChessBoardActivity.class);
        if (v.getId() == R.id.button1) {
            intent.putExtra("game", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        }
        if (v.getId() == R.id.button2) {
            intent.putExtra("game", "2r2rk1/2q2p1p/p1p1nPpQ/3p4/4p1B1/8/PPP3PP/R4R1K");
        }
        /*if (v.getId() == R.id.button3) {
            intent.putExtra("game", );
        }
        if (v.getId() == R.id.button4) {
            intent.putExtra("game", );
        }
        if (v.getId() == R.id.button5) {
            intent.putExtra("game", );
        }*/
        startActivity(intent);
    }

}