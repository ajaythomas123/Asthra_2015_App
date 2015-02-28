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
public class EventsFragment_GAM extends Fragment implements ClickListener{
    View view;
    List<EventDetails> gamEvents;
    EventDetails gamEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] gamEventDescriptions = new String[40];
    String[] gamEventRules = new String[40];
    String[] gamEventContacts = new String[40];
    String[] gamEventPrizes = new String[40];

    public EventsFragment_GAM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_gam, container, false);
        gamEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        gamEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        gamEventRules = getResources().getStringArray(R.array.event_rules);
        gamEventContacts = getResources().getStringArray(R.array.event_contacts);
        gamEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 40; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("GAM")) {
                gamEventDetails = new EventDetails();
                gamEventDetails.setEventId(eventsActivity.eventIds[i]);
                gamEventDetails.setEventName(eventsActivity.eventNames[i]);
                gamEventDetails.setEventDescription(gamEventDescriptions[i]);
                gamEventDetails.setEventRule(gamEventRules[i]);
                gamEventDetails.setEventContact(gamEventContacts[i]);
                gamEventDetails.setEventPrize(gamEventPrizes[i]);
                gamEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                gamEventDetails.setEventDay(eventsActivity.eventDays[i]);
                gamEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                gamEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                gamEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                gamEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                gamEvents.add(gamEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), gamEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", gamEvents.get(position).getEventId());
        intent.putExtra("eventName", gamEvents.get(position).getEventName());
        intent.putExtra("eventDescription", gamEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", gamEvents.get(position).getEventRule());
        intent.putExtra("eventContact", gamEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", gamEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", gamEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", gamEvents.get(position).getEventDay());
        intent.putExtra("eventTime", gamEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", gamEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", gamEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", gamEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
