package com.example.amber.materialui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Amber on 5/20/15.
 */
public class gridAdapter extends BaseAdapter{
    private Integer colorIndicators[] = {R.drawable.dot_placeholder,
            R.drawable.dot_lv1,R.drawable.dot_lv2,
            R.drawable.dot_lv3,R.drawable.dot_lv4,
            R.drawable.dot_lv5,R.drawable.dot_lv6,
            R.drawable.dot_lv7,R.drawable.dot_lv8,
            R.drawable.dot_lv9,R.drawable.dot_lv10};

    private Integer indicatorDisplay[] = {
            colorIndicators[1], colorIndicators[1],colorIndicators[2],colorIndicators[1],
            colorIndicators[3],colorIndicators[2],colorIndicators[5],colorIndicators[1],
            colorIndicators[4],colorIndicators[3],colorIndicators[3],colorIndicators[1],
            colorIndicators[5],colorIndicators[3],colorIndicators[1],colorIndicators[10],
            colorIndicators[5],colorIndicators[3],colorIndicators[1],colorIndicators[1],
            colorIndicators[4],colorIndicators[2],colorIndicators[5],colorIndicators[1],
            colorIndicators[0],colorIndicators[0],colorIndicators[0],colorIndicators[0],
            colorIndicators[0],colorIndicators[0],colorIndicators[0],colorIndicators[0]};

    private Context context;

    public gridAdapter(Activity activity){
        this.context = activity;
    }

    @Override
    public int getCount() {
        return indicatorDisplay.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if (convertView == null){
            img = new ImageView(context);
            img.setLayoutParams(new GridView.LayoutParams(100,100));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //img.setPadding(10,10,5,5);
        } else {
            img = (ImageView) convertView;
        }

        img.setImageResource(indicatorDisplay[position]);
        return img;
    }
}
