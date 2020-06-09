package com.example.uafsmartguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    EditText email,agnumber,pass,confirmpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    public void SignUp(View view) {
        if (!pass.getText().toString().equals(confirmpass.getText().toString()))
        {

            Snackbar.make(view,"Password did not match",Snackbar.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //MOve to next Activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                            finish();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });




    }


    public void init()
    {
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailsignup);
        pass = findViewById(R.id.passsignup);
        confirmpass = findViewById(R.id.confirmpass);
        agnumber = findViewById(R.id.agnumbersignup);
    }
}
