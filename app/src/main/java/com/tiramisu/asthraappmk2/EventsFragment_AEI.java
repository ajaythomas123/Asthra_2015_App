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
public class EventsFragment_AEI extends Fragment implements ClickListener {
    View view;
    List<EventDetails> aeiEvents;
    EventDetails aeiEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] aeiEventDescriptions = new String[41];
    String[] aeiEventRules = new String[41];
    String[] aeiEventContacts = new String[41];
    String[] aeiEventPrizes = new String[41];

    public EventsFragment_AEI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_aei, container, false);
        aeiEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        aeiEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        aeiEventRules = getResources().getStringArray(R.array.event_rules);
        aeiEventContacts = getResources().getStringArray(R.array.event_contacts);
        aeiEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 41; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("AEI")) {
                aeiEventDetails = new EventDetails();
                aeiEventDetails.setEventId(eventsActivity.eventIds[i]);
                aeiEventDetails.setEventName(eventsActivity.eventNames[i]);
                aeiEventDetails.setEventDescription(aeiEventDescriptions[i]);
                aeiEventDetails.setEventRule(aeiEventRules[i]);
                aeiEventDetails.setEventContact(aeiEventContacts[i]);
                aeiEventDetails.setEventPrize(aeiEventPrizes[i]);
                aeiEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                aeiEventDetails.setEventDay(eventsActivity.eventDays[i]);
                aeiEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                aeiEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                aeiEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                aeiEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                aeiEvents.add(aeiEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), aeiEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", aeiEvents.get(position).getEventId());
        intent.putExtra("eventName", aeiEvents.get(position).getEventName());
        intent.putExtra("eventDescription", aeiEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", aeiEvents.get(position).getEventRule());
        intent.putExtra("eventContact", aeiEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", aeiEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", aeiEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", aeiEvents.get(position).getEventDay());
        intent.putExtra("eventTime", aeiEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", aeiEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", aeiEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", aeiEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
