package com.example.justen.shapedodge;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import  android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by berke on 3/23/2017.
 */
@RunWith(AndroidJUnit4.class)
public class HighScoreActivityTest {

    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);
    //Tests when highscore button is pressed, highscore activity is shown.
    @Test
    public void highscoreButtonisClicked(){
        onView(withId(R.id.highscoreButton)).perform(click());
        onView(withId(R.id.highscoreText)).check(matches(withText("High Scores")));
        onView(withId(R.id.rankText)).check(matches(withText("Rank")));
        onView(withId(R.id.scoreText)).check(matches(withText("Score")));
        onView(withId(R.id.usernameText)).check(matches(withText("Username")));
    }
    //Tests if main menu button functions correctly.
    @Test
    public void mainMenuButtonIsClicked(){
        onView(withId(R.id.highscoreButton)).perform(click());
        onView(withId(R.id.mainMenuBtn)).perform(click());
        onView(withId(R.id.playButton)).check(matches(isDisplayed()));
    }
}