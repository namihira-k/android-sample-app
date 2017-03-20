/*
 * Copyright 2017 Kosuke Namihira All Rights Reserved.
 */
package com.wavething.sampleapp;

import android.app.ListActivity;
import android.os.Bundle;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Activity for Twitter Timeline
 */
public class TimelineActivity extends ListActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        final String username = "twitter";

        final UserTimeline userTimeline = new UserTimeline.Builder().screenName(username).build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this).setTimeline(userTimeline).build();

        setListAdapter(adapter);
    }
}
