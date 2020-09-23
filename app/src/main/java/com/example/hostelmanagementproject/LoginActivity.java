package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2;
    Button b1;
    ProgressBar progressBar;

    String p,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        b1.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {

        String usn=e1.getText().toString().trim();
        final String password=e2.getText().toString().trim();
        if(usn.equalsIgnoreCase(""))
        {
            e1.setError("Usn field cannot be empty");
            return ;
        }
        if(password.equalsIgnoreCase(""))
        {
            e2.setError("Password field cannot be empty");
            return ;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("login").child(usn);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    e1.setError("Enter valid email");
                    return ;

                }
                 p=dataSnapshot.child("password").getValue().toString();
                 b=dataSnapshot.child("biocode").getValue().toString();
                 if(!p.equalsIgnoreCase(password))
                 {
                     e2.setError("Enter Valid password");
                     return ;
                 }
                 progressBar.setVisibility(View.VISIBLE);
                Intent i=new Intent(getApplicationContext(),MainPageActivity.class);
                 i.putExtra("biocode",b);
                 startActivity(i);
                 finish();



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
