package com.tiramisu.asthraappmk2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class EventInfoActivity extends BaseActivity {
    TextView descriptionTextView;
    ImageView eventImage;
    String eventId, eventName, eventDescription, eventBranch, eventTime;
    Boolean eventSpot, eventTeam;
    int eventDay, eventPosterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        super.onBaseCreate("");
        descriptionTextView = (TextView) findViewById(R.id.eventDescription);
        eventImage = (ImageView) findViewById(R.id.eventImage);
        Intent intent = getIntent();
        eventId = intent.getStringExtra("eventId");
        eventName = intent.getStringExtra("eventName");
        eventDescription = intent.getStringExtra("eventDescription");
        eventBranch = intent.getStringExtra("eventBranch");
        eventTime = intent.getStringExtra("eventTime");
        eventTeam = intent.getBooleanExtra("eventTeam", true);
        eventSpot = intent.getBooleanExtra("eventSpot", false);
        eventDay = intent.getIntExtra("eventDay", 1);
        eventPosterId = intent.getIntExtra("eventPosterId", R.drawable.csgo);

        toolbar.setTitle(eventName);
        descriptionTextView.setText(eventDescription);
        eventImage.setImageResource(eventPosterId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_info, menu);
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

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_EVENTS;
    }
}
