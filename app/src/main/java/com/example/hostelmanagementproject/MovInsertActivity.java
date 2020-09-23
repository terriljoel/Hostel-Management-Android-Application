package com.example.hostelmanagementproject;

import android.icu.text.DateFormat;
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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MovInsertActivity extends AppCompatActivity {

    EditText  destination, leavedate, arrivaldate;
    Button add;
    DatabaseReference reff;
    MovementRegisterClass mrc;
    final static String DATE_FORMAT="dd-MM-yyyy";
    boolean l1,a1;
    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov_insert);
        destination = (EditText) findViewById(R.id.editText8);
        leavedate = (EditText) findViewById(R.id.editText9);
        arrivaldate = (EditText) findViewById(R.id.editText10);
        add = (Button) findViewById(R.id.button5);
        mrc = new MovementRegisterClass();
        FirebaseApp.initializeApp(this);

        reff = FirebaseDatabase.getInstance().getReference("Movement Register");


        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String bc = MainPageActivity.b;
                String d = destination.getText().toString().trim();
                String l = leavedate.getText().toString().trim();
                String a = arrivaldate.getText().toString().trim();


                if(d.equalsIgnoreCase(""))
                {
                    destination.setError("Fields cannot be empty");
                    return ;
                }
                if(l.equalsIgnoreCase(""))
                {
                    leavedate.setError("Fields cannot be empty");
                    return ;
                }
                if(a.equalsIgnoreCase(""))
                {
                    arrivaldate.setError("Fields cannot be empty");
                    return ;
                }
                l1=isDateValid(l);
                if(!l1)
                {
                    leavedate.setError("Invalid Date format");
                    return ;
                }
                a1=isDateValid(a);
                if(!a1)
                {
                    arrivaldate.setError("Invalid Date format");
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
                       leavedate.setError("Invalid date");
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
                    arrivaldate.setError("Invalid date");
                    return;
                }




                mrc.setDestination(d);
                mrc.setArrivaldate(a);
                mrc.setLeavedate(l);
                mrc.setDate(date);
                mrc.setStatus("0");
                String id = reff.push().getKey();
              //  Toast.makeText(getApplicationContext(), "id is " + id, Toast.LENGTH_LONG).show();
                reff.child(bc).child(id).setValue(mrc);
                Toast.makeText(getApplicationContext(),"Registered Sucessfully",Toast.LENGTH_LONG).show();
                finish();



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
