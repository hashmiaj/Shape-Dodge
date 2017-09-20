package com.example.justen.shapedodge;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
        -- Screen is responsible for traversing between menus
        -- can access the game, high scores, settings and exit
		--
		--
----------------------------------------------------------------------------------*/

public class MainMenu extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button HighScoreButton = (Button)findViewById(R.id.highscoreButton);

         HighScoreButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                startActivity(new Intent(MainMenu.this, HighScoreActivity.class));
            }

        });



        Button SettingsButton = (Button) findViewById(R.id.settingsButton);

        SettingsButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                startActivity(new Intent(MainMenu.this, SettingsActivity.class));
            }
        });


        Button PlayButton = (Button) findViewById(R.id.playButton);

        PlayButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                startActivity(new Intent(MainMenu.this, GameActivity.class));
            }
        });

        Button howToPlayButton = (Button) findViewById(R.id.instructionsButton);

        howToPlayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                startActivity(new Intent(MainMenu.this, Instructions.class));
            }
        });

    }
}
