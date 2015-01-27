package com.tiramisu.asthraappmk2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/*You need a separate dependency of google play services for this !*/
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class LocationActivity extends BaseActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        super.onBaseCreate("Location");
        /*Just So you Know SJCET Location is:  9.726865, 76.726110 */

        //The Intital Boring Map Setup, btw. the map id name is map.!

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    //Map Does its magical Works.. err.. operations here, like the markers and stuff! oh oh. also the camera pan animation. ( i like it personallly!)
    @Override
    public final void onMapReady(GoogleMap map){
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions().position(new LatLng(9.726865, 76.726110 )).title("Asthra 2015 at SJCET").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(9.726865, 76.726110), 18),2000,null);

        /*
        * Da,
        * Here we will manually need to add markers for events. okay.
        * I had enuff of dynamic map s***t , so we will stick with the regualr static style.!
        * */
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_LOCATION;
    }
}
