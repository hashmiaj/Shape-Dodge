package com.example.justen.shapedodge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/17/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Responsible for configuring the game and access credits
        -- Can access credits, change skin (color), change difficulty, and change music
		--
		--
----------------------------------------------------------------------------------*/

public class SettingsActivity extends AppCompatActivity {

    public static int level ;
    public static String difficultyEasy = "Difficulty: Easy";
    public static String difficultyMedium = "Difficulty: Medium";
    public static String difficultyHard = "Difficulty: Hard";
    public static String difficultyInsane = "Difficulty: Insane";

    public static int maxIndex;
    public static int colorIndex;
    private boolean playMusic;

    private SharedPreferences gameValues;
    private SharedPreferences.Editor editGameValues;

    Button playerColorText;
    Button MainMenuButton;
    Button difficultySetting;
    Button musicSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        playerColorText = (Button) findViewById(R.id.playerColor);
        MainMenuButton = (Button) findViewById(R.id.mainMenu);
        difficultySetting = (Button) findViewById(R.id.difficulty);
        musicSetting = (Button) findViewById(R.id.Music);

        gameValues = getSharedPreferences("gameValues", Context.MODE_PRIVATE);
        editGameValues = gameValues.edit();
        maxIndex = gameValues.getInt("maxIndex",5);
        colorIndex = gameValues.getInt("colorIndex",0);
        level = gameValues.getInt("level",0);
        playMusic = gameValues.getBoolean("playMusic", true);

        setColor(colorIndex);// sets previous or last saved color
        setLevel(level);// sets previous or last saved level

        MainMenuButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                SettingsActivity.super.onBackPressed();
            }
        });

        playerColorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorIndex = gameValues.getInt("colorIndex",0);
                colorIndex++;
                colorIndex %= maxIndex;
                editGameValues.putInt("colorIndex",colorIndex);
                setColor(colorIndex);
                editGameValues.commit();
            }
        });

        difficultySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = gameValues.getInt("level",0);
                level++;
                level%=4;
                editGameValues.putInt("level",level);
                setLevel(level);
                editGameValues.commit();

            }


        });

        musicSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playMusic = gameValues.getBoolean("playMusic", true);
                playMusic=!playMusic;
                editGameValues.putBoolean("playMusic",playMusic);
                editGameValues.commit();
                if(playMusic){
                    musicSetting.setText("Music: On");
                }else{
                    musicSetting.setText("Music: Off");
                }
            }
                                        }
        );
    }

    protected void setLevel(int level) {
        final Button difficultySetting = (Button) findViewById(R.id.difficulty);
        switch (level){
            case 0:
                difficultySetting.setText(difficultyEasy);
                editGameValues.putInt("minDimension",10);
                editGameValues.putInt("difficulty", 100);
                editGameValues.putInt("maxratio", 6);
                editGameValues.putInt("enemyDirection",2);
                editGameValues.putInt("powerUpSpawn", 1000);
                break;
            case 1:
                difficultySetting.setText(difficultyMedium);
                editGameValues.putInt("minDimension",20);
                editGameValues.putInt("difficulty", 100);
                editGameValues.putInt("maxratio", 5);
                editGameValues.putInt("enemyDirection",4);
                editGameValues.putInt("powerUpSpawn", 750);
                break;
            case 2:
                difficultySetting.setText(difficultyHard);
                editGameValues.putInt("minDimension",30);
                editGameValues.putInt("difficulty", 75);
                editGameValues.putInt("maxratio", 4);
                editGameValues.putInt("enemyDirection",4);
                editGameValues.putInt("powerUpSpawn", 500);
                break;
            case 3:
                difficultySetting.setText(difficultyInsane);
                editGameValues.putInt("minDimension",50);
                editGameValues.putInt("difficulty", 50);
                editGameValues.putInt("maxratio", 4);
                editGameValues.putInt("enemyDirection",4);
                editGameValues.putInt("powerUpSpawn", 300);
                break;

        }
    }

    protected void setColor(int playerIndex) {
        switch(playerIndex){
            case 0:
                playerColorText.setTextColor(Color.GREEN);
                break;
            case 1:
                playerColorText.setTextColor(Color.GRAY);
                break;
            case 2:
                playerColorText.setTextColor(Color.YELLOW);
                break;
            case 3:
                playerColorText.setTextColor(Color.CYAN);
                break;
            case 4:
                playerColorText.setTextColor(Color.MAGENTA);
                break;
            case 5:
                playerColorText.setTextColor(Color.BLUE);
                break;
            case 6:
                playerColorText.setTextColor(Color.rgb(138,43,226));
                break;
            case 7:
                playerColorText.setTextColor(Color.rgb(255,105,180));
                break;
            case 8:
                playerColorText.setTextColor(Color.rgb(255,140,0));
                break;
            case 9:
                playerColorText.setTextColor(Color.rgb(255,215,0));
                break;
            case 10:
                playerColorText.setTextColor(Color.WHITE);
                break;
        }
    }
}