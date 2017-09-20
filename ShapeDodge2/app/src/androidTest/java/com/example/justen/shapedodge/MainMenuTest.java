package com.example.justen.shapedodge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class MainMenuTest {


    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);

    //Tests if mainmenu buttons (options) are displayed
    @Test
    public void mainMenuIsDisplayed(){
        onView(withId(R.id.playButton)).check(matches(isDisplayed()));
        onView(withId(R.id.settingsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.highscoreButton)).check(matches(isDisplayed()));
    }

    //Tests if settings button is clickable
    @Test
    public void settingsButtonAppear(){
        onView(withId(R.id.settingsButton)).perform(click());
    }

    //Tests if highscore button is clickable
    @Test
    public void highScoreButtonAppear(){
        onView(withId(R.id.highscoreButton)).perform(click());
    }
    //Tests if play button is clickable
    @Test
    public void playButtonAppear(){
        onView(withId(R.id.playButton)).perform(click());
    }
}