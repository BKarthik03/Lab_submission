package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class delete_staff extends AppCompatActivity {

    EditText roll;
    FirebaseDatabase fb;
    DatabaseReference db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_staff);
        fb=FirebaseDatabase.getInstance();
        db=fb.getReference();
        roll=findViewById(R.id.del_log);
    }


    public void delete_sta(View view) {

        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {

        String getroll=roll.getText().toString();




        if (!getroll.isEmpty()) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Staff").child(getroll).exists()) {
                        db.child("Staff").child(getroll).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(delete_staff.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        finish();
                    } else {

                        Toast.makeText(delete_staff.this, "Staff Not Exist!\nYou may add the Staff", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(delete_staff.this, "Error", Toast.LENGTH_SHORT).show();

                }


            });

        } else {
            Toast.makeText(delete_staff.this, "Please enter the Roll Number!", Toast.LENGTH_SHORT).show();
        }


    }}

}