package com.example.hostelmanagementproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    ImageView i1,i2;
    TextView t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        i1=(ImageView)findViewById(R.id.imageView3);
        i2=(ImageView)findViewById(R.id.imageView4);
        t1=(TextView)findViewById(R.id.textView131);
        t2=(TextView)findViewById(R.id.textView141);
        String s1="Name :Swaraj YJ \nUSN :4nm16cs160\nSem :6\nSection :D\nCollege :NMAM institute of technology\nEmail :yjswaraj@gmail.com";
        t1.setText(s1);
        String s2="Name :Teril Joel Narazeth\nUSN :4nm16cs162\nSem :6\nSection :D\nCollege :NMAM institute of technology\nEmail :terriljoel@gmail.com";
        t2.setText(s2);
    }
}
