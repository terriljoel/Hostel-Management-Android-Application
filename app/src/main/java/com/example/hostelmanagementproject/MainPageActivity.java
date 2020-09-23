package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {
    public static String b;

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    private String nameList[]=
            {
                    "Circulars","Mess\nTimetable",
                    "Movement \nRegister","Maintanance \nRegister",
                    "Contact\ninfo","About"
            };
    private int icondId[]=
            {
                    R.drawable.circular, R.drawable.timetable,
                    R.drawable.attendance, R.drawable.maintance,
                    R.drawable.call, R.drawable.info
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Intent i=getIntent();
        b=i.getStringExtra("biocode");



        recyclerView =(RecyclerView)findViewById(R.id.rv);
        layoutManager=new GridLayoutManager(MainPageActivity.this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        com.example.hostelmanagementproject.adapter.MyAdapter myAdapter=new com.example.hostelmanagementproject.adapter.MyAdapter(MainPageActivity.this,icondId,nameList);

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.m1,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutid:
                Intent i2=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
