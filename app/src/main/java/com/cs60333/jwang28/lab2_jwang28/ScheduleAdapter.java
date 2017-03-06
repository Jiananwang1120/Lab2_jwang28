package com.cs60333.jwang28.lab2_jwang28;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Team> {

    ScheduleAdapter (Context context, ArrayList<Team> teams) {
        super(context, R.layout.schedule_item, (ArrayList<Team>) teams);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        Team team = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = team.getTeamLogo();

        teamName.setText(team.getTeamName());
        int resID = getContext().getResources().getIdentifier(String.valueOf(mDrawableName), "drawable", getPackageName());
        teamLogo.setImageResource(resID );
        return scheduleView;
    }

    public String getPackageName() {
        return "com.cs60333.jwang28.lab2_jwang28";
    }
}


