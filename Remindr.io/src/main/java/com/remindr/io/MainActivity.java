package com.remindr.io;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    String sampleTweet = "I absolutely love being in Portland, OR!";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ListViewFragment.newInstance()).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // isEvent checks an individual tweet to see if it has a location and date
    // if it does not, it returns false

    public boolean isEvent(String tweetText_inp) {
        String[] tweetText = tweetText_inp.split(" ");
        boolean loc = hasLocation(tweetText);
        boolean date = hasDate(tweetText);

        if (loc && date)
            return true;
        else
            return false;

    }

    public boolean hasLocation(String[] tweetText) {
        String[] locationList = {"corvallis", "portland", "mcdonald", "wow", "hall", "kelley engineering", "roseland", "theater", "woodburn"};
        int count = 0;
        int len = tweetText.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < locationList.length; j++) {
                if (tweetText[i].toLowerCase() == locationList[j]) {
                    count++;
                }
            }
        }
        if (count >= 2)
            return true;
        else
            return false;
    }

    public boolean hasDate(String[] tweetText) {
        String[] dateList = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        int len = tweetText.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (tweetText[i].toLowerCase() == dateList[j])
                    return true;
            }
        }

        return false;
    }

    public String returnPlace(String tweet) {
        String[] tweetText = tweet.split(" ");
        String out = "";
        if (isEvent(tweet)) {
            String[] locationList = {"corvallis", "portland", "mcdonald", "wow", "hall", "kelley engineering", "roseland", "theater", "woodburn"};
            int count = 0;
            int len = tweetText.length;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < locationList.length; j++) {
                    if (tweetText[i].toLowerCase() == locationList[j]) {
                        out += locationList[j] + " ";
                    }
                }

            }
        }
        return out;

    }
}
