package com.appstoremarketresearch.colorpickerapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.appstoremarketresearch.colorpickerapp.R;
import com.appstoremarketresearch.colorpickerapp.view.MainFragment;
import com.appstoremarketresearch.simplecolorpicker.controller.ColorPickerActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * getSelectedColorIntValue
     */
    private int getSelectedColorIntValue() {

        int colorIntValue = 0;

        FragmentManager manager = this.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.main_fragment);

        if (fragment instanceof MainFragment) {
            colorIntValue = ((MainFragment)fragment).getSelectedColorIntValue();
        }
        else if (fragment == null) {
            Log.e(LOG_TAG, "Found no Fragment for id=R.id.main_fragment");
        }
        else {
            Log.e(LOG_TAG, "Found unknown Fragment of type " + fragment.getClass().getName());
        }

        return colorIntValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * showColorValues
     */
    public void showColorValues(View view) {

        int colorIntValue = getSelectedColorIntValue();
        String colorHex = "#" + Integer.toHexString(colorIntValue).toUpperCase();

        String message = "Color int = " + colorIntValue;
        message += "\nColor hex = " + colorHex;

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * showSimpleColorPicker
     */
    public void showSimpleColorPicker(View view) {
        Class nextActivity = ColorPickerActivity.class;
        Intent intent = new Intent(this, nextActivity);
        this.startActivity(intent);
    }
}
