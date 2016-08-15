package com.example.jeffrey.investingcalculator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ValueCalculatorActivity extends AppCompatActivity {

    ListView list;
    String[] itemname ={
            "Benjamin Graham Formula",
            "Buffet's Value Formula",
            "DCF Method",
            "Reverse DCF",
            "Earnings Power Value (EPV)",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    String[] itemdesc ={
            "Using Benjamin Graham’s Formula to Value a Stock",
            "Buffet Number",
            "DCF Method",
            "Reverse DCF",
            "Earnings Power Value (EPV)",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    Integer[] imgid={
            R.drawable.stockchart,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid,itemdesc);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                Intent intent = null;

                // Open value calculator activity
                if (position == 0) {
                    //intent = new Intent(getBaseContext(), MainActivity.class);
                    intent = new Intent(getBaseContext(), GrahamValuation.class);
                    intent.putExtra("id", position);
                    intent.putExtra("act_title", "Graham Value Formula");
                }

                if(intent != null)
                    startActivity(intent);
            }
        });

    }

}
