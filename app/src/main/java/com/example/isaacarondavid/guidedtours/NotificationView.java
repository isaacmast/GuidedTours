package com.example.isaacarondavid.guidedtours;

import android.app.Activity;
import android.os.Bundle;

/**
 * Alerts user with notifications based on their activity within the app
 * @author aronharder on 4/5/16.
 */
public class NotificationView extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }
}
