package com.example.restaurantfinder;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Switch gWorld;
    private Spinner cuisType;
    private int maxDist = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references from manipulated filters
    }

}

