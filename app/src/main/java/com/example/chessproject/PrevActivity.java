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
//        Intent intent = getIntent();
//        email = intent.getStringExtra("email");
//        password = intent.getStringExtra("password");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChessBoardActivity.class);
//        ArrayList<String> credentials = new ArrayList<String>();
//        credentials.add(email);
//        credentials.add(password);
//        String data = "";
        if (v.getId() == R.id.button1) {
//            credentials.add("1");
//            try {
//                data = new Get_game().execute(credentials).get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
            intent.putExtra("game", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
            intent.putExtra("game_info", "Начало партии");
        }
        if (v.getId() == R.id.button2) {
//            credentials.add("2");
//            try {
//                data = new Get_game().execute(credentials).get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
            intent.putExtra("game", "rnbqkbnr/ppp1pppp/8/3p4/2PP4/8/PP2PPPPP/RNBQKBNR");
            intent.putExtra("game_info", "Принятый ферзевый гамбит");
        }
//        if (v.getId() == R.id.button3) {
//            credentials.add("3");
//            try {
//                data = new Get_game().execute(credentials).get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//            intent.putExtra("game", );
//        }
//        if (v.getId() == R.id.button4) {
//            credentials.add("4");
//            try {
//                data = new Get_game().execute(credentials).get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//            intent.putExtra("game", );
//        }
//        if (v.getId() == R.id.button5) {
//            credentials.add("5");
//            try {
//                data = new Get_game().execute(credentials).get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//            intent.putExtra("game", );
//        }
//        startActivity(intent);
    }

//    class Get_game extends AsyncTask<ArrayList<String>,Void,String> {
//        Socket socket;
//        BufferedWriter writer;
//        BufferedReader reader;
//        protected String doInBackground(ArrayList<String>...params){
//            String FEN = "";
//            try {
//                socket = new Socket(HOST, PORT);
//                writer = new BufferedWriter(
//                        new OutputStreamWriter(
//                                socket.getOutputStream()));
//                reader = new BufferedReader(
//                        new InputStreamReader(
//                                socket.getInputStream()));
//                System.out.println("Connected to server");
//                ArrayList<String> credentials = params[0];
//                writer.write(3);
//                writer.flush();
//                writer.write(credentials.get(0));
//                writer.write("|");
//                writer.write(credentials.get(1));
//                writer.write("|");
//                writer.write(credentials.get(2));
//                writer.flush();
//                socket.close();
//            } catch (IOException e) {
//                System.out.println("Connection failed");
//                e.printStackTrace();
//            }
//            System.out.println(FEN);
//            return FEN;
//        }
//    }
}