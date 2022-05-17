package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private  String     HOST      = "";
    private  int        PORT      = -1;

    private  String     LOG_TAG   = "SOCKET";

    Button Scan_Button;
    Button Prev_Button;
    EditText text;

    SharedPreferences sPref;
    EditText etText;
    final String SAVED_TEXT = "saved_text";

    String key = "key";
    String[] base = new String[5];

    private void update(String s) {
        for (int i = 0; i < 5; ++i) {
            base[i] = base[i + 1];
        }
        base[0] = s;
    }

    private void connect() {
        // Создание подключения
        final Connection[] mConnect = {new Connection(HOST, PORT)};
        // Открытие сокета в отдельном потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(LOG_TAG, "Соединение установлено");
                    Log.d(LOG_TAG, "(mConnect != null) = "
                            + (mConnect[0] != null));
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                    mConnect[0] = null;
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scan_Button = (Button) findViewById(R.id.scan_game);
        Scan_Button.setOnClickListener(this);
        Prev_Button = (Button) findViewById(R.id.prev_game);
        Prev_Button.setOnClickListener(this);
        //connect();
        text = (EditText) findViewById(R.id.editTextTextPersonName);
        //text.setText(base[0]);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putStringArray(key, base);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        base = savedInstanceState.getStringArray(key);


    }

    @Override
    public void onClick(View v) {
        base[0] = text.getText().toString();
        if (v.getId() == R.id.scan_game) {
            Intent intent = new Intent(this, ActivityTwo.class);
            startActivity(intent);
            base[1] = base[0];
            base[0] = "poshel nahui";
        }
        if (v.getId() == R.id.prev_game) {
            text.setText(base[1]);
            Intent intent = new Intent(this, ActivityThree.class);
            startActivity(intent);
        }
    }
}