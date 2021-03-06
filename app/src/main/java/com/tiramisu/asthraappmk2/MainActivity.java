package com.tiramisu.asthraappmk2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements ClickListener{
    List<EventDetails> geEvents;
    EventDetails geEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] geEventDescriptions = new String[40];
    String[] geEventRules = new String[40];
    String[] geEventContacts = new String[40];
    String[] geEventPrizes = new String[40];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onBaseCreate("Asthra 2015");


        geEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        geEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        geEventRules = getResources().getStringArray(R.array.event_rules);
        geEventContacts = getResources().getStringArray(R.array.event_contacts);
        geEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 40; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("GE")) {
                geEventDetails = new EventDetails();
                geEventDetails.setEventId(eventsActivity.eventIds[i]);
                geEventDetails.setEventName(eventsActivity.eventNames[i]);
                geEventDetails.setEventDescription(geEventDescriptions[i]);
                geEventDetails.setEventRule(geEventRules[i]);
                geEventDetails.setEventContact(geEventContacts[i]);
                geEventDetails.setEventPrize(geEventPrizes[i]);
                geEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                geEventDetails.setEventDay(eventsActivity.eventDays[i]);
                geEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                geEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                geEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                geEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                geEvents.add(geEventDetails);
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(this, geEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*Hiding action bar buttons*/
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
        return NAVDRAWER_ITEM_HOME;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(this, EventInfoActivity.class);

        intent.putExtra("eventId", geEvents.get(position).getEventId());
        intent.putExtra("eventName", geEvents.get(position).getEventName());
        intent.putExtra("eventDescription", geEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", geEvents.get(position).getEventRule());
        intent.putExtra("eventContact", geEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", geEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", geEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", geEvents.get(position).getEventDay());
        intent.putExtra("eventTime", geEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", geEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", geEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", geEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
