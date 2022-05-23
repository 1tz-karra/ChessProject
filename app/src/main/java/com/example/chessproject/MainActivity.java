package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private  String     HOST      = "";
    private  int        PORT      = -1;

    private  String     LOG_TAG   = "SOCKET";

    Button Scan_Button;
    Button Prev_Button;
    EditText etText;

    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    int num = 0;
    String[] base = new String[5];

    private void update(String s) {
        String prev = "";
        String next = base[0];
        for (int i = 1; i < 5; ++i) {
            prev = base[i];
            base[i] = next;
            next = prev;
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
        //etText = (EditText) findViewById(R.id.editTextTextPersonName);
        //text.setText(base[0]);
        //loadText();
    }

    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("size", 5);
        for(int i = 0; i < 5; i++)
            ed.putString("" + i, base[i]);
        ed.apply();
    }

    private void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        int size = sPref.getInt("size", 0);
        for(int i = 0; i < 5; i++)
            base[i] = sPref.getString("" + i, null);
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan_game) {
            //update(etText.getText().toString());
            Intent intent = new Intent(this, ScanActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.prev_game) {
            //etText.setText(base[num]);
            ++num;
            Intent intent = new Intent(this, PrevActivity.class);
            startActivity(intent);
        }
    }
}