package com.example.hostelmanagementproject;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class MaiInsertActivity extends AppCompatActivity {
    EditText issue,roomno,blockno;
    Button add;
    DatabaseReference reff;
    MaintenanceRegisterClass mrc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai_insert);
        issue = (EditText) findViewById(R.id.editTextt8);
        blockno = (EditText) findViewById(R.id.editTextt9);
        roomno = (EditText) findViewById(R.id.editTextt10);

        add = (Button) findViewById(R.id.buttonn5);
        mrc2 = new MaintenanceRegisterClass();
        FirebaseApp.initializeApp(this);
        reff = FirebaseDatabase.getInstance().getReference("Maintenance Register");
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String bc = MainPageActivity.b;
                String i = issue.getText().toString().trim();
                String bn = blockno.getText().toString().trim();
                String rn = roomno.getText().toString().trim();

                if(i.equalsIgnoreCase(""))
                {
                    issue.setError("Fields cannot be empty");
                    return ;
                }
                if(bn.equalsIgnoreCase(""))
                {
                    blockno.setError("Fields cannot be empty");
                    return ;
                }
                if(rn.equalsIgnoreCase(""))
                {
                    roomno.setError("Fields cannot be empty");
                    return ;
                }
                int intbn=Integer.parseInt(bn);
                if(intbn<=0||intbn>=6)
                {
                    blockno.setError("Invalid block number");
                    return;
                }
                int intrn=Integer.parseInt(rn);
                if(intrn<=0)
                {
                    roomno.setError("Invalid room number");
                    return;
                }
                String date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


                mrc2.setDate(date);

                mrc2.setBlockno(bn);
                mrc2.setIssue(i);
                mrc2.setRoomno(rn);
                mrc2.setStatus("0");

                String id = reff.push().getKey();

                reff.child(bc).child(id).setValue(mrc2);
                Toast.makeText(getApplicationContext(),"Registered Sucessfully",Toast.LENGTH_LONG).show();
                finish();



            }
        });
    }
}
