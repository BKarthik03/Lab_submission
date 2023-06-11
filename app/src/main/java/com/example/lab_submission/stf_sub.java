package com.example.lab_submission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stf_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stf_sub);
    }

    public void staff_get(View view) { if (!NetworkUtils.isNetworkAvailable(this)) {
        NetworkUtils.showNoInternetDialog(this);
    }else {

        Intent i = new Intent(this,pdf_view.class);
        startActivity(i);
    }}
}