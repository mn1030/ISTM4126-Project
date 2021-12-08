package com.example.restaurantfinder;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private DBHandler dbHandler;

    //references for programmatically manipulated features
    private boolean use_gWorld;
    private Spinner cuisType;
    private int maxDist = 5;

    private TextView distTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references from manipulated filters

        Switch gWorld = (Switch) findViewById(R.id.gworld_switch); //GWORLD SWITCH

        if (gWorld.isChecked()){
            use_gWorld = false;
        }
        else{
            use_gWorld = true;
        }

        cuisType = (Spinner) findViewById(R.id.types); //SPINNER
        String cuisine = String.valueOf(cuisType.getSelectedItem()); //SAVE OUR CUISINETYPE!!

        SeekBar distSeekBar = (SeekBar) findViewById(R.id.distSeekBar); //SEEKBAR
        distSeekBar.setOnSeekBarChangeListener(seekBarListener);

        distTextView = (TextView) findViewById(R.id.distTextView); //SEEKBAR TEXTVIEW
        //distTextView.setText(maxDist+ " miles from Duques");
    }

    //listener object for the SeekBar's progress changed events
    private SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    maxDist = progress; //SAVE OUR MAXDIST!!
                    distTextView.setText(maxDist+ " miles from Duques");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    public void buttonOnClick(View view){
        //Read through data and filter based on maxDist, use_gWorld, cuisine
    }

}

