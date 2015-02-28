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
public class EventsFragment_OL extends Fragment implements ClickListener{
    View view;
    List<EventDetails> olEvents;
    EventDetails olEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] olEventDescriptions = new String[40];
    String[] olEventRules = new String[40];
    String[] olEventContacts = new String[40];
    String[] olEventPrizes = new String[40];

    public EventsFragment_OL() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ol, container, false);
        olEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        olEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        olEventRules = getResources().getStringArray(R.array.event_rules);
        olEventContacts = getResources().getStringArray(R.array.event_contacts);
        olEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 40; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("OL")) {
                olEventDetails = new EventDetails();
                olEventDetails.setEventId(eventsActivity.eventIds[i]);
                olEventDetails.setEventName(eventsActivity.eventNames[i]);
                olEventDetails.setEventDescription(olEventDescriptions[i]);
                olEventDetails.setEventRule(olEventRules[i]);
                olEventDetails.setEventContact(olEventContacts[i]);
                olEventDetails.setEventPrize(olEventPrizes[i]);
                olEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                olEventDetails.setEventDay(eventsActivity.eventDays[i]);
                olEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                olEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                olEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                olEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                olEvents.add(olEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), olEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", olEvents.get(position).getEventId());
        intent.putExtra("eventName", olEvents.get(position).getEventName());
        intent.putExtra("eventDescription", olEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", olEvents.get(position).getEventRule());
        intent.putExtra("eventContact", olEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", olEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", olEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", olEvents.get(position).getEventDay());
        intent.putExtra("eventTime", olEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", olEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", olEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", olEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
