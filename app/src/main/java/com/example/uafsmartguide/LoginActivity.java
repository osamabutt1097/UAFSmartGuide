package com.example.uafsmartguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void Login(View view) {
        if (email.getText().toString().isEmpty())
        {
            Snackbar.make(view,"Please provide Email/Password",Snackbar.LENGTH_LONG).show();
            return;
        }


    }

    public void SignUp(View view) {
    }


    public void init()
    {
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passLogin);
    }
}
