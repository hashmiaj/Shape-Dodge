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
public class PauseActivityTest {
    @Rule
    public ActivityTestRule<GameActivity> GameActivity = new ActivityTestRule<com.example.justen.shapedodge.GameActivity>(GameActivity.class);

    @Test
    public void pauseActivityIsDisplayed(){
        onView(withId(R.id.pauseButton)).perform(click());
        onView(withId(R.id.quitButton)).check(matches(isDisplayed()));
        onView(withId(R.id.resumeButton)).check(matches(isDisplayed()));
        onView(withId(R.id.restartButton)).check(matches(isDisplayed()));
        onView(withId(R.id.soundSettingsButton)).check(matches(isDisplayed()));
    }

    @Test
    public void resumeButtonFunctions(){
        onView(withId(R.id.pauseButton)).perform(click());
        onView(withId(R.id.resumeButton)).perform(click());
        onView(withId(R.id.gameView)).check(matches(isDisplayed()));
    }

    @Test
    public void quitButtonFunctions(){
        onView(withId(R.id.pauseButton)).perform(click());
        onView(withId(R.id.quitButton)).perform(click());
        onView(withId(R.id.playButton)).check(matches(isDisplayed()));
    }
}