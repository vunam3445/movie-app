package com.example.moviesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviesapp.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userName, password;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userName = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"data is empty",Toast.LENGTH_SHORT).show();
                } else if (userName.getText().toString().equals("nam") && password.getText().toString().equals("nam")) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this,"data not found",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}