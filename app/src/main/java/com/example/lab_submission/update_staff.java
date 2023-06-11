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

public class update_staff extends AppCompatActivity {

    EditText name;
    EditText pass;
    EditText login,sub;
    FirebaseDatabase fb;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff);
        name=findViewById(R.id.up_name_st);
        login=findViewById(R.id.up_no_st);
        pass=findViewById(R.id.up_pass_st);
        sub=findViewById(R.id.up_sub_st);
        fb=FirebaseDatabase.getInstance();
        db=fb.getReference();
    }



    public void update_stf(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {

        String getname=name.getText().toString();
        String getroll=login.getText().toString();
        String getpass=pass.getText().toString();
        String getsub=sub.getText().toString();


        HashMap<String,Object> hm = new HashMap<>();
        hm.put("name",getname);
        hm.put("Login No",getroll);
        hm.put("Password",getpass);
        hm.put("Subject",getsub);

        if (!getname.isEmpty() && !getroll.isEmpty() && !getpass.isEmpty() && !getsub.isEmpty()) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Staff").child(getroll).exists()) {
                        db.child("Staff").child(getroll).setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(update_staff.this, "Details Updated Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        finish();
                    } else {

                        Toast.makeText(update_staff.this, "Staff Not Exist!\nYou may add the Staff", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(update_staff.this, "Error", Toast.LENGTH_SHORT).show();

                }


            });

        } else {
            Toast.makeText(update_staff.this, "Please complete the information", Toast.LENGTH_SHORT).show();
        }





    }}

}