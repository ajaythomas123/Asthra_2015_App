package com.tiramisu.asthraappmk2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment_CSE extends Fragment {
    View view;
    MaterialLargeImageCard card;
    CardExpand expand;
    String[] stArr = {"Counter Strike: Global Offensive", "DOTA 2"};
    String[] stSub = {"2:00PM", "3:00PM"};
    int[] images = {R.drawable.csgo, R.drawable.dota};


    public EventsFragment_CSE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events_cse, container, false);


        ArrayList<Card> cards = new ArrayList<Card>();
        CardRecyclerView mRecyclerView = (CardRecyclerView) view.findViewById(R.id.carddemo_recyclerview);
        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);


        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);

        for (int i = 0; i < 2; i++) {
            card = MaterialLargeImageCard.with(getActivity()).useDrawableId(images[i]).setTextOverImage(stArr[i]).build();
            expand = new CardExpand(getActivity());

            expand.setTitle(stSub[i]);

            card.addCardExpand(expand);
            cards.add(card);

            cards.get(i).setViewToClickToExpand(viewToClickToExpand);
        }

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
        return view;
    }


}
