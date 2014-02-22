package com.remindr.io;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evant_000 on 2/22/14.
 */
public class TextViewFragment extends ListFragment {

    public static TextViewFragment newInstance() {
        return new TextViewFragment();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        return inflater.inflate(R.layout.map_fragment, container, false);
    }
    }
}