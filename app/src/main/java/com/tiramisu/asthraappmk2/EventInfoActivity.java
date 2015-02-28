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
    TextView descriptionTextView, ruleTextView, contactTextView;
    ImageView eventImage;
    String eventId, eventName, eventDescription, eventRule, eventContact, eventPrize, eventBranch, eventTime;
    Boolean eventSpot, eventTeam;
    int eventDay, eventPosterId;
    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        super.onBaseCreate("");
        descriptionTextView = (TextView) findViewById(R.id.eventDescription);
        ruleTextView = (TextView) findViewById(R.id.eventRules);
        contactTextView = (TextView) findViewById(R.id.contactInfo);
        eventImage = (ImageView) findViewById(R.id.eventImage);
        Intent intent = getIntent();
        eventId = intent.getStringExtra("eventId");
        eventName = intent.getStringExtra("eventName");
        eventDescription = intent.getStringExtra("eventDescription");
        eventRule = intent.getStringExtra("eventRule");
        eventPrize = intent.getStringExtra("eventPrize");
        eventContact = intent.getStringExtra("eventContact");
        eventBranch = intent.getStringExtra("eventBranch");
        eventTime = intent.getStringExtra("eventTime");
        eventTeam = intent.getBooleanExtra("eventTeam", true);
        eventSpot = intent.getBooleanExtra("eventSpot", false);
        eventDay = intent.getIntExtra("eventDay", 1);
        eventPosterId = intent.getIntExtra("eventPosterId", R.drawable.csgo);

        toolbar.setTitle(eventName);
        descriptionTextView.setText(eventDescription);
        ruleTextView.setText(eventRule);
        contactTextView.setText(eventContact);
        eventImage.setImageResource(eventPosterId);

        //A small stunt here. look into this later TODO
/*        if(eventSpot) {
            item.setVisible(false);
        }
*/

    }

    public void startReg(){


            Intent intent = new Intent(this, RegistrationActivity.class);
            intent.putExtra("eventId", eventId);
            intent.putExtra("eventName", eventName);
            intent.putExtra("eventDescription", eventDescription);
            intent.putExtra("eventBranch", eventBranch);
            intent.putExtra("eventDay", eventDay);
            intent.putExtra("eventTime", eventTime);
            intent.putExtra("eventTeam", eventTeam);
            intent.putExtra("eventSpot", eventSpot);
            intent.putExtra("eventPosterId", eventPosterId);
            startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_info, menu);


        if(!eventSpot) {
            item = menu.add(Menu.NONE, R.id.action_NewReg, Menu.NONE, R.string.reg);
            item.setIcon(R.drawable.ic_register_white_24dp);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }


            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        if (id == R.id.action_NewReg) {


                startReg();


            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_EVENTS;
    }
}
