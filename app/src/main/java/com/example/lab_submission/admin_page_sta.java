package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_page_sta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_sta);
    }

    public void Add_Staff(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {
        Intent i = new Intent(this,add_staff.class);
        startActivity(i);}
    }

    public void Update_Staff(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else{
        Intent i = new Intent(this,update_staff.class);
        startActivity(i);}
    }

    public void Delete_Staff(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else{
        Intent i = new Intent(this,delete_staff.class);
        startActivity(i);}
    }
}