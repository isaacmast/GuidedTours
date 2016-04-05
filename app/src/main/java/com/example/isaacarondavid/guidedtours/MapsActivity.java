package com.example.isaacarondavid.guidedtours;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks,
        OnConnectionFailedListener {
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap map;
    private GoogleApiClient googleApiClient;

    //**************************************************************
    // Activity lifecycle methods
    //****************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // if GoogleMap object is not already available, get it
        if (map == null) {
            FragmentManager manager = getSupportFragmentManager();
            SupportMapFragment fragment =
                    (SupportMapFragment) manager.findFragmentById(R.id.map);
            map = fragment.getMap();
        }

        // if GoogleMap object is available, configure it
        if (map != null) {
            map.getUiSettings().setZoomControlsEnabled(true);
        }

        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();

        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if returning from connection failed resolution activity...
        if (requestCode == CONNECTION_FAILURE_RESOLUTION_REQUEST) {
            // do additional processing
        }
    }

    //**************************************************************
    // Private methods
    //****************************************************************
    private void updateMap(){
        if (googleApiClient.isConnected()) {
            setCurrentLocationMarker();
        }
    }

    private void setCurrentLocationMarker(){
        if (map != null) {
            if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
            }
            // get current location
            Location location = LocationServices.FusedLocationApi
                    .getLastLocation(googleApiClient);

            if (location != null) {
                // zoom in on current location
                map.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(location.getLatitude(),
                                                location.getLongitude()))
                                        .zoom(16.5f)
                                        .bearing(0)
                                        .tilt(25)
                                        .build()));

                // add a marker for the current location
                map.clear();      // clear old marker(s)
                map.addMarker(    // add new marker
                        new MarkerOptions()
                                .position(new LatLng(location.getLatitude(),
                                        location.getLongitude()))
                                .title("You are here"));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Notify("Title","This is a test");
                        /**Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                        startActivity(descIntent);*/
                        return false;
                    }
                });
            }
        }
    }

    public void Notify(String notificationTitle, String notificationMessage){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Intent intent = new Intent(this, NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(MapsActivity.this);
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
        builder.setContentTitle("GuidedTours Notification");
        builder.setContentText("This is a test.");
        builder.build();
        Notification myNotification = builder.getNotification();
        notificationManager.notify(999, myNotification);

    }


    //**************************************************************
    // Implement ConnectionCallbacks interface
    //****************************************************************
    @Override
    public void onConnected(Bundle dataBundle) {
        updateMap();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show();
    }

    //**************************************************************
    // Implement OnConnectionFailedListener
    //****************************************************************
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // if Google Play services can resolve the error, display activity
        if (connectionResult.hasResolution()) {
            try {
                // start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }
            catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
        else {
            new AlertDialog.Builder(this)
                    .setMessage("Connection failed. Error code: "
                            + connectionResult.getErrorCode())
                    .show();
        }
    }
  @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        Toast.makeText(this,"Map ready",Toast.LENGTH_SHORT).show();
        setCurrentLocationMarker();
    }

}
