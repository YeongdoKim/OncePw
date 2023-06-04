package com.example.oncepw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private Button generateButton;
    private Button completeButton;
    private TextView passwordTextView;
    private List<String> passwordList;


    private static final String DEFAULT_TEXT = "임시 비밀번호가 생성되지 않았습니다.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateButton = findViewById(R.id.generateButton);
        completeButton = findViewById(R.id.completeButton);
        passwordTextView = findViewById(R.id.passwordTextView);
        passwordList = new ArrayList<>();

        passwordTextView.setText(DEFAULT_TEXT);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = generatePassword();
                passwordTextView.setText(password);
                passwordList.add(password);
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearPasswordList();
                Toast.makeText(MainActivity.this, "임시 비밀번호가 사용 완료되었습니다.", Toast.LENGTH_SHORT).show();
                passwordTextView.setText(DEFAULT_TEXT);
            }
        });
    }



    private String generatePassword() {
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(9) + 1;
            passwordBuilder.append(digit);
        }

        return passwordBuilder.toString();
    }

    private void clearPasswordList() {
        passwordList.clear();
    }
}

