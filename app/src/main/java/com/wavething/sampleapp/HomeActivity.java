/*
 * Copyright 2017 Kosuke Namihira All Rights Reserved.
 */
package com.wavething.sampleapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

public class HomeActivity extends AppCompatActivity {

    private static final String TWITTER_API_KEY = BuildConfig.TWITTER_API_KEY;
    private static final String TWITTER_API_SECRET = BuildConfig.TWITTER_API_SECRET;

    private ListView timelineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_API_KEY, TWITTER_API_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
            }
        });

        final TwitterSession twitterSession = Twitter.getInstance().core.getSessionManager().getActiveSession();
        final String username = twitterSession != null ? twitterSession.getUserName() : "twitter";

        final UserTimeline userTimeline = new UserTimeline.Builder().screenName(username).build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this).setTimeline(userTimeline).build();

        timelineView = (ListView) findViewById(R.id.timeline);
        timelineView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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

        if (id == R.id.action_login) {
            Intent intent = new Intent(getApplication(), LoginWithTwitterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
