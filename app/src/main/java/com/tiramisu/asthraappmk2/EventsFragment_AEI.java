package com.tiramisu.asthraappmk2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment_AEI extends Fragment implements ClickListener{
    View view;
    List<EventDetails> aeiEvents;
    EventDetails aeiEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] aeiEventDescriptions = new String[12];


    public EventsFragment_AEI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_aei, container, false);
        aeiEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        aeiEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("AEI")) {
                aeiEventDetails = new EventDetails();
                aeiEventDetails.setEventId(eventsActivity.eventIds[i]);
                aeiEventDetails.setEventName(eventsActivity.eventNames[i]);
                aeiEventDetails.setEventDescription(aeiEventDescriptions[i]);
                aeiEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                aeiEventDetails.setEventDay(eventsActivity.eventDays[i]);
                aeiEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                aeiEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                aeiEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                aeiEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                aeiEvents.add(aeiEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), aeiEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void cardClicked(View view, int position) {

    }
}
