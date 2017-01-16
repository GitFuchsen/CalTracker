package de.fhdw.bfwi115a.caltracker.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BFWI115Ako on 11.01.2017.
 */

public class FragmentFoods extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String [] itemsDashboardArray = {
                "1 Bar Twix 250 kcal",
                "2 Can Coke 450 kcal",
                "1 Meal Chicken 1250 kcal",
                "2 Slice Bread 180 kcal",
                "4 Piece Gummibears 150 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
                "1 Bar Twix 250 kcal",
        };

        List<String> itemList = new ArrayList<>(Arrays.asList(itemsDashboardArray));

        ArrayAdapter<String> itemListAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item,
                        R.id.list_item_textview,
                        itemList);

        View rootView = inflater.inflate(R.layout.content_foods, container, false);

        ListView itemListListView = (ListView) rootView.findViewById(R.id.listview_dashboard);
        itemListListView.setAdapter(itemListAdapter);

        return rootView;
    }
}
