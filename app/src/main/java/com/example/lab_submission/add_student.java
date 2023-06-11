package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class add_student extends AppCompatActivity {

    EditText name;
    EditText roll;
    EditText pass;
    Button add;
    FirebaseDatabase fb;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        fb = FirebaseDatabase.getInstance();
        db=fb.getReference();

        name=findViewById(R.id.editTextText2);
        roll=findViewById(R.id.editTextNumber2);
        pass=findViewById(R.id.editTextNumberPassword);
        add=findViewById(R.id.button9);


        // ...

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(add_student.this)) {
                    NetworkUtils.showNoInternetDialog(add_student.this);
                } else {
                    String getname = name.getText().toString();
                    String getroll = roll.getText().toString();
                    String getpass = pass.getText().toString();

                    DatabaseReference studentRef = db.child("Students").child(getroll);

                    studentRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(add_student.this, "Student already exists!", Toast.LENGTH_SHORT).show();
                            } else {
                                HashMap<String,Object> hm = new HashMap<>();
                                hm.put("name", getname);
                                hm.put("Roll No", getroll);
                                hm.put("Password", getpass);

                                db.child("Students").child(getroll).setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(add_student.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(add_student.this, "Cannot Add!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(add_student.this, admin_page.class);
                                        startActivity(i);
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_student.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

// ...

    }}
