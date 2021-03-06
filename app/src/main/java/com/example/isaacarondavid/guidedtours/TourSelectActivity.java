package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Activity that allows the user to select a predefined tour from the DB
 * @author David Nester
 */
public class TourSelectActivity extends Activity implements AdapterView.OnItemClickListener {

    // declare instance variables
    private String[] tours;
    private TourDB db;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_select);

        // create new TourDB object
        db = new TourDB(this.getApplicationContext());

        // create EMU tour
        db.insertTour(new Tour(1, "EMU", "Significant places around EMU"));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 1, "Quad", "The quad is a the lawn between three of the undergraduate dorms, Cedarwood, Elmwood, and Maplewood. All three are LEED Gold Certified and have capacity for 294 total students.", (float) 38.472000, (float) -78.877306));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 2, "Hilltop", "The hilltop has a fire pit with an amazing view of the campus, city, and the surrounding Blue Ridge Mountains.", (float) 38.471409, (float) -78.882383));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 3, "Caf", "The cafeteria is located under the Northlawn dormitory and serves all of the campus food.", (float) 38.471730, (float) -78.879643));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 4, "Science Center", "This building was newly renovated in 2015 and has all of our science labs along with our lecture hall and the D. Ralph Hostetter Museum of Natural History.", (float) 38.470007, (float) -78.878113));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 5, "Library", "Sadie Hartler Library. The library has quiet spaces with computers where students can come to work on papers and finish homeowrk. The library is also home to The Technology Help Desk and the Menno Simmons Historical Library.", (float) 38.470272, (float) -78.878997));

        // create Mennonite Colleges tour
        db.insertTour(new Tour(2, "Mennonite Colleges", "These are the five mennonite colleges in the US"));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 6, "EMU", "Eastern Menonite University, Harrisonburg, VA, Founded 1917", (float) 38.472000, (float) -78.877306));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 7, "Bluffton", "Bluffton University, Bluffton, OH, Founded 1899", (float) 40.896393, (float) -83.896934));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 8, "Goshen", "Goshen College, Goshen,IN, Founded 1894", (float) 41.564414, (float) -85.827363));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 9, "Bethel", "Bethel College, Bethel, KS, Founded 1887", (float) 38.074617, (float) -97.342347));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 10, "Hesston", "Hesston College, Hesston, KS, Founded 1909", (float) 38.13324, (float) -97.432913));

        // create Harrisonburg Area Hiking tour
        db.insertTour(new Tour(3, "Harrisonburg Area Hiking", "Hiking locations around Harrisonburg"));
        db.insertDestination(new Destination(db.getTour("Harrisonburg Area Hiking").getId(), 11, "Hone Quarry", "Hone Quarry Campground with Lover's Leap and Hidden Rocks", (float) 38.457906, (float) -79.133355));
        db.insertDestination(new Destination(db.getTour("Harrisonburg Area Hiking").getId(), 12, "Fridley Gap", "Fridley Gap has beautiful hikes that overlook Harrisonburg", (float) 38.496775, (float) -78.709348));
        db.insertDestination(new Destination(db.getTour("Harrisonburg Area Hiking").getId(), 13, "Switzer Lake", "Hiking Area right before the West Virginia border on US33", (float) 38.571722, (float) -79.145149));
        db.insertDestination(new Destination(db.getTour("Harrisonburg Area Hiking").getId(), 14, "Reddish Knob", "Highest Point in Virginia with an amazing view", (float) 38.462244, (float) -79.241747));
        db.insertDestination(new Destination(db.getTour("Harrisonburg Area Hiking").getId(), 15, "Shenandoah National Park", "Skyline Drive is dotted with countless beuatiful overlooks and the Appalachian Trail", (float) 38.358598, (float) -78.546885));

        // create Washington DC National Mall tour
        db.insertTour(new Tour(4, "Washington DC National Mall", "Famous buildings on the National Mall"));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 16, "Washington Monument", "A large Obelisk honoring the First president of the USA", (float) 38.889461, (float) -77.035281));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 17, "The White House", "Home of the American president", (float) 38.898586, (float) -77.036588));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 18, "Lincoln Memorial", "A tribute to Abraham Lincoln, 16th president of the United States", (float) 38.889284, (float) -77.049842));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 19, "WWII Memorial", "A memorial for those who fought in World War II", (float) 38.889442, (float) -77.040097));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 20, "US Capitol Building", "Home of Congress", (float) 38.889837, (float) -77.008628));
        db.insertDestination(new Destination(db.getTour("Washington DC National Mall").getId(), 21, "Library of Congress", "The World's Largest Library", (float) 38.888681, (float) -77.005511));

        tours = db.getTourNames();

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tours));
        listView.setOnItemClickListener(this);
    }

    /**
     * Called when a tour is selected from the AdapterView
     * @param parent - the AdapterView where the user click occurred
     * @param view - the view that was clicked by the user
     * @param position - the position of the view that was clicked
     * @param id - the row id of the clicked item
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent map = new Intent(getApplicationContext(), MapsActivity.class);
        String t = tours[position];
        map.putExtra("Tour Name", t);
        startActivity(map);
    }
}
