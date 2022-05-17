package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ChessBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
        Intent intent = getIntent();
        ChessView chessView = findViewById(R.id.chessView);
        chessView.piecesData = intent.getStringExtra("game");
    }
}