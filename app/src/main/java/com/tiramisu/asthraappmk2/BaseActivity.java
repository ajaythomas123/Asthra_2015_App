package com.tiramisu.asthraappmk2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by ASUS on 24-01-2015.
 */
public class BaseActivity extends ActionBarActivity {
    Toolbar toolbar;
    protected void onBaseCreate(final String toolbarTitle) {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);
    }
}
