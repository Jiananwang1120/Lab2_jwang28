package com.cs60333.jwang28.lab2_jwang28;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     /*   if (build.VERSION.SDK_INT >= build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(4);
        }*/
        setSupportActionBar(toolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
// code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
            shareIntent.putExtra("android.content.Intent.EXTRA_TEXT", gameSchedule());
            startActivity(Intent.createChooser(shareIntent), "Share via");
        } else if (res_id == R.id.sync) {
// Snackbar with Try Again action
        } else if (res_id == R.id.settings) {
// Floating Contextual Menu with options

        }
        return true;
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

// here write code to inflate floating_contextual_menu xml file

    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        int item_id = item.getItemId();

        if (item_id == R.id.women) {

// to be implemented later

        }

        and so on ...

        return false;

    }

    Coordinatorlayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

    SnackBar snackbar = SnackBar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);

    snackbar.setAction("Try Again", new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            SnackBar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();

        }

    };

    snackbar.show();

};

    /* final ArrayList<Team> teams = new ArrayList<>();
        Team team1 = new Team(new String[]{"fsl", "Florida State Feb 11", "Saturday, February 11, 6:00PM\nPurcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Florida State\nSeminoles\n(21-5)", "72-84\nFinal", "Notre Dame\nFighting Irish\n(19-7)","fsl", "ndl"});
        Team team2 = new Team(new String[]{"bcl", "Boston College   Feb 14", "Tuesday, February 14, 7:00PM\nSilvio O. Conte Forum, Chestnut Hill, Massachusetts", "Notre Dame\nFighting Irish\n(20-7)", "84-76\nFinal", "Boston College\nEagles\n(9-18)","ndl","bcl"});
        Team team3 = new Team(new String[]{"ncl", "North Carolina State   Feb 18", "Saturday, February 18, 12:00PM\nPNC Arena, Raleigh, North Carolina", "Notre Dame\nFighting Irish\n(21-7)", "81-72\nFinal", "North Carolina State\nWolfpack\n(14-14)","ndl","ncl"});
        Team team4 = new Team(new String[]{"gtl", "Georgia Tech   Feb 26", "Sunday, February 26, 6:30PM\nPurcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Georgia Tech\nYellow Jackets\n(16-11)", "","Notre Dame\nFighting Irish\n(21-7)","gtl","ndl"});
        Team team5 = new Team(new String[]{"bcl", "Boston College   March 1", "Wednesday, March 1, 8:00PM\nPurcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Boston College\nEagles\n(9-19)", "", "Notre Dame\nFighting Irish\n(21-7)","bcl","ndl"});
        Team team6 = new Team(new String[]{"lvl", "Louisville   March 4", "Saturday, March 4, 2:00PM\nKFC yum! Center, Louisville, Kentucky", "Notre Dame\nFighting Irish\n(21-7)", "", "Louisville\nCardinals\n(22-5)","ndl","lvl"});
        Team team7 = new Team(new String[]{"accl", "ACC Tournament    March 7","","", "","", "ndl","accl"});
        Team team8 = new Team(new String[]{"ncaal", "NCAA Tournament   March 16","","","", "", "ndl","ncaal"});
*/
    //create a csvreader object
    MyCsvFileReader csvReader = new MyCsvFileReader(this);
    //ArrayList<String[]> teamInfo = call readcsv funtion of the csvobject
    int resID = getResources().getIdentifier("schedule", "raw", getPackageName());
    ArrayList<String[]> teamInfo = csvReader.readCsvFile(resID);
    //for loop to iterate over teamInfo to create ArrayList teams

    final ArrayList<Team> teams = new ArrayList<>();
    for(int i = 0;i<teamInfo.size();i++)

    {
        String[] str = teamInfo.get(i);
        //     Team team = new Team ()
        Team team = new Team(str);
        teams.add(team);
    }

    /* teams.add(team1);
     teams.add(team2);
     teams.add(team3);
     teams.add(team4);
     teams.add(team5);
     teams.add(team6);
     teams.add(team7);
     teams.add(team8);
*/
    //  ListAdapter scheduleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
    ScheduleAdapter scheduleAdapter = (ScheduleAdapter) new ScheduleAdapter(this, teams);
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
            intent.putExtra("team", teams.get(position)); // where al is your ArrayList holding team information.
            // String[] matchItem = getItem(position);
            //TextView date = (TextView) findViewById(R.id.date);
            //TextView info = (TextView) findViewById(R.id.info);
            //TextView score = (TextView) findViewById(R.id.score);

            startActivity(intent);
//start the activity using the intent with the bundle you just created.

        }

    };

// this will automatically attach the listener to each item of the listview.

    scheduleListView.setOnItemClickListener(clickListener);
}
}
