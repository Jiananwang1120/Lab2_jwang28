package com.cs60333.jwang28.lab2_jwang28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        ArrayList<String[]> schedule = new ArrayList<String[]>();
        String[] florida = {"fsl", "Forida State   Feb 11"};
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
    }
}
