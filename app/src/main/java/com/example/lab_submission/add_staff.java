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

public class add_staff extends AppCompatActivity {

    EditText name;
    EditText no,sub;
    EditText pass;
    Button add;
    FirebaseDatabase fb;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        fb = FirebaseDatabase.getInstance();
        db=fb.getReference();

        name=findViewById(R.id.staff_name);
        no=findViewById(R.id.Staff_no);
        pass=findViewById(R.id.Staff_pass);
        sub=findViewById(R.id.staff_Sub);
        add=findViewById(R.id.Add_staff);

// ...

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(add_staff.this)) {
                    NetworkUtils.showNoInternetDialog(add_staff.this);
                } else {
                    String getname = name.getText().toString();
                    String getroll = no.getText().toString();
                    String getpass = pass.getText().toString();
                    String getsub = sub.getText().toString();

                    DatabaseReference staffRef = db.child("Staff").child(getroll);

                    staffRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(add_staff.this, "Staff member already exists!", Toast.LENGTH_SHORT).show();
                            } else {
                                HashMap<String,Object> hm = new HashMap<>();
                                hm.put("name", getname);
                                hm.put("Login No", getroll);
                                hm.put("Password", getpass);
                                hm.put("Subject", getsub);

                                db.child("Staff").child(getroll).setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(add_staff.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(add_staff.this, "Cannot Add!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(add_staff.this, admin_page_sta.class);
                                        startActivity(i);
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_staff.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

// ...

        }
    }
