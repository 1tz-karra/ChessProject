package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChessBoardActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
        Intent intent = getIntent();
        ChessView chessView = findViewById(R.id.chessView);
        chessView.piecesData = intent.getStringExtra("game");
        text = findViewById(R.id.textView3);
        text.setText(intent.getStringExtra("game_info"));
    }
}