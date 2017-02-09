package com.cs60333.jwang28.lab2_jwang28;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleAdapter extends ArrayAdapter<String[]> {
    ScheduleAdapter (Context context, ArrayList<String[]> schedule) {
        super(context, R.layout.schedule_item, schedule);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        String[] matchItem = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem[0];
        teamName.setText(matchItem[1]);
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        teamLogo.setImageResource(resID );
        return scheduleView;
    }

    public String getPackageName() {
        return "com.cs60333.jwang28.lab2_jwang28";
    }
}


