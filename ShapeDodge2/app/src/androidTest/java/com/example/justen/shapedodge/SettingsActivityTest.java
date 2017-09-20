package com.example.justen.shapedodge;

import static org.junit.Assert.*;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import  android.support.test.runner.AndroidJUnit4;

import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by berke on 3/23/2017.
 */

@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> SettingsActivityRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);
    //Tests if settings activity is shown
    @Test
    public void buttonsAppear(){
        onView(withId(R.id.difficulty)).check(matches(isDisplayed()));
        onView(withId(R.id.Music)).check(matches(isDisplayed()));
        onView(withId(R.id.playerColor)).check(matches(isDisplayed()));
        onView(withId(R.id.mainMenu)).check(matches(isDisplayed()));
    }
    //Tests if main menu button functions correctly
    @Test
    public void mainMenuButtonFuntions(){
        onView(withId(R.id.mainMenu)).perform(click());
        onView(withId(R.id.playButton)).check(matches(isDisplayed()));
    }

}