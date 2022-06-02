package com.example.chessproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LogActivity extends AppCompatActivity {

    Button log;
    Button reg;

    TextView email;
    TextView password;

    private  String     HOST      = "192.168.1.5";
    private  int        PORT      = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        log = findViewById(R.id.log);
        reg = findViewById(R.id.reg);
        reg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
        log.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginWindow();
            }
        });
    }



    private void showLoginWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);
        email = register_window.findViewById(R.id.email);
        password = register_window.findViewById(R.id.password);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String semail = email.getText().toString();
                String spassword = password.getText().toString();
                ArrayList<String> credentials = new ArrayList<String>();
                credentials.add(semail);
                credentials.add(spassword);
                String ret = "";
                try {
                    ret = new Send_credetials_in().execute(credentials).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ret);
                if (ret.equals("1")) {
                    start();
                } else {
                    showBack();
                }
            }
        });
        dialog.show();
    }

    class Send_credetials_in extends AsyncTask<ArrayList<String>,Void,String> {
        Socket socket;
        BufferedWriter writer;
        BufferedReader reader;
        protected String doInBackground(ArrayList<String>...params){
            String ret = "";
            try {
                socket = new Socket(HOST, PORT);
                writer = new BufferedWriter(
                         new OutputStreamWriter(
                         socket.getOutputStream()));
                reader = new BufferedReader(
                         new InputStreamReader(
                         socket.getInputStream()));
                System.out.println("Connected to server");
                ArrayList<String> credentials = params[0];
                writer.write(1);
                writer.flush();
                writer.write(credentials.get(0));
                writer.write("|");
                writer.write(credentials.get(1));
                writer.flush();
                ret = reader.readLine();
                socket.close();
            } catch (IOException e) {
                System.out.println("Connection failed");
                e.printStackTrace();
            }
            return ret;
        }
    }

    private void showBack() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Не найден пользователь");
        dialog.setMessage("Попробуйте снова");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);
        email = register_window.findViewById(R.id.email);
        password = register_window.findViewById(R.id.password);
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String semail = email.getText().toString();
                String spassword = password.getText().toString();
                ArrayList<String> credentials = new ArrayList<String>();
                credentials.add(semail);
                credentials.add(spassword);
                String ret = "";
                try {
                    ret = new Send_credetials_in().execute(credentials).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ret);
                if (ret.equals("1")) {
                    start();
                } else {
                    showBack();
                }
            }
        });
        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        TextView email = register_window.findViewById(R.id.email);
        TextView password = register_window.findViewById(R.id.password);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String semail = email.getText().toString();
                String spassword = password.getText().toString();
                ArrayList<String> credentials = new ArrayList<String>();
                credentials.add(semail);
                credentials.add(spassword);
                try {
                    new Send_credetials_up().execute(credentials).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                start();
            }
        });
        dialog.show();
    }

    class Send_credetials_up extends AsyncTask<ArrayList<String>,Void,Void> {
        Socket socket;
        BufferedWriter writer;
        BufferedReader reader;

        protected Void doInBackground(ArrayList<String>... params) {
            try {
                socket = new Socket(HOST, PORT);
                writer = new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                System.out.println("Connected to server");
                ArrayList<String> credentials = params[0];
                writer.write(0);
                writer.flush();
                writer.write(credentials.get(0));
                writer.write("|");
                writer.write(credentials.get(1));
                writer.flush();
                socket.close();
            } catch (IOException e) {
                System.out.println("Connection failed");
                e.printStackTrace();
            }
            return null;
        }
    }

    private void start() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("password", password.getText().toString());
        System.out.println("Log");
        System.out.println(email.getText());
        System.out.println(password.getText());
        startActivity(intent);
    }
}