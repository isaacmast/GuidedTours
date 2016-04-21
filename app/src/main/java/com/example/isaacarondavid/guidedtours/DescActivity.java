package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity that shows information about a selected destination by the user
 * @author aronharder on 3/31/16.
 */
public class DescActivity extends Activity implements OnClickListener {
    //TODO: add a "Start from here" button that allows the user to start the tour in the middle
    //NOTE: take out these 2 lines if github won't sync

    // declare instance variables
    private TextView title;
    private TextView description;
    private Button backToMaps;
    private Intent mapsIntent;

    /**
     * {@inheritDoc}
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        backToMaps = (Button) findViewById(R.id.backToMaps);
        backToMaps.setOnClickListener(this);
        mapsIntent = new Intent(getApplicationContext(),
                MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mapsIntent.putExtra("Tour Name",getIntent().getStringExtra("Tour Name"));

        title.setText(getIntent().getStringExtra("Title"));
        description.setText(getIntent().getStringExtra("Description"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        startActivity(mapsIntent);
    }
}
