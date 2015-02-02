package com.tiramisu.asthraappmk2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 03-02-2015.
 */
public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.RegistrationViewHolder>{
    public LayoutInflater inflater;
    List<EventDetails> eventDetails = Collections.emptyList();
    Context context;
    ClickListener clickListener;

    public RegistrationAdapter(Context context, List<EventDetails> eventDetails){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.eventDetails = eventDetails;
    }

    @Override
    public RegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.registration_list_item, parent, false);
        RegistrationViewHolder holder = new RegistrationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RegistrationViewHolder holder, int position) {
        EventDetails current = eventDetails.get(position);
        holder.eventName.setText(current.getEventName());
    }

    @Override
    public int getItemCount() {
        return eventDetails.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class RegistrationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventName;
        public RegistrationViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }

        }
    }
}
