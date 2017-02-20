package com.cs60333.jwang28.lab2_jwang28;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  String[] names = {"Ohio State", "Florida State", "Wake Forest", "Boston College", "North Carolina State", "Georgia Tech", "North Virginia", "Chicago Sate"};
        final ArrayList<String[]> schedule = new ArrayList<String[]>();
        String[] florida = {"fsl", "Forida State   Feb 11", "Saturday, February 11, 6:00PM\nPurcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Florida State\nSeminoles\n(21-5)", "72-84\nfinal"};
        String[] boston = {"bcl", "Boston College   Feb 14"};
        String[] nc = {"ncl", "North Carolina State   Feb 18"};
        String[] gt = {"gtl", "Georgia Tech   Feb 26"};
        String[] boston2 = {"bcl", "Boston College   March 1"};
        String[] louis = {"lvl", "Louisville   March 4"};
        String[] acc = {"accl", "ACC Tournament    March 7"};
        String[] ncaa = {"ncaal", "NCAA Tournament   March 16"};
        schedule.add(florida);
        schedule.add(boston);
        schedule.add(nc);
        schedule.add(gt);
        schedule.add(boston2);
        schedule.add(louis);
        schedule.add(acc);
        schedule.add(ncaa);

        //  ListAdapter scheduleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ScheduleAdapter scheduleAdapter = (ScheduleAdapter) new ScheduleAdapter(this, schedule);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);

        scheduleListView.setAdapter(scheduleAdapter);

//    DetailActivity detailActivity = (DetailActivity) new DetailActivity(this, detail);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//Write code here to open the activity that will show details of the game event,i.e. if //you click on Florida State, you should see details of the match between Florida State //and Notre Dame. You need to do the following three steps.

//create the intent to start DetailActivity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //startActivity(intent);
//create a bundle object using the following:
//Bundle bundle = new Bundle();
                intent.putExtra("team", schedule.get(position)); // where al is your ArrayList holding team information.
                startActivity(intent);
//start the activity using the intent with the bundle you just created.

            }

        };

// this will automatically attach the listener to each item of the listview.

        scheduleListView.setOnItemClickListener(clickListener);
    }
}
