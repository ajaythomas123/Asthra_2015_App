package com.tiramisu.asthraappmk2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class EventsActivity extends BaseActivity implements MaterialTabListener {
    ViewPager viewPager;
    MaterialTabHost materialTabHost;
    EventDetails eventDetails;
    /*The following 6 arrays store the details of the events.
    * Each time you add a new event, make sure you add the required values in the arrays.
    * The String array called eventIds contains the IDs used to identify the events in the database.*/
    String[] eventIds = {"GE1", "GE2", "GE3", "GE4", "AEI1", "AEI2", "AEI3", "AEI4", "CE1", "CE2", "CE3", "CE4", "CSE1", "CSE2", "CSE3", "CSE4", "ECE1", "ECE2", "ECE3", "ECE4", "ECE5", "EEE1", "EEE2", "EEE3", "EEE4", "ME1", "ME2", "ME3", "ME4", "ME5", "WS1", "WS2", "OL1", "OL2", "OL3", "OL4", "INF1", "INF2", "INF3", "INF4"};
    String[] eventNames = {"Mr & Ms Asthra", "Tesoro", "Ideen", "Network Gaming", "Kalinga", "Technocrat", "Pyrokinesis", "Inquizitive", "Castello", "Esittely", "Secret Rush", "Decollare", "Cybertron", "TecHtuZ", "BASH-Err", "Alt-Ctrl-Delicious", "Robo Race", "El-Classico", "Circuit Debugging", "VIBGYOR", "Vault2", "Recreate", "Bodha", "In Time", "E Whizz", "Mex Hunt", "Papera", "Junkyard Wars", "Driver's Bay", "Aqua Missile", "Photography", "Python Programming", "Snapist", "Group Selfiee", "RoadRash", "Asthra in Frames", "4 X 4 Football", "Shooting", "Laser Tag", "Minute to Win"};
    String[] eventDescriptions = new String[40];
    String[] eventRules = new String[40];
    String[] eventContacts = new String[40];
    String[] eventPrizes = new String[40];
    String[] eventBranches = {"GE", "GE", "GE", "GE", "AEI", "AEI", "AEI", "AEI", "CE", "CE", "CE", "CE", "CSE", "CSE", "CSE", "CSE", "ECE", "ECE", "ECE", "ECE", "ECE", "EEE", "EEE", "EEE", "EEE", "ME", "ME", "ME", "ME", "ME", "WS", "WS", "OL", "OL", "OL", "OL", "INF", "INF", "INF", "INF"};
    int[] eventDays = {1, 2, 1, 12, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 12, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    String[] eventTimes = {"N/A", "N/A", "N/A", "N/A", "Full Day", "Forenoon", "Full Day", "Forenoon", "N/A", "N/A", "N/A", "N/A", "Full Day", "Full Day", "Full Day", "Full Day", "Forenoon", "N/A", "Forenoon", "Full Day", "Forenoon", "Full Day", "Full Day", "Full Day", "Full Day", "Full Day", "Full Day", "Full Day", "N/A", "Full Day", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "", "", "", "", "", "", "", "", "", ""};
    Boolean[] eventSpot = {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true};
    Boolean[] eventTeam = {false, true, false, false, true, false, true, true, true, true, true, true, true, true, false, false, true, true, true, true, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false};
    int[] eventPosterIds = {R.drawable.eposter0, R.drawable.eposter1, R.drawable.eposter2, R.drawable.eposter3, R.drawable.eposter4, R.drawable.eposter5, R.drawable.eposter6, R.drawable.eposter7, R.drawable.eposter8, R.drawable.eposter9, R.drawable.eposter10, R.drawable.eposter11, R.drawable.eposter12, R.drawable.eposter13, R.drawable.eposter14, R.drawable.eposter15, R.drawable.eposter16, R.drawable.eposter17, R.drawable.eposter18, R.drawable.eposter19, R.drawable.eposter20, R.drawable.eposter21, R.drawable.eposter22, R.drawable.eposter23, R.drawable.eposter24, R.drawable.eposter25, R.drawable.eposter26, R.drawable.eposter27, R.drawable.eposter28, R.drawable.eposter29, R.drawable.eposter30, R.drawable.eposter31, R.drawable.eposter32, R.drawable.eposter33, R.drawable.eposter34, R.drawable.eposter35, R.drawable.eposter36, R.drawable.eposter37, R.drawable.eposter38, R.drawable.eposter39};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onBaseCreate("Events");
        eventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        eventRules = getResources().getStringArray(R.array.event_rules);
        eventContacts = getResources().getStringArray(R.array.event_contacts);
        eventPrizes = getResources().getStringArray(R.array.event_prizes);
        for (int i = 0; i < 26; i++) {
            eventDetails = new EventDetails();
            eventDetails.setEventId(eventIds[i]);
            eventDetails.setEventName(eventNames[i]);
            eventDetails.setEventDescription(eventDescriptions[i]);
            eventDetails.setEventRule(eventRules[i]);
            eventDetails.setEventContact(eventContacts[i]);
            eventDetails.setEventPrize(eventPrizes[i]);
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
            return "General";
        }
        if (position == 1) {
            return "Applied Electronics";
        }
        if (position == 2) {
            return "Civil";
        }
        if (position == 3) {
            return "Computer Science";
        }
        if (position == 4) {
            return "Electronics";
        }
        if (position == 5) {
            return "Electrical";
        }
        if (position == 6) {
            return "Mechanical";
        }
        if (position == 7) {
            return "Workshops";
        }
        if (position == 8) {
            return "Online";
        }
        if (position == 9) {
            return "Informals";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new EventsFragment_GE();
        }
        if (position == 1) {
            fragment = new EventsFragment_AEI();
        }
        if (position == 2) {
            fragment = new EventsFragment_CE();
        }
        if (position == 3) {
            fragment = new EventsFragment_CSE();
        }
        if (position == 4) {
            fragment = new EventsFragment_ECE();
        }
        if (position == 5) {
            fragment = new EventsFragment_EEE();
        }
        if (position == 6) {
            fragment = new EventsFragment_ME();
        }
        if (position == 7) {
            fragment = new EventsFragment_WS();
        }
        if (position == 8) {
            fragment = new EventsFragment_OL();
        }
        if (position == 9) {
            fragment = new EventsFragment_INF();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
