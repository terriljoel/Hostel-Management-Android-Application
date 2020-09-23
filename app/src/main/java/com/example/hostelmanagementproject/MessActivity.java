package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MessActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter ad;
    String s[]={"Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
        listView=(ListView)findViewById(R.id.ll);
        ad=new ArrayAdapter<String>(this,R.layout.l2,s);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String day=ad.getItem(position).toString();
        Intent i=new Intent(getApplicationContext(),DispMessActivity.class);
            i.putExtra("day",day);
            startActivity(i);

    }
}
