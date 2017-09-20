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
import static com.example.justen.shapedodge.GameView.gameHeight;
import static com.example.justen.shapedodge.GameView.gameWidth;
import static com.example.justen.shapedodge.GameView.player;

@RunWith(AndroidJUnit4.class)
public class boundsTest {
    private GameView gameScreen;
    private int playerSize = 20;
    private static final String TAG = "boundsTest";


    //Methods used to simulate a swipe interaction
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

    //Test will swipe in all directions until the player reaches the left bound
    @Test
    public void joystickViewMove() {
        onView(withId(R.id.playButton)).perform(click());

        for (int i = 0; i < 5; i++) {                                                //For loop to iterate multiple swipes that will attempt to
            onView(withId(R.id.joystickView)).perform(swipeLeft());                  //move the player out of bounds
        }

        if (player.getxPos() == playerSize) {                                         //Conditional statement that will determine if the player has gone
            Log.v(TAG, "Player is in bounds, xPos = " + player.getxPos());           //out of bounds. If the xPos is == to the playerSize, this will indicate that the player has
        }                                                                            //reached the left bound of the game
        else {
            Log.v(TAG, "Player is out of  bounds, xPos = " + player.getxPos());      //Output messages are logged under the tag "boundsTest" within logcat
        }


        //Test will swipe right until the player reaches the right bound

        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.joystickView)).perform(swipeRight());
        }

        if (player.getxPos() == gameWidth - playerSize) {                             //Conditional statement that will determine if the player has gone
            Log.v(TAG, "Player is in bounds, xPos = " + player.getxPos());           //out of bounds. If the xPos is == to the gameWidth - playerSize, this will indicate that the
        }                                                                            //player has reached the right bound of the game
        else {
            Log.v(TAG, "Player is out of  bounds, xPos = " + player.getxPos());      //Output messages are logged under the tag "boundsTest" within logcat
        }


        //Test will swipe up until the player reaches the upper bound


        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.joystickView)).perform(swipeUp());
        }

        if (player.getyPos() == playerSize) {                                         //Conditional statement that will determine if the player has gone
            Log.v(TAG, "Player is in bounds, yPos = " + player.getyPos());           //out of bounds. If the yPos is == to the playerSize, this will indicate that the
        }                                                                            //player has reached the upper bound of the game
        else {
            Log.v(TAG, "Player is out of  bounds, yPos = " + player.getyPos());      //Output messages are logged under the tag "boundsTest" within logcat
        }


        //Test will swipe down until the player reaches the bottom bound


        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.joystickView)).perform(swipeDown());
        }

        if (player.getyPos() == gameHeight - playerSize) {                            //Conditional statement that will determine if the player has gone
            Log.v(TAG, "Player is in bounds, yPos = " + player.getyPos());           //out of bounds. If the yPos is == to the gameHeight - playerSize, this will indicate that the
        }                                                                            //player has reached the bottom bound of the game
        else {
            Log.v(TAG, "Player is out of  bounds, yPos = " + player.getyPos());      //Output messages are logged under the tag "boundsTest" within logcat
        }
    }
}



