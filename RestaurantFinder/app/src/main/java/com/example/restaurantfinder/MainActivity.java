package com.example.restaurantfinder;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private DBHandler dbHandler;

    //references for programmatically manipulated features
    private static boolean use_gWorld;
    private static String cuisine;
    private static int maxDist = 5;

    private List<Restaurant> restaurantEntries= new ArrayList<>(); //this will list the search results restaurants

    private TextView distTextView;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references from manipulated filters

        Switch gWorld = (Switch) findViewById(R.id.gworld_switch); //GWORLD SWITCH

        if (gWorld.isChecked()){
            use_gWorld = false; //other payment
        }
        else{
            use_gWorld = true;
        }

        Spinner cuisType = (Spinner) findViewById(R.id.types); //SPINNER
        cuisine = String.valueOf(cuisType.getSelectedItem()); //SAVE OUR CUISINETYPE!!

        SeekBar distSeekBar = (SeekBar) findViewById(R.id.distSeekBar); //SEEKBAR
        distSeekBar.setOnSeekBarChangeListener(seekBarListener);

        distTextView = (TextView) findViewById(R.id.distTextView); //SEEKBAR TEXTVIEW
        //distTextView.setText(maxDist+ " miles from Duques");

        lv = (ListView) findViewById(R.id.restaurantList); //OUR LISTVIEW


        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.restaurantList, restaurantEntries);

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
        read_csv(); //execute READING METHOD
    }


    private void read_csv(){
        //Restaurant entry;
        InputStream is = getResources().openRawResource(R.raw.data);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break; //read line by line

                // split by commas
                String[] tokens = line.split(",");

                // read data
                Restaurant entry = new Restaurant();

                entry.setMileCategory(Integer.parseInt(tokens[0])); //MILE CAT: INT -> STRING
                entry.setCuisineType(tokens[1]);
                entry.setName(tokens[2]);
                entry.setDistance(Double.parseDouble(tokens[3])); //DIST: INT -> STRING
                entry.setOn_campus(tokens[4]);
                entry.setgWorld_data(tokens[5]);
                entry.setMenuLink(tokens[6]);
                entry.setHours(tokens[7]);

                //CHECK IF THEY MATCH THE FILTERS
                boolean addEntry = true;

                //check use_gWorld
                if ( (use_gWorld == true) && (entry.getgWorld_data().equals("yes"))){
                    addEntry = true;
                }
                else if ((use_gWorld == false) && (entry.getgWorld_data().equals("no"))){
                    addEntry = true;
                }

                //AFTER WE PASS GWORLD FILTER, CHECK EVERYTHING ELSE
                if (addEntry == true){
                    //check cuisine
                    if (cuisine.equals(entry.getCuisineType())){
                        addEntry = true;
                    }
                    else{
                        addEntry = false; //this breaks us out
                    }
                    //check maxDist
                    if (entry.getMileCategory() <= maxDist){
                        addEntry = true;
                    }
                    else{
                        addEntry = false;
                    }
                }

                if (addEntry == true){
                    restaurantEntries.add(entry);
                }

                Log.d("MyActivity", "Just created: " + entry);
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error on line" + line, e);
                e.printStackTrace();
            }

        }
    }


    public class Restaurant{

        //values we can get from data
        private int mileCategory;
        private String cuisineType;
        private String name;
        private double distance;
        private String on_campus;
        private String gWorld_data;
        private String menuLink;
        private String hours;

        //getters and setters for values
        public int getMileCategory() {
            return mileCategory;
        }

        public void setMileCategory(int mileCategory) {
            this.mileCategory = mileCategory;
        }

        public String getCuisineType() {
            return cuisineType;
        }

        public void setCuisineType(String cuisineType) {
            this.cuisineType = cuisineType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getOn_campus() {
            return on_campus;
        }

        public void setOn_campus(String on_campus) {
            this.on_campus = on_campus;
        }

        public String getgWorld_data() {
            return gWorld_data;
        }

        public void setgWorld_data(String gWorld_data) {
            this.gWorld_data = gWorld_data;
        }

        public String getMenuLink() {
            return menuLink;
        }

        public void setMenuLink(String menuLink) {
            this.menuLink = menuLink;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }
    }
}

