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
    public void onActivityResult (
        int requestCode,
        int resultCode,
        Intent data) {

        int hashCode = ColorPickerActivity.class.getName().hashCode();

        if (resultCode == RESULT_OK &&
            requestCode == Math.abs(hashCode)) {

            Bundle extras = data.getExtras();

            if (extras != null) {

                int colorValue = extras.getInt("colorValue");

                if (colorValue != 0) {

                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.main_fragment);

                    if (fragment instanceof MainFragment) {
                        int buttonId = R.id.select_color_button_by_reqres;
                        ((MainFragment)fragment).onColorSelected(colorValue, buttonId);
                    }
                    else if (fragment == null) {
                        Log.e(LOG_TAG, "Found no Fragment with id=R.id.main_fragment");
                    }
                    else {
                        Log.e(LOG_TAG, "Found wrong Fragment type: " + fragment.getClass().getSimpleName());
                    }
                }
                else {
                    Log.e(LOG_TAG, "Extras have no color value");
                }
            }
            else {
                Log.e(LOG_TAG, "Intent has no Extras");
            }
        }
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
        boolean startActivityForResult = true;

        if (view != null && view.getTag() != null) {
            String responseMethod = view.getTag().toString();
            intent.putExtra("responseMethod", responseMethod);

            if (responseMethod.equals("sendBroadcast")) {
                this.startActivity(intent);
                startActivityForResult = false;
            }
        }

        if (startActivityForResult) {
            int hashCode = ColorPickerActivity.class.getName().hashCode();
            int requestCode = Math.abs(hashCode);
            this.startActivityForResult(intent, requestCode, null);
        }
    }
}
