package com.remindr.io;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by evant_000 on 2/22/14.
 */
public class ListViewFragment extends ListFragment {

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_column, R.id.text);
        setListAdapter(adapter);
        adapter.addAll(createDataList(100));
    }

    private static List<String> createDataList(int counts) {
        List<String> list = new ArrayList<String>();
        list.add("Miley Cyrus is twerking at the Moda Center!");
        list.add("Justin Beiber is in jail at the Special Handling Unit!");
        list.add("Barack Obama is chillin' at the White House");
        return list;
    }
}