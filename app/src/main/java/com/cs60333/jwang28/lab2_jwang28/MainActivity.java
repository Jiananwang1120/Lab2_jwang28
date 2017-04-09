package com.cs60333.jwang28.lab2_jwang28;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ArrayList<Team> teams;
    DBHelper dbHelper = new DBHelper(this.getApplicationContext());

    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        MyCsvFileReader csvReader = new MyCsvFileReader(this);
        //ArrayList<String[]> teamInfo = call readcsv funtion of the csvobject
        int resID = getResources().getIdentifier("schedule", "raw", getPackageName());
        ArrayList<String[]> teamInfo = csvReader.readCsvFile(resID);
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_NAME, String.valueOf(teamInfo));
        dbHelper.insertData("Team", contentValues);

        //for loop to iterate over teamInfo to create ArrayList teams

        teams = new ArrayList<>();
        for (int i = 0; i < teamInfo.size(); i++) {
            String[] str = teamInfo.get(i);
            Team team = new Team(str);
            teams.add(team);
        }

        ScheduleAdapter scheduleAdapter = (ScheduleAdapter) new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);

        scheduleListView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("team", teams.get(position)); // where al is your ArrayList holding team information.

                startActivity(intent);
            }

        };

        scheduleListView.setOnItemClickListener(clickListener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public String gameSchedule() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0;i<teams.size();i++) {
            String str = teams.get(i).getTeamName() + ", " + teams.get(i).getPlace() + ", " + teams.get(i).getDate() + "\n";
            builder.append(str);
        }

        String result = builder.toString();
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
// code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            //startActivity(shareIntent);
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));

        } else if (res_id == R.id.sync) {
// Snackbar with Try Again action
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);

            snackbar.setAction("Try Again", new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();

                }

            });

            snackbar.show();
        } else if (res_id == R.id.settings) {
// Floating Contextual Menu with options
            registerForContextMenu((ListView) findViewById(R.id.scheduleListView));
            openContextMenu((ListView) findViewById(R.id.scheduleListView));
            unregisterForContextMenu((ListView) findViewById(R.id.scheduleListView));
        }
        return true;
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.floating_contextual_menu, menu);
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        int item_id = item.getItemId();

        //if (item_id == R.id.women) {

// to be implemented later

        //}

        //and so on ...

        return false;

    }



};


