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
public class EventsFragment_ME extends Fragment implements ClickListener {
    View view;
    List<EventDetails> meEvents = new ArrayList<>();
    EventDetails meEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] meEventDescriptions = new String[41];
    String[] meEventRules = new String[41];
    String[] meEventContacts = new String[41];
    String[] meEventPrizes = new String[41];

    public EventsFragment_ME() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_me, container, false);
        eventsActivity = new EventsActivity();
        meEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        meEventRules = getResources().getStringArray(R.array.event_rules);
        meEventContacts = getResources().getStringArray(R.array.event_contacts);
        meEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 41; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("ME")) {
                meEventDetails = new EventDetails();
                meEventDetails.setEventId(eventsActivity.eventIds[i]);
                meEventDetails.setEventName(eventsActivity.eventNames[i]);
                meEventDetails.setEventDescription(meEventDescriptions[i]);
                meEventDetails.setEventRule(meEventRules[i]);
                meEventDetails.setEventContact(meEventContacts[i]);
                meEventDetails.setEventPrize(meEventPrizes[i]);
                meEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                meEventDetails.setEventDay(eventsActivity.eventDays[i]);
                meEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                meEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                meEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                meEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                meEvents.add(meEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), meEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", meEvents.get(position).getEventId());
        intent.putExtra("eventName", meEvents.get(position).getEventName());
        intent.putExtra("eventDescription", meEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", meEvents.get(position).getEventRule());
        intent.putExtra("eventContact", meEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", meEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", meEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", meEvents.get(position).getEventDay());
        intent.putExtra("eventTime", meEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", meEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", meEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", meEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
