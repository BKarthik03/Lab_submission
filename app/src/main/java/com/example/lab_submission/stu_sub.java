package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stu_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_sub);
    }

    public void upload_report(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {
        String b = getIntent().getStringExtra("roll");
        Intent i = new Intent(this,Upload_Report.class);
        i.putExtra("roll",b);
        startActivity(i);}
    }

    public void delete_report(View view) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showNoInternetDialog(this);
        }else {
        String b = getIntent().getStringExtra("roll");
        Intent i = new Intent(this,Delete_Report.class);
        i.putExtra("roll",b);
        startActivity(i);}
    }
}