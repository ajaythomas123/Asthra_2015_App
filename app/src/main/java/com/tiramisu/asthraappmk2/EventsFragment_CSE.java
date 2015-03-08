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
public class EventsFragment_CSE extends Fragment implements ClickListener {
    View view;
    List<EventDetails> cseEvents = new ArrayList<>();
    EventDetails cseEventDetails;
    EventsActivity eventsActivity;
    RecyclerView recyclerView;
    EventCardAdapter eventCardAdapter;
    String[] cseEventDescriptions = new String[41];
    String[] cseEventRules = new String[41];
    String[] cseEventContacts = new String[41];
    String[] cseEventPrizes = new String[41];


    public EventsFragment_CSE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_cse, container, false);
        eventsActivity = new EventsActivity();
        cseEventDescriptions = getResources().getStringArray(R.array.event_descriptions);
        cseEventRules = getResources().getStringArray(R.array.event_rules);
        cseEventContacts = getResources().getStringArray(R.array.event_contacts);
        cseEventPrizes = getResources().getStringArray(R.array.event_prizes);
        String branch;
        for (int i = 0; i < 41; i++) {
            branch = eventsActivity.eventBranches[i];
            if (branch.equals("CSE")) {
                cseEventDetails = new EventDetails();
                cseEventDetails.setEventId(eventsActivity.eventIds[i]);
                cseEventDetails.setEventName(eventsActivity.eventNames[i]);
                cseEventDetails.setEventDescription(cseEventDescriptions[i]);
                cseEventDetails.setEventRule(cseEventRules[i]);
                cseEventDetails.setEventContact(cseEventContacts[i]);
                cseEventDetails.setEventPrize(cseEventPrizes[i]);
                cseEventDetails.setEventBranch(eventsActivity.eventBranches[i]);
                cseEventDetails.setEventDay(eventsActivity.eventDays[i]);
                cseEventDetails.setEventTime(eventsActivity.eventTimes[i]);
                cseEventDetails.setEventTeam(eventsActivity.eventTeam[i]);
                cseEventDetails.setEventSpot(eventsActivity.eventSpot[i]);
                cseEventDetails.setEventPosterId(eventsActivity.eventPosterIds[i]);
                cseEvents.add(cseEventDetails);
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycler);
        eventCardAdapter = new EventCardAdapter(getActivity(), cseEvents);
        eventCardAdapter.setClickListener(this);
        recyclerView.setAdapter(eventCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), EventInfoActivity.class);

        intent.putExtra("eventId", cseEvents.get(position).getEventId());
        intent.putExtra("eventName", cseEvents.get(position).getEventName());
        intent.putExtra("eventDescription", cseEvents.get(position).getEventDescription());
        intent.putExtra("eventDescription", cseEvents.get(position).getEventDescription());
        intent.putExtra("eventRule", cseEvents.get(position).getEventRule());
        intent.putExtra("eventContact", cseEvents.get(position).getEventContact());
        intent.putExtra("eventPrize", cseEvents.get(position).getEventPrize());
        intent.putExtra("eventBranch", cseEvents.get(position).getEventBranch());
        intent.putExtra("eventDay", cseEvents.get(position).getEventDay());
        intent.putExtra("eventTime", cseEvents.get(position).getEventTime());
        intent.putExtra("eventTeam", cseEvents.get(position).getEventTeam());
        intent.putExtra("eventSpot", cseEvents.get(position).getEventSpot());
        intent.putExtra("eventPosterId", cseEvents.get(position).getEventPosterId());

        startActivity(intent);
    }
}
