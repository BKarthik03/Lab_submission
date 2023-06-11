package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class admin_login extends AppCompatActivity {

    EditText email,pass;
    Button bt1;

    ProgressBar pb1;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email=findViewById(R.id.editTextTextPersonName);
        pass=findViewById(R.id.editTextNumberPassword2);
        pb1=findViewById(R.id.pb1);
        bt1=findViewById(R.id.button8);
        mAuth=FirebaseAuth.getInstance();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(admin_login.this)) {
                    NetworkUtils.showNoInternetDialog(admin_login.this);
                }
                else{
                pb1.setVisibility(View.VISIBLE);
                String em, pas;
                em=email.getText().toString();
                pas=pass.getText().toString();

                if(TextUtils.isEmpty(em)){
                    Toast.makeText(admin_login.this, "Enter valid emial id!", Toast.LENGTH_SHORT).show();
                    pb1.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(pas)){
                    Toast.makeText(admin_login.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    pb1.setVisibility(View.GONE);
                    return;
                }


                mAuth.signInWithEmailAndPassword(em, pas)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb1.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(admin_login.this, "Logged In!", Toast.LENGTH_SHORT).show();
                                    Intent i =
                                            new Intent(admin_login.this,Admin_Home.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(admin_login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }}
        });

    }
}