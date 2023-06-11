package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class student_home extends AppCompatActivity {

    EditText roll,pass;
    String s1,s2;
    FirebaseDatabase fb;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        roll=findViewById(R.id.stu_uname);
        pass=findViewById(R.id.stu_pass);
        fb = FirebaseDatabase.getInstance();
        db=fb.getReference();
    }


    public void stu_logg(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else{


        s1 = roll.getText().toString();
        s2 = pass.getText().toString();


        if (!s1.isEmpty() && !s2.isEmpty()) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String a = String.valueOf(dataSnapshot.child("Students").child(s1).child("Password").getValue());
                    if (dataSnapshot.child("Students").child(s1).exists()) {
                        if(a.equals(s2)){
                        Toast.makeText(student_home.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent i = new Intent(student_home.this,stu_sub.class);
                        i.putExtra("roll",s1);
                        startActivity(i);}
                        else {
                            Toast.makeText(student_home.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(student_home.this, "Student Not Exist\nPlease Contact Administrator!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(student_home.this, "Error", Toast.LENGTH_SHORT).show();

                }


            });

        } else {
            Toast.makeText(student_home.this, "Please complete the information", Toast.LENGTH_SHORT).show();
        }
    }}

}