package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }

    }


    public void stu_login(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else {
            Intent i = new Intent(this, student_home.class);
            startActivity(i);
        }
    }

    public void stf_login(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else {
            Intent i = new Intent(this, staff_home.class);
            startActivity(i);
        }
    }

    public void admin_page(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }
        else {
            Intent i = new Intent(this, admin_login.class);
            startActivity(i);
        }
    }
}