package com.example.isaacarondavid.guidedtours;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

//Some possible locations for testing (EMU Tour)
//EMU Quad - 38.472543, -78.877306
//Science Center - 38.470007, -78.878113
//Library - 38.470272, -78.878997
//Caf - 38.471730, -78.879643
//Hilltop - 38.471409, -78.882383

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks,
        OnConnectionFailedListener {
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap map;
    private GoogleApiClient googleApiClient;

    private TourDB db;

    private Tour EMU;

    private Destination Quad;
    private Destination Caf;
    private Destination Hill;
    private Destination SC;
    private Destination Library;

    //**************************************************************
    // Activity lifecycle methods
    //****************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        db = new TourDB(this.getApplicationContext());
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();


        //for testing purposes
        EMU = new Tour(1,"EMU","Significant places around EMU",(float)38.450999,(float)-78.878997);
        //db.insertTour(EMU); //need this method
        //db.insertDestination(new Destination(db.getTour("EMU").getId(),1,"Quad","This is where the main undergraduate dorms are.",(float)38.472543,(float)-78.877306));
        //db.insertDestination(new Destination(db.getTour("EMU").getId(),2,"Hilltop","There is a great view of the city here.",(float)38.471409,(float)-78.882383));
        //would call setDestinationMarkers(EMU) here
        Quad = new Destination(1,1,"Quad","This is where the main undergraduate dorms are.",(float)38.472000,(float)-78.877306);
        Hill = new Destination(1,2,"Hilltop","There is a great view of the city here.",(float)38.471409,(float)-78.882383);
        Caf = new Destination(1,3,"Caf","This is where all students eat located under Northlawn.",(float)38.471730,(float)-78.879643);
        SC = new Destination(1,3,"Caf","This is where all students eat located under Northlawn.",(float)38.470007,(float) -78.878113);
        Library = new Destination(1,3,"Caf","This is where all students eat located under Northlawn.",(float)38.470272, (float)-78.878997);
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
            //setCurrentLocationMarker();
            dummySetDestinationMarkers();
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
                map.addMarker(    // add new marker
                        new MarkerOptions()
                                .position(new LatLng(location.getLatitude(),
                                        location.getLongitude()))
                                .title("You are here")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                /*
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Notify("Title","This is a test");
                        Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                        startActivity(descIntent);
                        return false;
                    }
                });
                */
            }
        }
    }
    /*
    This method is here to just place pins without using the database
     */
    public void dummySetDestinationMarkers() {
        map.clear();
        map.addMarker(    // add new marker
                new MarkerOptions()
                        .position(new LatLng(Quad.getLatitude(),
                                Quad.getLongitude()))
        );
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Notify("Title", "This is a test");
                Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                startActivity(descIntent);
                return false;
            }
        });
        map.addMarker(    // add new marker
                new MarkerOptions()
                        .position(new LatLng(Hill.getLatitude(),
                                Hill.getLongitude()))
        );
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Notify("Title", "This is a test");
                Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                startActivity(descIntent);
                return false;
            }
        });
        map.addMarker(    // add new marker
                new MarkerOptions()
                        .position(new LatLng(Caf.getLatitude(),
                                Caf.getLongitude()))
        );
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Notify("Title", "This is a test");
                Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                startActivity(descIntent);
                return false;
            }
        });
        map.addMarker(    // add new marker
                new MarkerOptions()
                        .position(new LatLng(SC.getLatitude(),
                                SC.getLongitude()))
        );
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Notify("Title", "This is a test");
                Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                startActivity(descIntent);
                return false;
            }
        });
        map.addMarker(    // add new marker
                new MarkerOptions()
                        .position(new LatLng(Library.getLatitude(),
                                Library.getLongitude()))
        );
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Notify("Title", "This is a test");
                Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                startActivity(descIntent);
                return false;
            }
        });
        setCurrentLocationMarker();
    }
    /*
    Given a list of destinations this will set a marker for each one and move the camera to the primary destination
     */
    public void setDestinationMarkers(Tour tour) {
            map.clear();
            ArrayList<Destination> destinations = db.getDestinations(tour.getName());
            for (int i = 0; i < destinations.size(); i++){
                    map.addMarker(    // add new marker
                            new MarkerOptions()
                                .position(new LatLng(destinations.get(i).getLatitude(),
                                        destinations.get(i).getLongitude()))
                                );

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Notify("Title","This is a test");
                        Intent descIntent = new Intent(getApplicationContext(), DescActivity.class);
                        startActivity(descIntent);
                        return false;
                    }
                });

            }
            setCurrentLocationMarker();

            //move camera to primary destination
            map.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                        new CameraPosition.Builder()
                                .target(new LatLng(tour.getPrimaryLat(),
                                        tour.getPrimaryLong()))
                                .zoom(16.5f)
                                .bearing(0)
                                .tilt(25)
                                .build()));
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
        //setCurrentLocationMarker();
        dummySetDestinationMarkers();
    }

}
