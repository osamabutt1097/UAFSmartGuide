package com.example.uafsmartguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private LottieAnimationView anim;
    private EditText email, pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void Login(View view) {
        anim.setVisibility(View.VISIBLE);
        if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
        {
            Snackbar.make(view,"Please provide Email/Password",Snackbar.LENGTH_LONG).show();
            anim.setVisibility(View.GONE);

            return;
        }

        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            finish();
                            anim.setVisibility(View.GONE);

                            //updateUI(user);
                        } else {
                            anim.setVisibility(View.GONE);

                            // If sign in fails, display a message to the user.
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void SignUp(View view) {
        startActivity(new Intent(LoginActivity.this,SignupActivity.class));
    }


    public void init()
    {
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passLogin);
        anim = findViewById(R.id.load_login);
    }


    @Override
    protected void onStart() {
        super.onStart();

        anim.setVisibility(View.VISIBLE);

        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
//////////////////////////////////////////////////////////////////////////////////////////

            startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
            finish();
        }
        else
        {
            anim.setVisibility(View.GONE);
        }


    }
}
