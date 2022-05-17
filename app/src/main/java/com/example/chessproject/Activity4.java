package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.concurrent.locks.ReentrantLock;

public class Activity4 extends AppCompatActivity implements View.OnClickListener {

    private int width = 100, height = 150, x = 20, y= 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        Intent intent = getIntent();

    }

    @Override
    public void onClick(View v) {

    }

}