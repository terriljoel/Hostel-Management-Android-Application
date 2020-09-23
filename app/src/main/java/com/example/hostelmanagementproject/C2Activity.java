package com.example.hostelmanagementproject;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class C2Activity extends AppCompatActivity {

    private TextView text1;
    private PDFView pdfview;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    String receiverId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_c2);
        receiverId=getIntent().getExtras().get( "visit" ).toString();
        // Toast.makeText( getApplicationContext(),receiverId,Toast.LENGTH_LONG).show();
        pdfview=(PDFView)findViewById( R.id.pdfview );
        text1=(TextView)findViewById( R.id.text1 );
        DatabaseReference mref=db.getReference(receiverId);
        mref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                text1.setText( value );
                String url=text1.getText().toString();
                new RetrievePdfStream().execute(url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
    class RetrievePdfStream extends AsyncTask<String,Void,InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream( urlConnection.getInputStream() );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfview.fromStream( inputStream ).load();
        }
    }
}
