package com.example.myjustgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    private Button btn_Upload_Address;

    Spinner spinner;
    EditText eData;
    String textData= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("spinner");
        eData = (EditText) findViewById(R.id.txt_add);
        spinner =(Spinner) findViewById(R.id.DS_dd);
        spinnerDataList = new ArrayList<>();
        adapter  = new ArrayAdapter<String>(ImagesActivity.this,
                android.R.layout.simple_spinner_dropdown_item,spinnerDataList);
        spinner.setAdapter(adapter);
        red();
    }


    private void red()
    {
        listener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerDataList.clear();
                for(DataSnapshot item:dataSnapshot.getChildren())
                {
                    spinnerDataList.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void btn_add(View view) {

        textData=eData.getText().toString().trim();
        listener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerDataList.clear();
                for(DataSnapshot item:dataSnapshot.getChildren())
                {
                    if(textData != item.getValue().toString())
                    {
                        mDatabaseRef.push().setValue(textData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                eData.setText("");

                                adapter.notifyDataSetChanged();
                                Toast.makeText(ImagesActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(ImagesActivity.this, "Data Insert Fail", Toast.LENGTH_SHORT).show();
                    }



                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
