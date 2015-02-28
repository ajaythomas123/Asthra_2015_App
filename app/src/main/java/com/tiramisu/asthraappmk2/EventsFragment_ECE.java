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
public class EventsFragment_ECE extends Fragment implements ClickListener {
    View view;
    List<EventDetails> eceEvents = new ArrayList<>();
    EventDetails eceEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] eceEventDescriptions = new String[40];
    String[] eceEventRules = new String[40];
    String[] eceEventContacts = new String[40];
    String[] eceEventPrizes = new String[40];

    public EventsFragment_ECE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ece, container, false);
        eventsActivity = new EventsActivity();
        eceEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        eceEventRules = getResources().getStringArray(R.array.event_rules);
        eceEventContacts = getResources().getStringArray(R.array.event_contacts);
        eceEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 40; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("ECE")) {
                eceEventDetails = new EventDetails();
                eceEventDetails.setEventId(eventsActivity.eventIds[i]);
                eceEventDetails.setEventName(eventsActivity.eventNames[i]);
                eceEventDetails.setEventDescription(eceEventDescriptions[i]);
                eceEventDetails.setEventRule(eceEventRules[i]);
                eceEventDetails.setEventContact(eceEventContacts[i]);
                eceEventDetails.setEventPrize(eceEventPrizes[i]);
                eceEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                eceEventDetails.setEventDay(eventsActivity.eventDays[i]);
                eceEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                eceEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                eceEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                eceEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                eceEvents.add(eceEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), eceEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", eceEvents.get(position).getEventId());
        intent.putExtra("eventName", eceEvents.get(position).getEventName());
        intent.putExtra("eventDescription", eceEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", eceEvents.get(position).getEventRule());
        intent.putExtra("eventContact", eceEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", eceEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", eceEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", eceEvents.get(position).getEventDay());
        intent.putExtra("eventTime", eceEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", eceEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", eceEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", eceEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
