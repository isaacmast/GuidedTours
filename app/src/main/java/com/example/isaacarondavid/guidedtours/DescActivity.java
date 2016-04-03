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
 * Created by aronharder on 3/31/16.
 */
public class DescActivity extends Activity implements OnClickListener {
    private TextView title;
    private TextView description;
    private Button backToMaps;
    private Intent mapsIntent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        backToMaps = (Button) findViewById(R.id.backToMaps);
        //Toast.makeText(this, backToMaps.getText(), Toast.LENGTH_SHORT).show();
        backToMaps.setOnClickListener(this);
        mapsIntent = new Intent(getApplicationContext(),
                MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

        @Override
    public void onClick(View v) {
        startActivity(mapsIntent);
    }
}
