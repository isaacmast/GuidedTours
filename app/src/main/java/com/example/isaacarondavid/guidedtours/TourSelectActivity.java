package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class TourSelectActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String[] tours;
    private TourDB db;
    private Button dummyButton;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_select);

        db = new TourDB(this.getApplicationContext());

        dummyButton = (Button) findViewById(R.id.dummyButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        dummyButton.setOnClickListener(this);

        db.insertTour(new Tour(1, "EMU", "Significant places around EMU"));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 1, "Quad", "This is where the main undergraduate dorms are.", (float) 38.472000, (float) -78.877306));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 2, "Hilltop", "There is a great view of the city here.", (float) 38.471409, (float) -78.882383));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 3, "Caf", "This is where all students eat located under Northlawn.", (float) 38.471730, (float) -78.879643));
        db.insertDestination(new Destination(db.getTour("EMU").getId(),4,"SC","This building was newly renovated in 2015 and has all of our science labs.",(float)38.470007,(float) -78.878113));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 5, "Library", "Sadie Hartler Library: where students go to study.", (float) 38.470272, (float) -78.878997));

        db.insertTour(new Tour(2, "Mennonite Colleges", "These are the five mennonite colleges in the US"));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 6, "EMU", "Eastern Menonite University, Harrisonburg, VA", (float) 38.472000, (float) -78.877306));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 7, "Bluffton", "Bluffton University, Bluffton, OH", (float) 40.896393, (float) -83.896934));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 8, "Goshen", "Goshen College, Goshen, IN", (float) 41.564414, (float) -85.827363));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 9, "Bethel", "Bethel College, Bethel, KS", (float) 38.074617, (float) -97.342347));
        db.insertDestination(new Destination(db.getTour("Mennonite Colleges").getId(), 10, "Hesston", "Hesston College, Hesston, KS", (float) 38.13324, (float) -97.432913));


        tours = db.getTourNames();

        Toast.makeText(this,tours[0],Toast.LENGTH_LONG).show();
        ArrayAdapter<String> t = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tours);
        t.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dummyButton.setText("Mennonite Colleges");
        //spinner.setAdapter(t); //the line that is causing the problem
    }
    public void onListItemClick(ListView parent, View v, int position,
                                long id){
        Intent map = new Intent(getApplicationContext(),MapsActivity.class);
        String t = tours[position];
        map.putExtra("Tour Name",t);
        startActivity(map);
    }

    @Override
    public void onClick(View v) {
        Intent map = new Intent(getApplicationContext(),MapsActivity.class);
        map.putExtra("Tour Name","Mennonite Colleges");
        startActivity(map);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Intent map = new Intent(getApplicationContext(),MapsActivity.class);
        String t = tours[position];
        map.putExtra("Tour Name",t);
        startActivity(map);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
