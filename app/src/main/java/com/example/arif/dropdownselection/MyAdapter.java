package com.example.arif.dropdownselection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arif on 24/09/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    Context context;
    List<Information> data= Collections.emptyList();

    MyAdapter(Context context,  List<Information> data){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        Log.d("Arif"," onCreateViewHolder called ");
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("Arif"," onBindViewHolder called "+position);
        Information current=data.get(position);
        holder.drName.setText(current.getDrname());
        holder.region.setText(current.getRegion());
        holder.disease.setText(current.getDisease());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
                TextView drName,disease,region;
        public MyViewHolder(View itemView) {
            super(itemView);
            drName= (TextView) itemView.findViewById(R.id.drname);
            region= (TextView) itemView.findViewById(R.id.region);
            disease= (TextView) itemView.findViewById(R.id.disease);

        }
    }

}
