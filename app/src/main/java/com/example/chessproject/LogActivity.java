package com.example.chessproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.w3c.dom.Text;

public class LogActivity extends AppCompatActivity {

    Button log;
    Button reg;

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
        TextView email = register_window.findViewById(R.id.email);
        TextView password = register_window.findViewById(R.id.password);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //check email + password in server
                //email.toString() return email;
                //if user not found use showBack instead start()
                /*if (1 == 1) {
                    start();
                } else {
                    showBack();
                }*/
                start();
            }
        });
        dialog.show();
    }

    private void showBack() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Не найден пользователь");
        dialog.setMessage("Попробуйте снова");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
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
                start();
            }
        });
        dialog.show();
    }

    private void start() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}