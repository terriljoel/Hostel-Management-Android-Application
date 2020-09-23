package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DispMessActivity extends AppCompatActivity {
    TextView breakfast,lunch,dinner,tea,heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_mess);
        heading=(TextView)findViewById(R.id.textView8);
        breakfast=(TextView) findViewById(R.id.textView9);
        lunch=(TextView) findViewById(R.id.textView10);
        tea=(TextView)findViewById(R.id.textView11);
        dinner=(TextView) findViewById(R.id.textView12);
        Intent i=getIntent();
        String day=i.getStringExtra("day");
        String rday=day;
        day=day+" "+"Time Table";
        heading.setText(day);
        DatabaseReference reff= FirebaseDatabase.getInstance().getReference().child("Time Table").child(rday);
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sbreakfast=dataSnapshot.child("Breakfast").getValue().toString();
                String slunch=dataSnapshot.child("Lunch").getValue().toString();
                String stea=dataSnapshot.child("Tea").getValue().toString();
                String sdinner=dataSnapshot.child("Dinner").getValue().toString();

                sbreakfast="<u><b>"+"Breakfast :<br/>"+"</b></u>"+sbreakfast;
                breakfast.setText(Html.fromHtml(sbreakfast));

                slunch="<u><b>"+"Lunch :<br/>"+"</b></u>"+slunch;
                lunch.setText(Html.fromHtml(slunch));

                stea="<u><b>"+"Tea :<br/>"+"</b></u>"+stea;
                tea.setText(Html.fromHtml(stea));

                sdinner="<u><b>"+"Dinner :<br/>"+"</b></u>"+sdinner;
                dinner.setText(Html.fromHtml(sdinner));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
