package com.tiramisu.asthraappmk2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class RegistrationListActivity extends BaseActivity implements ClickListener{
    List<EventDetails> allEvents;
    EventDetails eventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    RegistrationAdapter registrationAdapter;
    String[] eventDescriptions = new String[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);
        super.onBaseCreate("Registration");
        allEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        eventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        for (int i = 0; i < 12; i++) {
            if(eventsActivity.eventSpot[i] == false) {
                eventDetails = new EventDetails();
                eventDetails.setEventId(eventsActivity.eventIds[i]);
                eventDetails.setEventName(eventsActivity.eventNames[i]);
                eventDetails.setEventDescription(eventDescriptions[i]);
                eventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                eventDetails.setEventDay(eventsActivity.eventDays[i]);
                eventDetails.setEventTime(eventsActivity.eventTimes[i]);
                eventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                eventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                eventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                allEvents.add(eventDetails);
            }

        }
        recyclerView = (RecyclerView) findViewById(R.id.registrationRecycler);
        registrationAdapter = new RegistrationAdapter(this, allEvents);
        registrationAdapter.setClickListener(this);
        recyclerView.setAdapter(registrationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_list, menu);
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
        return NAVDRAWER_ITEM_REG;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(this, RegistrationActivity.class);

        intent.putExtra("eventId", allEvents.get(position).getEventId());
        intent.putExtra("eventName", allEvents.get(position).getEventName());
        intent.putExtra("eventDescription", allEvents.get(position).getEventDescription());
        intent.putExtra("eventBranch", allEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", allEvents.get(position).getEventDay());
        intent.putExtra("eventTime", allEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", allEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", allEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", allEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
