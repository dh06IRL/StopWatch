package com.casenex.stopwatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.casenex.stopwatch.R;
import com.casenex.stopwatch.models.LapTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by davidhodge on 6/22/15.
 */
public class LapTimesAdapter extends BaseAdapter {

    private ArrayList<LapTime> items;
    private LayoutInflater inflater;
    Context mContext;
    SimpleDateFormat simpleDateFormat;

    public LapTimesAdapter(Context context, ArrayList<LapTime> items, SimpleDateFormat simpleDateFormat) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.mContext = context;
        this.simpleDateFormat = simpleDateFormat;
    }

    public void updateData(ArrayList<LapTime> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_laptime, parent, false);
            holder.lapCount = (TextView) convertView.findViewById(R.id.item_lap_count);
            holder.lapTime = (TextView) convertView.findViewById(R.id.item_lap_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lapCount.setText("" + (position + 1) + ". ");
        holder.lapTime.setText(items.get(position).getLapDisplay());

        return convertView;
    }

    class ViewHolder {
        TextView lapCount;
        TextView lapTime;
    }
}
