package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class update_student extends AppCompatActivity {

    EditText name;
    EditText pass;
    EditText roll;
    FirebaseDatabase fb;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        name=findViewById(R.id.up_name);
        roll=findViewById(R.id.up_roll);
        pass=findViewById(R.id.up_pass);
        fb=FirebaseDatabase.getInstance();
        db=fb.getReference();
    }



    public void update_st(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {

                String getname=name.getText().toString();
                String getroll=roll.getText().toString();
                String getpass=pass.getText().toString();


        HashMap<String,Object> hm = new HashMap<>();
        hm.put("name",getname);
        hm.put("Roll No",getroll);
        hm.put("Password",getpass);

        if (!getname.isEmpty() && !getroll.isEmpty() && !getpass.isEmpty()) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Students").child(getroll).exists()) {
                        db.child("Students").child(getroll).setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(update_student.this, "Details Updated Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        finish();
                    } else {

                        Toast.makeText(update_student.this, "Student Not Exist!\nYou may add the Student", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(update_student.this, "Error", Toast.LENGTH_SHORT).show();

                }


            });

        } else {
            Toast.makeText(update_student.this, "Please complete the information", Toast.LENGTH_SHORT).show();
        }





            }}

}