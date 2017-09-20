package com.example.justen.shapedodge;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.rule.ActivityTestRule;
import  android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.justen.shapedodge.SettingsActivity.level;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class difficultyTest {
    public static String difficultyEasy = "Difficulty: Easy";
    public static String difficultyMedium = "Difficulty: Medium";
    public static String difficultyHard = "Difficulty: Hard";
    public static String difficultyInsane = "Difficulty: Insane";

    public static String[] difficultyLevel ={difficultyEasy,difficultyMedium,difficultyHard,difficultyInsane};

    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);


    @Test
    //Tests if the playerColor is set to green when selected
    public void difficultyEasyTest(){

       onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.difficulty)).perform(click());
            if(level == 0)
                break;
        }
        assertEquals(difficultyEasy, difficultyLevel[level]);            //Asserts that the difficulty easy gets displayed
    }

    @Test
    //Tests if the playerColor is set to green when selected
    public void difficultyMediumTest(){

        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.difficulty)).perform(click());
            if(level == 1)
                break;
        }
        assertEquals(difficultyMedium, difficultyLevel[level]);            //Asserts that the difficulty medium gets displayed
    }

    @Test
    //Tests if the playerColor is set to green when selected
    public void difficultyHardTest(){

        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.difficulty)).perform(click());
            if(level == 2)
                break;
        }
        assertEquals(difficultyHard, difficultyLevel[level]);            //Asserts that the difficulty hard gets displayed
    }

    @Test
    //Tests if the playerColor is set to green when selected
    public void difficultyInsaneTest(){

        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.difficulty)).perform(click());
            if(level == 3)
                break;
        }
        assertEquals(difficultyInsane, difficultyLevel[level]);            //Asserts that the difficulty insane gets displayed
    }


}
