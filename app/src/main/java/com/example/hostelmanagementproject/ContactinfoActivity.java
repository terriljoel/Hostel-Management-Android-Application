package com.example.hostelmanagementproject;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactinfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter<String> adapter;
    ListView listView;
    String s[]={"Chief Warden","Warden","Electrician","Hostel office"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinfo);
        listView=(ListView)findViewById(R.id.clv);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
        adapter=new ArrayAdapter<String>(this,R.layout.l3,s );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s=adapter.getItem(position).toString();
        if(s.equalsIgnoreCase("Chief Warden"))
        {
           Intent i=new Intent(Intent.ACTION_CALL);
           i.setData(Uri.parse("tel:"+"936274633732"));
           try{
           startActivity(i);
               }
               catch (Exception e)
               {
                   Toast.makeText(getApplicationContext(),"call permission denied",Toast.LENGTH_LONG).show();
               }
        }
        else if(s.equalsIgnoreCase("Warden"))
        {

            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+"876274453732"));
            try{
                startActivity(i);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"call permission denied",Toast.LENGTH_LONG).show();
            }
        }
        else if(s.equalsIgnoreCase("Electrician"))
        {
            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+"767273693732"));
            try{
                startActivity(i);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"call permission denied",Toast.LENGTH_LONG).show();
            }

        }

        else
        {
            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+"0824-23243647"));
            try{
                startActivity(i);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"call permission denied",Toast.LENGTH_LONG).show();
            }
        }

    }
}
