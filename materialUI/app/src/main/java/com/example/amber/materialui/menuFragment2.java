package com.example.amber.materialui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Amber on 5/19/15.
 */
public class menuFragment2 extends Fragment {
    View rootview;
    GridView statGrid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.frag_layout2,container,false);
        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated called");
        statGrid = (GridView) getActivity().findViewById(R.id.stat_grid);
        statGrid.setAdapter(new gridAdapter(getActivity()));

        //for debugging
        statGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"Around 1 PM near coordinate (0, 0)", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }



//    public void setColorIndicator(){
//        View humidityGradient = getView().findViewById(R.id.humidityGradient);
//        GradientDrawable gradient = (GradientDrawable)humidityGradient.getBackground();
//        int startColor = Color.BLACK;
//        int endColor = Color.WHITE;
//        gradient.setColors(new int[]{ startColor, endColor });
//    }

}
