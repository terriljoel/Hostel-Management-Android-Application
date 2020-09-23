package com.example.hostelmanagementproject;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

public class MaiUpdateDeleteActivity extends AppCompatActivity {

    EditText e1, e2,e3;
    Button b1, b2;
    DatabaseReference reff;
    MaintenanceRegisterClass mrc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai_update_delete);

        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        mrc2 = new MaintenanceRegisterClass();
        e1 = (EditText) findViewById(R.id.editTexxt7);
        e2 = (EditText) findViewById(R.id.editTexxt11);
        e3 = (EditText) findViewById(R.id.editTexxt12);
        b1 = (Button) findViewById(R.id.buttoon6);
        b2 = (Button) findViewById(R.id.buttoon7);
        final String bc = MainPageActivity.b;
        reff= FirebaseDatabase.getInstance().getReference().child("Maintenance Register");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child(bc).child(id).exists()){

                    return;
                }


                String i = dataSnapshot.child(bc).child(id).child("issue").getValue().toString();
                String bn = dataSnapshot.child(bc).child(id).child("blockno").getValue().toString();
                String rn = dataSnapshot.child(bc).child(id).child("roomno").getValue().toString();








                e1.setText(i);
                e2.setText(bn);
                e3.setText(rn);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff=FirebaseDatabase.getInstance().getReference().child("Maintenance Register");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(bc).child(id).exists()) {
                            String y = dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                            if (y.equalsIgnoreCase("1")) {
                                Toast.makeText(getApplicationContext(), "Updatation not allowed", Toast.LENGTH_LONG).show();
                                return;
                            }

                            String i = e1.getText().toString().trim();
                            String bn = e2.getText().toString().trim();
                            String rn= e3.getText().toString().trim();



                            if(i.equalsIgnoreCase(""))
                            {
                                e1.setError("Fields cannot be empty");
                                return ;
                            }
                            if(bn.equalsIgnoreCase(""))
                            {
                                e2.setError("Fields cannot be empty");
                                return ;
                            }
                            if(rn.equalsIgnoreCase(""))
                            {
                                e3.setError("Fields cannot be empty");
                                return ;
                            }
                            int intbn=Integer.parseInt(bn);
                            if(intbn<=0||intbn>=6)
                            {
                                e2.setError("Invalid block number");
                                return;
                            }
                            int intrn=Integer.parseInt(rn);
                            if(intrn<=0)
                            {
                                e3.setError("Invalid room number");
                                return;
                            }
                            String date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());



                            mrc2.setIssue(i);
                            mrc2.setBlockno(bn);
                            mrc2.setRoomno(rn);
                            mrc2.setDate(date);


                            reff.child(bc).child(id).setValue(mrc2);
                            Toast.makeText(getApplicationContext(), "Updated Sucessfully", Toast.LENGTH_LONG).show();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff=FirebaseDatabase.getInstance().getReference().child("Maintenance Register");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String y = dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                        if (y.equalsIgnoreCase("1")) {
                            Toast.makeText(getApplicationContext(), "Deletion not allowed", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (dataSnapshot.child(bc).child(id).exists()) {

                            reff.child(bc).child(id).removeValue();


                            Toast.makeText(getApplicationContext(), "Deletion successfull", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
