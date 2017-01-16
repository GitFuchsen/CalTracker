package de.fhdw.bfwi115a.caltracker.app;

/**
 * Created by BFWI115Ako on 06.01.2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentRecipes extends Fragment {

    public FragmentRecipes() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipes, container, false);

        return rootView;
    }
}