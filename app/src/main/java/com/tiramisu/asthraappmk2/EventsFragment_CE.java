package com.tiramisu.asthraappmk2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment_CE extends Fragment {
    View view;
    List<EventDetails> ceEvents;
    EventDetails ceEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;

    public EventsFragment_CE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ce, container, false);
        ceEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("CE")) {
                ceEventDetails = new EventDetails();
                ceEventDetails.setEventId(eventsActivity.eventIds[i]);
                ceEventDetails.setEventName(eventsActivity.eventNames[i]);
                ceEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                ceEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                ceEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                ceEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                ceEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                ceEvents.add(ceEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), ceEvents);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
