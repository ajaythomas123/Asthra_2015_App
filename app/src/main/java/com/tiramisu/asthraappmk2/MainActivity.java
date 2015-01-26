package com.tiramisu.asthraappmk2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class MainActivity extends BaseActivity {
    CardViewNative cardViewNative;
    MaterialLargeImageCard card;
    CardExpand expand;
    String[] stArr = {"Counter Strike: Global Offensive", "DOTA 2"};
    String[] stSub = {"2:00PM", "3:00PM"};
    int[] images = {R.drawable.csgo, R.drawable.dota};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onBaseCreate("Asthra");
        ArrayList<Card> cards = new ArrayList<Card>();
        CardRecyclerView mRecyclerView = (CardRecyclerView) findViewById(R.id.carddemo_recyclerview);
        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(this, cards);


        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);

        for(int i =0; i<2; i++){
            card = MaterialLargeImageCard.with(this).useDrawableId(images[i]).setTextOverImage(stArr[i]).build();
            expand = new CardExpand(this);

            expand.setTitle(stSub[i]);

            card.addCardExpand(expand);
            cards.add(card);

            cards.get(i).setViewToClickToExpand(viewToClickToExpand);
        }





        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }




        /*card = MaterialLargeImageCard.with(this).setTitle("Counter Strike: Global Offensive").setSubTitle("2:00PM").useDrawableId(R.drawable.csgo).build();

        expand = new CardExpand(this);


        //Set inner title in Expand Area
        expand.setTitle(getString(R.string.demo_expand_basetitle));

        //Add expand to card
        card.addCardExpand(expand);
        cardViewNative = (CardViewNative) findViewById(R.id.carddemo_largeimage_text);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardViewNative);
        card.setViewToClickToExpand(viewToClickToExpand);
        cardViewNative.setCard(card);*/






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
