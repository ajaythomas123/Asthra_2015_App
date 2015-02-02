package com.tiramisu.asthraappmk2;


import android.content.Intent;
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
public class EventsFragment_EEE extends Fragment implements ClickListener {
    View view;
    List<EventDetails> eeeEvents = new ArrayList<>();
    EventDetails eeeEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] eeeEventDescriptions = new String[12];

    public EventsFragment_EEE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_eee, container, false);
        eventsActivity = new EventsActivity();
        eeeEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        String branch;
        for (int i = 0; i < 12; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("EEE")) {
                eeeEventDetails = new EventDetails();
                eeeEventDetails.setEventId(eventsActivity.eventIds[i]);
                eeeEventDetails.setEventName(eventsActivity.eventNames[i]);
                eeeEventDetails.setEventDescription(eeeEventDescriptions[i]);
                eeeEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                eeeEventDetails.setEventDay(eventsActivity.eventDays[i]);
                eeeEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                eeeEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                eeeEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                eeeEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                eeeEvents.add(eeeEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), eeeEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", eeeEvents.get(position).getEventId());
        intent.putExtra("eventName", eeeEvents.get(position).getEventName());
        intent.putExtra("eventDescription", eeeEvents.get(position).getEventDescription());
        intent.putExtra("eventBranch", eeeEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", eeeEvents.get(position).getEventDay());
        intent.putExtra("eventTime", eeeEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", eeeEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", eeeEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", eeeEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
