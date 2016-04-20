package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TourSelectActivity extends ListActivity {
    private ListView list;
    private String[] tours;
    private TourDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tour_select);
        db = new TourDB(this.getApplicationContext());
        list = getListView();


        db.insertTour(new Tour(1, "EMU", "Significant places around EMU"));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 1, "Quad", "This is where the main undergraduate dorms are.", (float) 38.472000, (float) -78.877306));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 2, "Hilltop", "There is a great view of the city here.", (float) 38.471409, (float) -78.882383));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 3, "Caf", "This is where all students eat located under Northlawn.", (float) 38.471730, (float) -78.879643));
        db.insertDestination(new Destination(db.getTour("EMU").getId(),4,"SC","This building was newly renovated in 2015 and has all of our science labs.",(float)38.470007,(float) -78.878113));
        db.insertDestination(new Destination(db.getTour("EMU").getId(), 5, "Library", "Sadie Hartler Library: where students go to study.", (float) 38.470272, (float) -78.878997));
        tours = db.getTourNames();
        Toast.makeText(this,"here",Toast.LENGTH_LONG).show();
        //list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tours));
    }
    public void onListItemClick(ListView parent, View v, int position,
                                long id){
        Intent map = new Intent(getApplicationContext(),MapsActivity.class);
        String t = tours[position];
        map.putExtra("Tour Name",t);
        startActivity(map);
    }
}
