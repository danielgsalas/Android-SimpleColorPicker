package com.appstoremarketresesearch.simplecolorpicker.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appstoremarketresesearch.simplecolorpicker.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ColorPickerFragment extends Fragment {

    public ColorPickerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_picker, container, false);
    }
}
