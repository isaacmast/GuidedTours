package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class TourSelectActivity extends ListActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_select);

        list = (ListView) findViewById(R.id.list);

    }
    public void onListItemClick(ListView parent, View v, int position,
                                long id){


    }
}
