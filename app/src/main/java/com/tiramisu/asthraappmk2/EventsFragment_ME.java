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
public class EventsFragment_ME extends Fragment {
    View view;
    List<EventDetails> meEvents = new ArrayList<>();
    EventDetails meEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;

    public EventsFragment_ME() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_me, container, false);
        eventsActivity = new EventsActivity();
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("ME")) {
                meEventDetails = new EventDetails();
                meEventDetails.setEventId(eventsActivity.eventIds[i]);
                meEventDetails.setEventName(eventsActivity.eventNames[i]);
                meEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                meEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                meEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                meEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                meEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                meEvents.add(meEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), meEvents);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}
