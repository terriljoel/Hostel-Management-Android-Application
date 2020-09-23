package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MovDisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> list,temp,reffno1,reffno2;
    ArrayAdapter<String> adapter;
    ListView listView;
    DatabaseReference myreff=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov_display);
        listView=(ListView)findViewById(R.id.lv);


        list=new ArrayList<>();
        temp=new ArrayList<>();
        reffno1=new ArrayList<>();
        reffno2=new ArrayList<>();

        listView.setOnItemClickListener(this);
        adapter=new ArrayAdapter<String>(this,R.layout.mlist,R.id.tvlist,list);

        myreff=FirebaseDatabase.getInstance().getReference("Movement Register");
        myreff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.child(MainPageActivity.b).getChildren())
                {

                        String r = ds.getKey();
                      String s = "Ref No      :" + ds.getKey() + "\nLeave Date  :" + ds.child("leavedate").getValue() + "\nDestination :" + ds.child("destination").getValue() + "\nArrival Date :" + ds.child("arrivaldate").getValue();

                        temp.add(s);
                        reffno1.add(r);


                }
                int n= (int) dataSnapshot.child(MainPageActivity.b).getChildrenCount();
                for(int i=n-1;i>=0;i--)
                {
                    list.add(temp.get(i));
                    reffno2.add(reffno1.get(i));
                }
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getApplicationContext(),MovUpdateDeleteActivity.class);
        i.putExtra("id",reffno2.get(position));
        startActivity(i);





    }



    @Override
   protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
