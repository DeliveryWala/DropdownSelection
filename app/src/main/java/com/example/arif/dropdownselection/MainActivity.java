package com.example.arif.dropdownselection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MyDBHelper helper;
    Spinner region, disease;
    String regionSelected;

     String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDBHelper(this);
        region = (Spinner) findViewById(R.id.spinRegion);
        disease = (Spinner) findViewById(R.id.spinDisease);
        region.setOnItemSelectedListener(this);
        disease.setOnItemSelectedListener(this);
      /*  helper.insertEntry("Arif","Delhi","Ortho");
        helper.insertEntry("Arif1","Dehradun","Patho");
        helper.insertEntry("Arif2","Mumbai","Dermo");
        helper.insertEntry("Arif3","Bangalore","Neuro");
        helper.insertEntry("Arif4","Delhi","Ortho");
        helper.insertEntry("Arif5","Dehradun","Patho");
        helper.insertEntry("Arif6","Mumbai","Dermo");
        helper.insertEntry("Arif7","Bangalore","Neuro");helper.insertEntry("Arif10","Mumbai","Dermo");
        helper.insertEntry("Arif8","Dehradun","Patho");
        helper.insertEntry("Arif9","Delhi","Ortho");*/


        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Recycler.class);
                intent.putExtra("region",regionSelected);
                intent.putExtra("disease",item);
                startActivity(intent);
            }
        });

        List<String> regionCategories = new ArrayList<>();
        regionCategories.add("All");
        regionCategories.add("Bangalore");
        regionCategories.add("Delhi");
        regionCategories.add("Dehradun");
        regionCategories.add("Mumbai");

        List<String> diseaseCategories = new ArrayList<>();
        diseaseCategories.add("All");
        diseaseCategories.add("Ortho");
        diseaseCategories.add("Dermo");
        diseaseCategories.add("Patho");
        diseaseCategories.add("Neuro");

        ArrayAdapter<String> regionAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,regionCategories);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(regionAdapter);

        ArrayAdapter<String> diseaseAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,diseaseCategories);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disease.setAdapter(diseaseAdapter);






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinRegion)
        {
            regionSelected= parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected Region: " + regionSelected, Toast.LENGTH_LONG).show();
        }
        else if(spinner.getId() == R.id.spinDisease)
        {
             item = parent.getItemAtPosition(position).toString();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
