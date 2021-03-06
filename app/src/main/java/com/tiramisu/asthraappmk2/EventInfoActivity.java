package com.tiramisu.asthraappmk2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class EventInfoActivity extends BaseActivity implements View.OnClickListener{
    TextView descriptionTextView, ruleTextView, contactTextView, prizeHeadTextView, prizeTextView, dayTextView;
    ImageView eventImage;
    Button registerButton;
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
        prizeTextView = (TextView) findViewById(R.id.prizeMoney);
        dayTextView = (TextView) findViewById(R.id.eventDay);
        prizeHeadTextView = (TextView) findViewById(R.id.prizeHead);
        registerButton = (Button) findViewById(R.id.registerButton);
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
        if(eventSpot){
            registerButton.setVisibility(View.GONE);
        }
        registerButton.setOnClickListener(this);
        descriptionTextView.setText(eventDescription);
        ruleTextView.setText(eventRule);
        switch (eventDay){
            case 1:
                dayTextView.setText("Day: 1");
                break;
            case 2:
                dayTextView.setText("Day: 2");
                break;
            case 12:
                dayTextView.setText("Day: 1 & 2");
                break;
            default:
                dayTextView.setVisibility(View.GONE);
        }
        prizeTextView.setText(eventPrize);
        if(eventPrize.equals("N/A")) {
            prizeHeadTextView.setVisibility(View.GONE);
            prizeTextView.setVisibility(View.GONE);
        }
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

        /*Hiding register button on the toolbar
        getMenuInflater().inflate(R.menu.menu_event_info, menu);


        if(!eventSpot) {
            item = menu.add(Menu.NONE, R.id.action_NewReg, Menu.NONE, R.string.reg);
            item.setIcon(R.drawable.ic_register_white_24dp);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        */


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

    @Override
    public void onClick(View v) {
        startReg();
    }
}
