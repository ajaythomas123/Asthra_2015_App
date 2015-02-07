package com.tiramisu.asthraappmk2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class EventsActivity extends BaseActivity implements MaterialTabListener {
    ViewPager viewPager;
    MaterialTabHost materialTabHost;
    EventDetails eventDetails;
    /*The following 6 arrays store the details of the events.
    * Each time you add a new event, make sure you add the required values in the arrays.
    * The String array called eventIds is the id used to identify the event in the database.*/
    String[] eventIds = {"AEI1", "AEI2", "CE1", "CE2", "CSE1", "CSE2", "ECE1", "ECE2", "EEE1", "EEE2", "ME1", "ME2"};
    String[] eventNames = {"AEI Event 1", "AIE Event 2", "CE Event 1", "CE Event 2", "CSE Event 1", "CSE Event 2", "ECE Event 1", "ECE Event 2", "EEE Event 1", "EEE Event 2", "ME Event 1", "ME Event 2"};
    String[] eventDescriptions = new String[12];
    String[] eventBranches = {"AEI", "AEI", "CE", "CE", "CSE", "CSE", "ECE", "ECE", "EEE", "EEE", "ME", "ME"};
    int[] eventDays = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
    String[] eventTimes = {"10:00AM", "2:00PM", "10:00AM", "2:00PM", "10:00AM", "2:00PM", "10:00AM", "2:00PM", "10:00AM", "2:00PM", "10:00AM", "2:00PM"};
    Boolean[] eventSpot = {false, true, false, true, false, true, false, true, false, true, false, true};
    Boolean[] eventTeam = {true, true, false, true, false, true, false, true, false, true, false, true};
    int[] eventPosterIds = {R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event, R.drawable.dummy_event};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onBaseCreate("Events");
        eventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        for (int i = 0; i < 12; i++) {
            eventDetails = new EventDetails();
            eventDetails.setEventId(eventIds[i]);
            eventDetails.setEventName(eventNames[i]);
            eventDetails.setEventDescription(eventDescriptions[i]);
            eventDetails.setEventBranch(eventBranches[i]);
            eventDetails.setEventDay(eventDays[i]);
            eventDetails.setEventTime(eventTimes[i]);
            eventDetails.setEventTeam(eventTeam[i]);
            eventDetails.setEventSpot(eventSpot[i]);
            eventDetails.setEventPosterId(eventPosterIds[i]);
            allEvents.add(eventDetails);
        }
        viewPager = (ViewPager) findViewById(R.id.eventsPager);
        materialTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        EventPagerAdapter eventPagerAdapter = new EventPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(eventPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                materialTabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < eventPagerAdapter.getCount(); i++) {
            materialTabHost.addTab(materialTabHost.newTab().setText(eventPagerAdapter.getPageTitle(i)).setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        return NAVDRAWER_ITEM_EVENTS;
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}

class EventPagerAdapter extends FragmentStatePagerAdapter {
    public EventPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Applied Electronics & Instrumentation";
        }
        if (position == 1) {
            return "Civil Engineering";
        }
        if (position == 2) {
            return "Computer Science";
        }
        if (position == 3) {
            return "Electronics and Communication";
        }
        if (position == 4) {
            return "Electronics and Electrical";
        }
        if (position == 5) {
            return "Mechanical Engineering";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new EventsFragment_AEI();
        }
        if (position == 1) {
            fragment = new EventsFragment_CE();
        }
        if (position == 2) {
            fragment = new EventsFragment_CSE();
        }
        if (position == 3) {
            fragment = new EventsFragment_ECE();
        }
        if (position == 4) {
            fragment = new EventsFragment_EEE();
        }
        if (position == 5) {
            fragment = new EventsFragment_ME();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
