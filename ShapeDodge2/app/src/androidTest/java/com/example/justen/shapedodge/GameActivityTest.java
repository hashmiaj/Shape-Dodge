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
public class GameActivityTest {
    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);
    //Tests if GameActivity is shown
    //Tests if joystick is displayed
    @Test
    public void joystickViewIsDisplayed() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.joystickView)).check(matches(isDisplayed()));
    }
    //Tests if lives is displayed
    @Test
    public void livesTextIsDisplayed() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.livesText)).check(matches(isDisplayed()));
    }
    //Tests if score is displayed
    @Test
    public void scoreTextIsDisplayed() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.inscoreText)).check(matches(isDisplayed()));
    }
    //Tests if pausebutton is displayed
    @Test
    public void pauseButtonIsDisplayed() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.pauseButton)).check(matches(isDisplayed()));
    }

}