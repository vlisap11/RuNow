package com.example.lin.runow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    String DB_NAME = "running_db.sqlite";
    RunningDAO runningdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();

        TextView textview = (TextView) findViewById(R.id.text_view_2);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        runningdao = database.getRunningdataDAO();
        List<Runningdata> runningdata_list = runningdao.getAllRunningdata();
        for (int i = 0; i < runningdata_list.size(); i++) {

            String starttime = runningdata_list.get(i).getStarttime();

            double dist = runningdata_list.get(i).getDistance();
            String distance = Double.toString(dist);

            double cal = runningdata_list.get(i).getCalorie();
            String calories = Double.toString(cal);

            textview.setText("Run " +i+ ": Distance: " + distance + " km, Calories: " + calories);
        }
    }

    public void onClickGoBack(View view) {
    }
}
