package com.cs60333.jwang28.lab2_jwang28;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Team> teams;
    DBHelper dbHelper;

    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this.getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        MyCsvFileReader csvReader = new MyCsvFileReader(this);
        //ArrayList<String[]> teamInfo = call readcsv funtion of the csvobject
        int resID = getResources().getIdentifier("schedule", "raw", getPackageName());
        ArrayList<String[]> teamInfo = csvReader.readCsvFile(resID);
      //  contentValues.put(dbHelper.COL_DATE, );


        //for loop to iterate over teamInfo to create ArrayList teams

        teams = new ArrayList<>();
        for (int i = 0; i < teamInfo.size(); i++) {
            String[] str = teamInfo.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COL_LOGO, String.valueOf(str[0]));
            contentValues.put(DBHelper.COL_NAME, String.valueOf(str[1]));
            contentValues.put(DBHelper.COL_DATE, String.valueOf(str[2]));
            contentValues.put(DBHelper.COL_PLACE, String.valueOf(str[3]));
            contentValues.put(DBHelper.COL_LT, String.valueOf(str[4]));
            contentValues.put(DBHelper.COL_LN, String.valueOf(str[5]));
            contentValues.put(DBHelper.COL_LS, String.valueOf(str[6]));
            contentValues.put(DBHelper.COL_SCORE, String.valueOf(str[7]));
            contentValues.put(DBHelper.COL_RT, String.valueOf(str[8]));
            contentValues.put(DBHelper.COL_RN, String.valueOf(str[9]));
            contentValues.put(DBHelper.COL_RS, String.valueOf(str[10]));
            contentValues.put(DBHelper.COL_LTL, String.valueOf(str[11]));
            contentValues.put(DBHelper.COL_RTL, String.valueOf(str[12]));
            dbHelper.insertData("Team", contentValues);
        }

        String[] fields = dbHelper.getTableFields("Team");
        Cursor cursor = dbHelper.getAllEntries("Team", fields);
        int[] item_ids = new int[] {R.id.florida, R.id.nd, R.id.date, R.id.info, R.id.textView7, R.id.score};
        SimpleCursorAdapter teamCursor;
        teamCursor = new SimpleCursorAdapter(this, R.layout.schedule_item, cursor, fields, item_ids, 0);

        Cursor cursor1 = dbHelper.getAllEntries("Team", dbHelper.getTableFields("Team"));

        if(cursor1 != null) {
            cursor1.moveToFirst();
            do {
                String[] str = new String[]{
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_LOGO)),
                    cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_NAME)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_DATE)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_PLACE)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_LT)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_LN)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_LS)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_SCORE)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_RT)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_RN)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_RS)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_LTL)),
                        cursor1.getString(cursor1.getColumnIndex(DBHelper.COL_RTL))};

                teams.add(new Team(cursor1.getInt(cursor1.getColumnIndex(DBHelper.COL_ID)), str));
            } while(cursor1.moveToNext());
        }
        ScheduleAdapter scheduleAdapter = (ScheduleAdapter) new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);

        scheduleListView.setAdapter(scheduleAdapter);
//        scheduleListView.setAdapter(teamCursor);

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("team", teams.get(position));
                startActivity(intent);
            }
        });

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



}


