package com.tiramisu.asthraappmk2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 24-01-2015.
 */
public class BaseActivity extends ActionBarActivity {
    public DrawerLayout drawerLayout = null;
    private ActionBarDrawerToggle drawerToggle;
    protected static final int NAVDRAWER_ITEM_HOME = 1;
    protected static final int NAVDRAWER_ITEM_EVENTS = 2;
    protected static final int NAVDRAWER_ITEM_REG = 3;
    protected static final int NAVDRAWER_ITEM_INVALID = -1;
    protected static final int NAVDRAWER_ITEM_LOCATION = 4;
    ArrayList<EventDetails> allEvents = new ArrayList<>();
    EventDetails eventDetails;
    RecyclerView recyclerView;
    NavDrawerAdapter adapter;
    Toolbar toolbar;
    int itemId;

    protected void onBaseCreate(final String toolbarTitle) {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);

        // R.id.drawer_layout should be in every activity with exactly the same id.
        itemId = getSelfNavDrawerItem();
        if (itemId == NAVDRAWER_ITEM_INVALID) {
            drawerLayout = null;
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } else {
            drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
                public void onDrawerClosed(View view) {

                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    invalidateOptionsMenu();
                }

                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                    /*if (slideOffset < 0.6) {
                        toolbar.setAlpha(1  slideOffset);
                    }*/
                }
            };
            drawerLayout.setDrawerListener(drawerToggle);

            recyclerView = (RecyclerView) findViewById(R.id.drawerList);
            adapter = new NavDrawerAdapter(this, getData());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (drawerLayout != null) {


            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (drawerLayout != null) {
            drawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public static List<NavigationDrawerInfo> getData() {
        List<NavigationDrawerInfo> data = new ArrayList<>();
        int[] icons = {R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder};
        String[] titles = {"Home", "Events", "Registration", "Location"};
        for (int i = 0; i < titles.length; i++) {
            NavigationDrawerInfo current = new NavigationDrawerInfo();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    private void goToNavDrawerItem(int item) {
        Intent intent;
        switch (item) {
            case NAVDRAWER_ITEM_HOME:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case NAVDRAWER_ITEM_EVENTS:
                intent = new Intent(this, EventsActivity.class);
                startActivity(intent);
                finish();
                break;

            case NAVDRAWER_ITEM_REG:
                intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                break;

            case NAVDRAWER_ITEM_LOCATION:
                intent = new Intent(this, LocationActivity.class);
                startActivity(intent);
        }
    }


    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }

    public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {
        private LayoutInflater inflater;
        BaseActivity baseActivity = new BaseActivity();             //Don't delete these two lines.
        private Context context;
        List<NavigationDrawerInfo> data = Collections.emptyList();

        public NavDrawerAdapter(Context context, List<NavigationDrawerInfo> data) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.navdrawer_row, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i) {
            NavigationDrawerInfo current = data.get(i);
            viewHolder.title.setText(current.title);
            viewHolder.icon.setImageResource(current.iconId);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                title = (TextView) itemView.findViewById(R.id.listText);
                icon = (ImageView) itemView.findViewById(R.id.listIcon);

            }

            @Override
            public void onClick(View v) {
                if (getPosition() == 0) {
                    goToNavDrawerItem(NAVDRAWER_ITEM_HOME);
                }
                if (getPosition() == 1) {
                    goToNavDrawerItem(NAVDRAWER_ITEM_EVENTS);
                }
                if (getPosition() == 2) {
                    goToNavDrawerItem(NAVDRAWER_ITEM_REG);
                }
                if (getPosition() == 3) {
                    goToNavDrawerItem(NAVDRAWER_ITEM_LOCATION);
                }
            }
        }
    }
}
