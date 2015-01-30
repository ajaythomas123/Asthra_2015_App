package com.tiramisu.asthraappmk2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 30-01-2015.
 */
public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.EventViewHolder> {
    public LayoutInflater inflater;
    List<EventDetails> eventDetails = Collections.emptyList();
    Context context;


    public EventCardAdapter(Context context, List<EventDetails> eventDetails) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.eventDetails = eventDetails;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.event_card, parent, false);
        EventViewHolder holder = new EventViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventDetails current = eventDetails.get(position);
        holder.eventName.setText(current.getEventName());
        holder.eventPoster.setImageResource(current.getEventPosterId());
    }

    @Override
    public int getItemCount() {
        return eventDetails.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        ImageView eventPoster;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            eventPoster = (ImageView) itemView.findViewById(R.id.eventImage);
        }
    }
}
