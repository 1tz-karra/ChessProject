package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class PrevActivity extends AppCompatActivity implements View.OnClickListener {

    Button game1;
    Button game2;
    Button game3;
    Button game4;
    Button game5;

    private  String     HOST      = "192.168.1.5";
    private  int        PORT      = 15000;

    String email;
    String password;

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
        String data = "";
        if (v.getId() == R.id.button1) {
            try {
                data = new Get_game().execute(1).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String[] words = data.split(",");
            intent.putExtra("game", words[0]);
            intent.putExtra("game_info", words[1]);
        }
        if (v.getId() == R.id.button2) {
            try {
                data = new Get_game().execute(2).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String[] words = data.split(",");
            intent.putExtra("game", words[0]);
            intent.putExtra("game_info", words[1]);
        }
        if (v.getId() == R.id.button3) {
            try {
                data = new Get_game().execute(3).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String[] words = data.split(",");
            intent.putExtra("game", words[0]);
            intent.putExtra("game_info", words[1]);
        }
        if (v.getId() == R.id.button4) {
            try {
                data = new Get_game().execute(4).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String[] words = data.split(",");
            intent.putExtra("game", words[0]);
            intent.putExtra("game_info", words[1]);
        }
        if (v.getId() == R.id.button5) {
            try {
                data = new Get_game().execute(5).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String[] words = data.split(",");
            intent.putExtra("game", words[0]);
            intent.putExtra("game_info", words[1]);
        }
        startActivity(intent);
    }

    class Get_game extends AsyncTask<Integer,Void,String> {
        Socket socket;
        BufferedWriter writer;
        BufferedReader reader;
        protected String doInBackground(Integer...params){
            String data = "";
            try {
                socket = new Socket(HOST, PORT);
                writer = new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                System.out.println("Connected to server");
                int button_no = params[0];
                writer.write(3);
                writer.flush();
                writer.write(button_no);
                writer.flush();
                String credentials = "";
                credentials += email;
                credentials += "|";
                credentials += password;
                while (credentials.length() < 40) {
                    credentials += " ";
                }
                writer.write(credentials);
                writer.flush();
                data = reader.readLine();
                System.out.println(data);
                socket.close();
            } catch (IOException e) {
                System.out.println("Connection failed");
                e.printStackTrace();
            }
            return data;
        }
    }
}