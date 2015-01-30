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
public class EventsFragment_ECE extends Fragment {
    View view;
    List<EventDetails> eceEvents = new ArrayList<>();
    EventDetails eceEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;

    public EventsFragment_ECE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ece, container, false);
        eventsActivity = new EventsActivity();
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("ECE")) {
                eceEventDetails = new EventDetails();
                eceEventDetails.setEventId(eventsActivity.eventIds[i]);
                eceEventDetails.setEventName(eventsActivity.eventNames[i]);
                eceEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                eceEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                eceEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                eceEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                eceEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                eceEvents.add(eceEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), eceEvents);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
