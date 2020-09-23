package com.example.hostelmanagementproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmanagementproject.C1Activity;
import com.example.hostelmanagementproject.ContactinfoActivity;
import com.example.hostelmanagementproject.M2Activity;
import com.example.hostelmanagementproject.MActivity;
import com.example.hostelmanagementproject.MainPageActivity;
import com.example.hostelmanagementproject.MessActivity;
import com.example.hostelmanagementproject.R;
import com.example.hostelmanagementproject.SettingActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private int images[];
    private String names[];

    public MyAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.images = images;
        this.names = names;
    }


    @NonNull
    @Override
    public  MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_layout,null);

        MyHolder myHolder=new MyHolder(layout);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder viewHolder, final int i) {
        viewHolder.img.setImageResource(images[i]);
        viewHolder.txt.setText(names[i]);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(names[i]=="Mess\nTimetable")
                {
                    Intent i4=new Intent(context,MessActivity.class);

                    context.startActivity(i4);

                }
                else if (names[i] == "About") {
                    Intent i5=new Intent(context, SettingActivity.class);
                    context.startActivity(i5);
                }
                else if (names[i] == "Contact\ninfo") {
                    Intent i6=new Intent(context, ContactinfoActivity.class);
                    context.startActivity(i6);
                }
                else if(names[i]=="Movement \nRegister")
                {
                    Intent i7=new Intent(context, MActivity.class);
                    context.startActivity(i7);
                }
                else if(names[i]=="Circulars")
                {
                    Intent i8=new Intent(context, C1Activity.class);
                    context.startActivity(i8);
                }
                else if(names[i]=="Maintanance \nRegister")
                {
                    Intent i7=new Intent(context, M2Activity.class);
                    context.startActivity(i7);
                }
                else
                    Toast.makeText(context,names[i],Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.length;
    }



    public static class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView txt;
        public MyHolder( View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView2);
            txt=(TextView)itemView.findViewById(R.id.textView3);
        }
    }


}
