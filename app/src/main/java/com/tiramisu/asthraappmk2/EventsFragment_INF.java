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
public class EventsFragment_INF extends Fragment implements ClickListener{
    View view;
    List<EventDetails> infEvents;
    EventDetails infEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] infEventDescriptions = new String[40];
    String[] infEventRules = new String[40];
    String[] infEventContacts = new String[40];
    String[] infEventPrizes = new String[40];

    public EventsFragment_INF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_inf, container, false);
        infEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        infEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        infEventRules = getResources().getStringArray(R.array.event_rules);
        infEventContacts = getResources().getStringArray(R.array.event_contacts);
        infEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 40; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("INF")) {
                infEventDetails = new EventDetails();
                infEventDetails.setEventId(eventsActivity.eventIds[i]);
                infEventDetails.setEventName(eventsActivity.eventNames[i]);
                infEventDetails.setEventDescription(infEventDescriptions[i]);
                infEventDetails.setEventRule(infEventRules[i]);
                infEventDetails.setEventContact(infEventContacts[i]);
                infEventDetails.setEventPrize(infEventPrizes[i]);
                infEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                infEventDetails.setEventDay(eventsActivity.eventDays[i]);
                infEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                infEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                infEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                infEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                infEvents.add(infEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), infEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", infEvents.get(position).getEventId());
        intent.putExtra("eventName", infEvents.get(position).getEventName());
        intent.putExtra("eventDescription", infEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", infEvents.get(position).getEventRule());
        intent.putExtra("eventContact", infEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", infEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", infEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", infEvents.get(position).getEventDay());
        intent.putExtra("eventTime", infEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", infEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", infEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", infEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
