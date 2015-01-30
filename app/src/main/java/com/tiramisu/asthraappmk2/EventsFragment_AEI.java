package com.tiramisu.asthraappmk2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment_AEI extends Fragment {
    View view;
    List<EventDetails> aeiEvents;
    EventDetails aeiEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;

    public EventsFragment_AEI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_aei, container, false);
        aeiEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("AEI")) {
                aeiEventDetails = new EventDetails();
                aeiEventDetails.setEventId(eventsActivity.eventIds[i]);
                aeiEventDetails.setEventName(eventsActivity.eventNames[i]);
                aeiEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                aeiEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                aeiEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                aeiEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                aeiEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                aeiEvents.add(aeiEventDetails);
            }
        }
        Log.d("Ajay", aeiEvents.get(0).getEventName() + aeiEvents.get(1).getEventName());
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), aeiEvents);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
