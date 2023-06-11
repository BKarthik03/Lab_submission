package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class staff_home extends AppCompatActivity {

    EditText log,pass;
    String s1,s2;
    FirebaseDatabase fb;
    DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        log=findViewById(R.id.stf_uname);
        pass=findViewById(R.id.stf_pass);
        fb = FirebaseDatabase.getInstance();
        db=fb.getReference();
    }

    public void stf_logg(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else{
        s1 = log.getText().toString();
        s2 = pass.getText().toString();


        if (!s1.isEmpty() && !s2.isEmpty()) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String a = String.valueOf(dataSnapshot.child("Staff").child(s1).child("Password").getValue());
                    if (dataSnapshot.child("Staff").child(s1).exists()) {
                        if(a.equals(s2)){
                            Toast.makeText(staff_home.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();

                            finish();
                            Intent i = new Intent(staff_home.this,stf_sub.class);
                            startActivity(i);}
                        else {
                            Toast.makeText(staff_home.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(staff_home.this, "Staff Not Exist\nPlease Contact Administrator!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(staff_home.this, "Error", Toast.LENGTH_SHORT).show();

                }


            });

        } else {
            Toast.makeText(staff_home.this, "Please complete the information", Toast.LENGTH_SHORT).show();
        }
    }}

}