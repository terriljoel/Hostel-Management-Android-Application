package com.example.hostelmanagementproject;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.hostelmanagementproject.MovInsertActivity.DATE_FORMAT;

public class MovUpdateDeleteActivity extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1, b2;
    DatabaseReference reff;
    MovementRegisterClass mrc;
    boolean ll,al;
    final static String DATE_FORMAT="dd-MM-yyyy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov_update_delete);
        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        mrc = new MovementRegisterClass();

        e1 = (EditText) findViewById(R.id.editText7);
        e2 = (EditText) findViewById(R.id.editText11);
        e3 = (EditText) findViewById(R.id.editText12);
        b1 = (Button) findViewById(R.id.button6);
        b2 = (Button) findViewById(R.id.button7);
        final String bc = MainPageActivity.b;
        reff=FirebaseDatabase.getInstance().getReference().child("Movement Register");


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child(bc).child(id).exists()){

                    return;
                }

                String d = dataSnapshot.child(bc).child(id).child("destination").getValue().toString();
                String l = dataSnapshot.child(bc).child(id).child("leavedate").getValue().toString();
                String a = dataSnapshot.child(bc).child(id).child("arrivaldate").getValue().toString();
                e1.setText(d);
                e2.setText(l);
                e3.setText(a);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff=FirebaseDatabase.getInstance().getReference().child("Movement Register");

                reff.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(bc).child(id).exists()) {
                            String y = dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                            if (y.equalsIgnoreCase("1")) {
                                Toast.makeText(getApplicationContext(), "Updatation not allowed", Toast.LENGTH_LONG).show();
                                return;
                            }


                            String d = e1.getText().toString().trim();
                            String l = e2.getText().toString().trim();
                            String a = e3.getText().toString().trim();

                            if(d.equalsIgnoreCase(""))
                            {
                                e1.setError("Fields cannot be empty");
                                return ;
                            }
                            if(l.equalsIgnoreCase(""))
                            {
                                e2.setError("Fields cannot be empty");
                                return ;
                            }
                            if(a.equalsIgnoreCase(""))
                            {
                                e3.setError("Fields cannot be empty");
                                return ;
                            }




                            ll=isDateValid(l);
                            if(!ll)
                            {
                                e2.setError("Invalid Date format");
                                return ;
                            }
                            al=isDateValid(a);
                            if(!al)
                            {
                                e3.setError("Invalid Date format");
                                return ;
                            }
                            String date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
                            Calendar calendar1=Calendar.getInstance();
                            Calendar calendar2=Calendar.getInstance();
                            Date date1 = null,date2=null;

                            try {
                                date1=dateFormat.parse(date);
                                date2=dateFormat.parse(l);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            calendar1.setTime(date1);
                            calendar2.setTime(date2);
                            int r=calendar1.compareTo(calendar2);

                            if(r>0){
                                e2.setError("Invalid date");
                                return;
                            }

                            try {
                                date1=dateFormat.parse(l);
                                date2=dateFormat.parse(a);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            calendar1.setTime(date1);
                            calendar2.setTime(date2);
                            r=calendar1.compareTo(calendar2);

                            if(r>0){
                                e3.setError("Invalid date");
                                return;
                            }












                            mrc.setDestination(d);
                            mrc.setArrivaldate(a);
                            mrc.setLeavedate(l);
                            mrc.setDate(date);
                            mrc.setStatus("0");

                            reff.child(bc).child(id).setValue(mrc);
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




                reff=FirebaseDatabase.getInstance().getReference().child("Movement Register");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String y=dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                        if(y.equalsIgnoreCase("1"))
                        {
                            Toast.makeText(getApplicationContext(),"Deletion not allowed",Toast.LENGTH_LONG).show();
                            return ;
                        }
                        if(dataSnapshot.child(bc).child(id).exists()) {

                            reff.child(bc).child(id).removeValue();


                            Toast.makeText(getApplicationContext(), "Deletion successfull", Toast.LENGTH_LONG).show();
                            finish();
                        }

                  //      Intent i=new Intent(getApplicationContext(),MovDisplayActivity.class);
                    //    startActivity(i);







                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isDateValid(String date)
    {
        try
        {
            DateFormat df=new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }










    }


/*
    @Override
    super.

    onCreate(savedInstanceState);

    setContentView(R.layout.activity_mov_update_delete);

    Intent i = getIntent();
    final String id = i.getStringExtra("id");
    mrc =new

    MovementRegisterClass();

    reff =FirebaseDatabase.getInstance().

    getReference("Movement Register");

    e1=(EditText)

    findViewById(R.id.editText7);

    e2=(EditText)

    findViewById(R.id.editText11);

    e3=(EditText)

    findViewById(R.id.editText12);

    b1=(Button)

    findViewById(R.id.button6);

    b2=(Button)

    findViewById(R.id.button7);

    final String bc = MainPageActivity.b;

        reff.addValueEventListener(new

    ValueEventListener() {
        @Override
        public void onDataChange (@NonNull DataSnapshot dataSnapshot){

            String d = dataSnapshot.child(bc).child(id).child("destination").getValue().toString();
            String l = dataSnapshot.child(bc).child(id).child("leavedate").getValue().toString();
            String a = dataSnapshot.child(bc).child(id).child("arrivaldate").getValue().toString();
            e1.setText(d);
            e2.setText(l);
            e3.setText(a);

        }

        @Override
        public void onCancelled (@NonNull DatabaseError databaseError){

        }
    });
}
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String y=dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                        if(y.equalsIgnoreCase("1"))
                        {
                            Toast.makeText(getApplicationContext(),"Updatation not allowed",Toast.LENGTH_LONG).show();
                            return ;
                        }


                        String d = e1.getText().toString().trim();
                        String l = e2.getText().toString().trim();
                        String a = e3.getText().toString().trim();


                        mrc.setDestination(d);
                        mrc.setArrivaldate(a);
                        mrc.setLeavedate(l);
                        mrc.setStatus("0");

                        reff.child(bc).child(id).setValue(mrc);
                        Toast.makeText(getApplicationContext(),"Updated Sucessfully",Toast.LENGTH_LONG).show();

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
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String y=dataSnapshot.child(bc).child(id).child("status").getValue().toString().trim();
                        if(y.equalsIgnoreCase("1"))
                        {
                            Toast.makeText(getApplicationContext(),"Deletion not allowed",Toast.LENGTH_LONG).show();
                            return ;
                        }
                       DatabaseReference d=FirebaseDatabase.getInstance().getReference("Movement Register").child(bc).child(id);
                        d.onDisconnect().removeValue();

                        Toast.makeText(getApplicationContext(),"Deletion successfull",Toast.LENGTH_LONG).show();

                        Intent i=new Intent(getApplicationContext(),MovDisplayActivity.class);
                          startActivity(i);
                          reff=null;



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
*/
