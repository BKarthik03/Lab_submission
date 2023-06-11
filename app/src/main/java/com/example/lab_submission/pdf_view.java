package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class pdf_view extends AppCompatActivity {

    Button view_pdf;
    EditText roll_n;

    String i;
    Spinner sp;
    String roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        view_pdf=findViewById(R.id.view_report);
        roll_n=findViewById(R.id.view_roll);
        sp=findViewById(R.id.subjects);

        ArrayList<String> ar = new ArrayList<>();
        ar.add("Choose the Subject");
        ar.add("Java");
        ar.add("Go");
        ar.add("Android");
        ar.add("DS");
        ar.add("PHP");

       /* ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,ar);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);*/
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, ar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i =ar.get(position);
                if(position!=0) {
                    Toast.makeText(pdf_view.this, "Selected Subject is " + i, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(pdf_view.this)) {
                    NetworkUtils.showNoInternetDialog(pdf_view.this);
                }else {
                roll=roll_n.getText().toString();
                retrievePDFFromDatabase(roll);

            }}
        });}

    private void retrievePDFFromDatabase(String roll) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        String childPath = "Students/"+roll+"/files/"+i ; // Replace with your desired child path

        databaseRef.child(childPath).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String downloadUrl = dataSnapshot.getValue(String.class);
                if (downloadUrl != null && !downloadUrl.isEmpty()) {
                    openPDF(downloadUrl);
                } else {
                    // Handle the case where the download URL is not available
                    Toast.makeText(pdf_view.this, "Report not found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle the failure case
                Toast.makeText(pdf_view.this, "Failed to retrieve Report from database", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void openPDF(String downloadUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(downloadUrl), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Handle the case where a PDF viewer app is not installed
            Toast.makeText(pdf_view.this, "No PDF viewer app installed", Toast.LENGTH_SHORT).show();
        }
    }


}