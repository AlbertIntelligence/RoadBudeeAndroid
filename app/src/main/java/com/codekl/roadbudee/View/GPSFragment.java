package com.codekl.roadbudee.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekl.roadbudee.R;
import com.codekl.roadbudee.Custom.CustomFragment;

/**
 * Created by monsieurpetion on 2017-11-26.
 */

public class GPSFragment extends CustomFragment {

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.gps, null);


        return view;
    }

    private void setupView()
    {

        //etTouchNClick(R.id.imageView4);


    }
}
