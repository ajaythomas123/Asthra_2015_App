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
public class EventsFragment_WS extends Fragment implements ClickListener{
    View view;
    List<EventDetails> wsEvents;
    EventDetails wsEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] wsEventDescriptions = new String[41];
    String[] wsEventRules = new String[41];
    String[] wsEventContacts = new String[41];
    String[] wsEventPrizes = new String[41];

    public EventsFragment_WS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_ws, container, false);
        wsEvents = new ArrayList<>();
        eventsActivity = new EventsActivity();
        wsEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        wsEventRules = getResources().getStringArray(R.array.event_rules);
        wsEventContacts = getResources().getStringArray(R.array.event_contacts);
        wsEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 41; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("WS")) {
                wsEventDetails = new EventDetails();
                wsEventDetails.setEventId(eventsActivity.eventIds[i]);
                wsEventDetails.setEventName(eventsActivity.eventNames[i]);
                wsEventDetails.setEventDescription(wsEventDescriptions[i]);
                wsEventDetails.setEventRule(wsEventRules[i]);
                wsEventDetails.setEventContact(wsEventContacts[i]);
                wsEventDetails.setEventPrize(wsEventPrizes[i]);
                wsEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                wsEventDetails.setEventDay(eventsActivity.eventDays[i]);
                wsEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                wsEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                wsEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                wsEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                wsEvents.add(wsEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), wsEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", wsEvents.get(position).getEventId());
        intent.putExtra("eventName", wsEvents.get(position).getEventName());
        intent.putExtra("eventDescription", wsEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", wsEvents.get(position).getEventRule());
        intent.putExtra("eventContact", wsEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", wsEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", wsEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", wsEvents.get(position).getEventDay());
        intent.putExtra("eventTime", wsEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", wsEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", wsEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", wsEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
