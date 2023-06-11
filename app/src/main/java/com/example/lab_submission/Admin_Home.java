package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Home extends AppCompatActivity {

    Button stu,sta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        stu=findViewById(R.id.acc_stu);
        sta=findViewById(R.id.acc_sta);



        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(Admin_Home.this)) {
                    NetworkUtils.showNoInternetDialog(Admin_Home.this);
                }
                else{
                Intent i = new Intent(Admin_Home.this,admin_page.class);
                startActivity(i);
            }}
        });

        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(Admin_Home.this)) {
                    NetworkUtils.showNoInternetDialog(Admin_Home.this);
                }
                else{
                Intent i = new Intent(Admin_Home.this, admin_page_sta.class);
                startActivity(i);}
            }
        });
    }}
