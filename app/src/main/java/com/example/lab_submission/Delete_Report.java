package com.example.lab_submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Delete_Report extends AppCompatActivity {

    Spinner spinner;
    Button delete;

     FirebaseDatabase database;
     FirebaseStorage storage;
    String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_report);
        spinner = findViewById(R.id.spinner2);
        delete = findViewById(R.id.del_file_btn);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        ArrayList<String> ar = new ArrayList<>();
        // ar.add("Choose the Subject");
        ar.add("Please select subject"); // Unselectable item

        ar.add("Java");
        ar.add("Go");
        ar.add("Android");
        ar.add("DS");
        ar.add("PHP");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, ar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       /* ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,ar);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);*/

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i =ar.get(position);
                if(position!=0){
                Toast.makeText(Delete_Report.this, "Selected Subject "+i, Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String b = getIntent().getStringExtra("roll");
                    if (!NetworkUtils.isNetworkAvailable(Delete_Report.this)) {
                        NetworkUtils.showNoInternetDialog(Delete_Report.this);
                    }else{

                        // Construct the file URL based on the selected subject
                    String subject = i;
                    String fileName = b + "_" + i + ".pdf"; // Replace with the actual file name
                    String fileUrl = "https://firebasestorage.googleapis.com/v0/b/report-f447b.appspot.com/o/Reports%2F" + fileName + "?alt=media&token=0b7fb4f0-6047-4f20-b78d-9fb86091d879";

                    DatabaseReference fileRef = database.getReference().child("Students").child(b).child("files").child(i);
                    fileRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String fileUrl = dataSnapshot.getValue(String.class);

                                // Create a StorageReference from the fileUrl
                                StorageReference storageRef = storage.getReferenceFromUrl(fileUrl);

                                // Delete the file from Firebase Storage
                                storageRef.delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // File deleted successfully

                                                // Remove the file URL from the database
                                                dataSnapshot.getRef().removeValue();

                                                Toast.makeText(Delete_Report.this, "File deleted successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // An error occurred while deleting the file
                                                Toast.makeText(Delete_Report.this, "Failed to delete file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // File URL not found for the specified roll number and subject
                                Toast.makeText(Delete_Report.this, "Report File not exists", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Failed to query the database
                            Toast.makeText(Delete_Report.this, "Failed to query the database: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }}
            });

        }

    }
