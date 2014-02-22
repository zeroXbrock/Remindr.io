package com.remindr.io;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by evant_000 on 2/22/14.
 */
public class MapViewFragment extends MapFragment {

    public static MapViewFragment newInstance() {
        return new MapViewFragment();
    }
/*
    public void onActivityCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inflater.inflate(R.layout.map_fragment, container, false);
    }
*/
}