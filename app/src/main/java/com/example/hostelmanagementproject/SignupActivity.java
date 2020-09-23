package com.example.hostelmanagementproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    TextView t7;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        t7=(TextView)findViewById(R.id.textView7);
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i1=new Intent(SignupActivity.this,MainPageActivity.class);
        startActivity(i1);
    }
}
