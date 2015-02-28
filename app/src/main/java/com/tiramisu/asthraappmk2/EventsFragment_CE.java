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
public class EventsFragment_CE extends Fragment implements ClickListener{
    View view;
    List<EventDetails> ceEvents;
    EventDetails ceEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] ceEventDescriptions = new String[26];
    String[] ceEventRules = new String[26];
    String[] ceEventContacts = new String[26];
    String[] ceEventPrizes = new String[26];

    public EventsFragment_CE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ce, container, false);
        ceEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        ceEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        ceEventRules = getResources().getStringArray(R.array.event_rules);
        ceEventContacts = getResources().getStringArray(R.array.event_contacts);
        ceEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 26; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("CE")) {
                ceEventDetails = new EventDetails();
                ceEventDetails.setEventId(eventsActivity.eventIds[i]);
                ceEventDetails.setEventName(eventsActivity.eventNames[i]);
                ceEventDetails.setEventDescription(ceEventDescriptions[i]);
                ceEventDetails.setEventRule(ceEventRules[i]);
                ceEventDetails.setEventContact(ceEventContacts[i]);
                ceEventDetails.setEventPrize(ceEventPrizes[i]);
                ceEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                ceEventDetails.setEventDay(eventsActivity.eventDays[i]);
                ceEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                ceEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                ceEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                ceEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                ceEvents.add(ceEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), ceEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", ceEvents.get(position).getEventId());
        intent.putExtra("eventName", ceEvents.get(position).getEventName());
        intent.putExtra("eventDescription", ceEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", ceEvents.get(position).getEventRule());
        intent.putExtra("eventContact", ceEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", ceEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", ceEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", ceEvents.get(position).getEventDay());
        intent.putExtra("eventTime", ceEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", ceEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", ceEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", ceEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
