package com.casenex.stopwatch;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

import com.casenex.stopwatch.adapter.LapTimesAdapter;
import com.casenex.stopwatch.models.LapTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_list)
    ListView mainList;

    Chronometer lapTime;
    Chronometer curTime;
    Button startBtn;
    Button stopBtn;
    Button lapBtn;
    Button resetBtn;
    View headerView;
    Context mContext;
    LapTimesAdapter lapTimesAdapter;
    ArrayList<LapTime> lapTimes = new ArrayList<>();
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mContext = this;

        headerView = View.inflate(mContext, R.layout.header_watch, null);
        lapTime = (Chronometer) headerView.findViewById(R.id.lap_time);
        curTime = (Chronometer) headerView.findViewById(R.id.cur_time);
        startBtn = (Button) headerView.findViewById(R.id.start_btn);
        stopBtn = (Button) headerView.findViewById(R.id.stop_btn);
        lapBtn = (Button) headerView.findViewById(R.id.lap_btn);
        resetBtn = (Button) headerView.findViewById(R.id.reset_btn);

        simpleDateFormat = new SimpleDateFormat("hh:mm");

        mainList.addHeaderView(headerView);
        lapTimesAdapter = new LapTimesAdapter(mContext, lapTimes, simpleDateFormat);
        mainList.setAdapter(lapTimesAdapter);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curTime.setBase(SystemClock.elapsedRealtime());
                lapTime.setBase(SystemClock.elapsedRealtime());
                lapTime.start();
                curTime.start();
                startBtn.setEnabled(false);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curTime.stop();
                lapTime.stop();
                startBtn.setEnabled(true);
            }
        });

        lapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lapTime.stop();
                curTime.stop();
                LapTime newLap = new LapTime();
                long elapsedMillis = SystemClock.elapsedRealtime() - lapTime.getBase();
                newLap.setLapTime(elapsedMillis);
                newLap.setLapDisplay(lapTime.getText().toString());
                lapTimes.add(newLap);
                lapTimesAdapter.updateData(lapTimes);
                lapTimesAdapter.notifyDataSetChanged();
                lapTime.setBase(SystemClock.elapsedRealtime());
                lapTime.start();
                curTime.start();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curTime.stop();
                lapTime.stop();
                curTime.setBase(SystemClock.elapsedRealtime());
                lapTime.setBase(SystemClock.elapsedRealtime());
                lapTimes = new ArrayList<LapTime>();
                lapTimesAdapter.updateData(lapTimes);
                lapTimesAdapter.notifyDataSetChanged();
                startBtn.setEnabled(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
