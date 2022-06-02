package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ScanActivity extends AppCompatActivity {

    ImageView imageView;
    Button scan;

    private String email;
    private String password;

    private  String     HOST      = "192.168.1.5";
    private  int        PORT      = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        imageView = findViewById(R.id.imageView);
        scan = findViewById(R.id.scan);
        scan.setOnClickListener(this::onClick);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
    }

    public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        new Send_photo().execute(bitmap);
    }

    class Send_photo extends AsyncTask<Bitmap,Void,Void> {
        Socket socket;
        BufferedOutputStream writer;
        BufferedReader reader;
        protected Void doInBackground(Bitmap...params){
            String FEN = "";
            try {
                socket = new Socket(HOST, PORT);
                writer = new BufferedOutputStream(
                        socket.getOutputStream());
                reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                System.out.println("Connected to server");
                byte [] imgbyte = getBytesFromBitmap(params[0]);
                String credentials = "";
                credentials += email;
                credentials += "|";
                credentials += password;
                while (credentials.length() < 40) {
                    credentials += " ";
                }
                writer.write(2);
                writer.flush();
                writer.write(credentials.getBytes(StandardCharsets.UTF_8));
                writer.flush();
                writer.write(imgbyte);
                writer.flush();
                System.out.println("File sent");
                socket.close();
            } catch (IOException e) {
                System.out.println("Connection failed");
                e.printStackTrace();
            }
            System.out.println(FEN);
            return null;
        }
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
}