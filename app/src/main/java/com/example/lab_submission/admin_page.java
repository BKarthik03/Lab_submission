package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

    }

   public void add_student(View view) {
       if (!NetworkUtils.isNetworkAvailable(this)) {
           NetworkUtils.showNoInternetDialog(this);
       }
        else{
       Intent i = new Intent(this,add_student.class);
       startActivity(i);}
    }

    public void update_student(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else{
        Intent i = new Intent(this,update_student.class);
        startActivity(i);}
    }

    public void delete_student(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else{
        Intent i = new Intent(this,delete_student.class);
        startActivity(i);}
    }
}