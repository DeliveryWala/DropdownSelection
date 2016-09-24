package com.example.arif.dropdownselection;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Recycler extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<Information> data;
    MyDBHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recycler);
        data=new ArrayList<>();
        helper = new MyDBHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        Intent intent = getIntent();
        String region = intent.getStringExtra("region");
        String disease = intent.getStringExtra("disease");
        Log.d("Arif","Region"+region);
        Log.d("Arif","Disease"+disease);
        data= helper.checkInfo(region, disease);
        Log.d("Arif","Data"+data.toString());
        adapter = new MyAdapter(this,data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}


