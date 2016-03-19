package com.appstoremarketresearch.colorpickerapp.view;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appstoremarketresearch.colorpickerapp.R;
import com.appstoremarketresearch.simplecolorpicker.event.ColorPickerEventListener;
import com.appstoremarketresearch.simplecolorpicker.event.ColorPickerEventNotifier;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment
    extends Fragment
    implements ColorPickerEventListener {

    private Button mSelectColorButton;

    private static String LOG_TAG = MainFragment.class.getSimpleName();

    /*
     * Find the id of the drawable button for the color value.
     */
    private int getButtonDrawableId(
        int colorValue,
        Resources res) {

        int drawableId = 0;

        /*
         * res.getColor(int) is deprecated but res.getColor(int, Theme) requires API 23+.
         */
        if (colorValue == res.getColor(R.color.blue)) {
            drawableId = R.drawable.color_button_blue_selector;
        }
        else if (colorValue == res.getColor(R.color.green)) {
            drawableId = R.drawable.color_button_green_selector;
        }
        else if (colorValue == res.getColor(R.color.orange)) {
            drawableId = R.drawable.color_button_orange_selector;
        }
        else if (colorValue == res.getColor(R.color.purple)) {
            drawableId = R.drawable.color_button_purple_selector;
        }
        else if (colorValue == res.getColor(R.color.red)) {
            drawableId = R.drawable.color_button_red_selector;
        }
        else if (colorValue == res.getColor(R.color.yellow)) {
            drawableId = R.drawable.color_button_yellow_selector;
        }

        return drawableId;
    }

    /**
     * getButtonTextColorValue
     */
    private int getButtonTextColorValue(int drawableId) {

        int buttonTextColorValue = Color.BLACK;

        switch (drawableId) {
            case R.drawable.color_button_blue_selector:
            case R.drawable.color_button_green_selector:
            case R.drawable.color_button_purple_selector:
            case R.drawable.color_button_red_selector:
                buttonTextColorValue = Color.WHITE;
                break;
        }

        return buttonTextColorValue;
    }

    @Override
    public void onColorSelected(int colorValue) {

        if (mSelectColorButton != null) {

            Resources res = getActivity().getResources();
            int drawableId = getButtonDrawableId(colorValue, res);
            int buttonTextColorValue = getButtonTextColorValue(drawableId);

            if (drawableId > 0) {
                // update the button's background
                mSelectColorButton.setBackgroundResource(drawableId);
                mSelectColorButton.setTextColor(buttonTextColorValue);
            }
            else {
                String message = "No drawable XML found for color #";
                message += res.getColor(colorValue);
                Log.e(LOG_TAG, message);
            }
        }
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        int buttonId = R.id.select_color_button;
        mSelectColorButton = (Button) view.findViewById(buttonId);

        // subscribe for event notification
        ColorPickerEventNotifier.subscribe(this);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        // unsubscribe from event notification
        ColorPickerEventNotifier.unsubscribe(this);
    }
}
