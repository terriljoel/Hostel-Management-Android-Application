package com.example.hostelmanagementproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class C1Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    static int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_c1 );
            mDatabase= FirebaseDatabase.getInstance().getReference().child( "Global" );
            mDatabase.keepSynced( true );
            recyclerView=(RecyclerView)findViewById( R.id.Recycle );
            recyclerView.setHasFixedSize( true );
            //circulars=new ArrayList<>(  );
            recyclerView.setLayoutManager( new LinearLayoutManager( this ) );




        }

        @Override
        protected void onStart() {
            super.onStart();
            FirebaseRecyclerAdapter<Circular,CViewHolder> fireballRecyclerAdapter;
            fireballRecyclerAdapter = new FirebaseRecyclerAdapter<Circular, CViewHolder>(Circular.class, R.layout.layout,CViewHolder.class,mDatabase) {
                @Override
                protected void populateViewHolder(CViewHolder viewHolder, Circular model, final int position) {
                    viewHolder.setTitle( model.getTitle() );
                    viewHolder.setCref( model.getCref() );
                    viewHolder.setDat( model.getDat() );
                    viewHolder.setImg( getApplicationContext(),model.getImg());
                    viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String visit=getRef( position ).getKey();
                            Intent cintent=new Intent( getApplicationContext(),C2Activity.class );
                            cintent.putExtra( "visit",visit );
                            startActivity( cintent );
                        }
                    } );



                }
            };
            recyclerView.setAdapter( fireballRecyclerAdapter );

        }
        public static class CViewHolder extends RecyclerView.ViewHolder
        {
            View mview;

            public CViewHolder(View itemView) {
                super( itemView );
                mview=itemView;

            }
            public void setTitle(String title)
            {
                TextView t1=(TextView)mview.findViewById( R.id.textViewTitle );
                t1.setText( title );
            }
            public void setCref(String cref)
            {
                TextView t1=(TextView)mview.findViewById( R.id.textViewRating );
                t1.setText( cref);
            }
            public void setDat(String Dat)
            {
                TextView t1=(TextView)mview.findViewById( R.id.textViewPrice );
                t1.setText(Dat );
            }
            public void setImg(Context ctx, String img)
            {
                ImageView t1=(ImageView) mview.findViewById( R.id.imageView);
                t1.setImageDrawable( ctx.getResources().getDrawable( R.drawable.download ) );
                //Picasso.with( ctx ).load( img ).into( t1 );
                //t1.setImageDrawable( ctx.getResources().getDrawable( R.drawable.download ) );
            }
    }
}
