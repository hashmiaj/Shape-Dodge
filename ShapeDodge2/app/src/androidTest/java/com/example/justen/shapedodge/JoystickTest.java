package com.example.justen.shapedodge;

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
import static com.example.justen.shapedodge.GameView.player;


/**
 * Created by Mike on 4/7/2017.
 */
@RunWith(AndroidJUnit4.class)
public class JoystickTest {
    private static final String TAG = "JoyStickTest";

    public static GeneralSwipeAction swipeLeft() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.CENTER_LEFT, GeneralLocation.CENTER_LEFT, Press.FINGER);
    }
    public static GeneralSwipeAction swipeRight() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.CENTER_RIGHT, GeneralLocation.CENTER_RIGHT, Press.FINGER);
    }
    public static GeneralSwipeAction swipeUp() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.TOP_CENTER, GeneralLocation.TOP_CENTER, Press.FINGER);
    }
    public static GeneralSwipeAction swipeDown() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER, GeneralLocation.BOTTOM_CENTER, Press.FINGER);
    }
    @Rule
    public ActivityTestRule<MainMenu> MainMenuRule = new ActivityTestRule<MainMenu>(MainMenu.class);

    @Test
    //player should move if joystick is positioned from it's original position
    public void move() {
        onView(withId(R.id.playButton)).perform(click());
        int oldXposition = player.getxPos();
        onView(withId(R.id.joystickView)).perform(swipeLeft());
        if (player.getxPos() < oldXposition){                                         //Conditional statement that will determine if the player has gone left
            Log.v(TAG, "Player moved left Old X: " + oldXposition + " New: " +player.getxPos());
        }
        else{
            Log.v(TAG, "Player did not move Old X: " + oldXposition + " New: " +player.getxPos());      //Output messages are logged under the tag "JoyStickTest" within logcat
        }
        int oldYposition = player.getyPos();
        onView(withId(R.id.joystickView)).perform(swipeUp());
        if (player.getyPos() < oldYposition){                                         //Conditional statement that will determine if the player has gone up
            Log.v(TAG, "Player moved up Old Y: " + oldYposition + " New: " +player.getyPos());
        }
        else{
            Log.v(TAG, "Player did not move Old Y: " + oldYposition + " New: " +player.getyPos());      //Output messages are logged under the tag "JoyStickTest" within logcat
        }
        oldXposition = player.getxPos();
        onView(withId(R.id.joystickView)).perform(swipeRight());
        if (player.getxPos() > oldXposition){                                         //Conditional statement that will determine if the player has gone right
            Log.v(TAG, "Player moved right Old X: " + oldXposition + " New: " +player.getxPos());
        }
        else{
            Log.v(TAG, "Player did not move Old X: " + oldXposition + " New: " +player.getxPos());      //Output messages are logged under the tag "JoyStickTest" within logcat
        }
        oldYposition = player.getyPos();
        onView(withId(R.id.joystickView)).perform(swipeDown());
        if (player.getyPos() > oldYposition){                                         //Conditional statement that will determine if the player has gone down
            Log.v(TAG, "Player moved down Old Y: " + oldYposition + " New: " +player.getyPos());
        }
        else{
            Log.v(TAG, "Player did not move Old Y: " + oldYposition + " New: " +player.getyPos());      //Output messages are logged under the tag "JoyStickTest" within logcat
        }
    }

}