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
import static com.example.justen.shapedodge.SettingsActivity.colorIndex;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class playerColorTest {
    private static int [] playerColor = {Color.GREEN, Color.GRAY, Color.YELLOW, Color.CYAN, Color.MAGENTA};

    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);

    @Test
    //Tests if the playerColor is set to green when selected
    public void colorGreenTest()
    {
        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.playerColor)).perform(click());
            if(colorIndex == 0)
                break;
        }
        assertEquals(Color.GREEN, playerColor[colorIndex]);            //Asserts that the color Green has been stored into playerColor
    }

    @Test
    //Tests if the playerColor is set to gray when selected
    public void colorGrayTest()
    {
        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.playerColor)).perform(click());
            if(colorIndex == 1)
                break;
        }
        assertEquals(Color.GRAY, playerColor[colorIndex]);            //Asserts that the color Gray has been stored into playerColor
    }

    @Test
    //Tests if the playerColor is set to yellow when selected
    public void colorYellowTest()
    {
        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.playerColor)).perform(click());
            if(colorIndex == 2)
                break;
        }
        assertEquals(Color.YELLOW, playerColor[colorIndex]);            //Asserts that the color Yellow has been stored into playerColor
    }

    @Test
    //Tests if the playerColor is set to cyan when selected
    public void colorCyanTest()
    {
        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.playerColor)).perform(click());
            if(colorIndex == 3)
                break;
        }
        assertEquals(Color.CYAN, playerColor[colorIndex]);            //Asserts that the color Cyan has been stored into playerColor
    }

    @Test
    //Tests if the playerColor is set to magenta when selected
    public void colorMagentaTest()
    {
        onView(withId(R.id.settingsButton)).perform(click());
        for(int i = 0; i < 5; i++){
            onView(withId(R.id.playerColor)).perform(click());
            if(colorIndex == 4)
                break;
        }
        assertEquals(Color.MAGENTA, playerColor[colorIndex]);            //Asserts that the color Magenta has been stored into playerColor
    }
}
