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
public class EventsFragment_EEE extends Fragment {
    View view;
    List<EventDetails> eeeEvents = new ArrayList<>();
    EventDetails eeeEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;

    public EventsFragment_EEE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_eee, container, false);
        eventsActivity = new EventsActivity();
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("EEE")) {
                eeeEventDetails = new EventDetails();
                eeeEventDetails.setEventId(eventsActivity.eventIds[i]);
                eeeEventDetails.setEventName(eventsActivity.eventNames[i]);
                eeeEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                eeeEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                eeeEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                eeeEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                eeeEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                eeeEvents.add(eeeEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), eeeEvents);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
