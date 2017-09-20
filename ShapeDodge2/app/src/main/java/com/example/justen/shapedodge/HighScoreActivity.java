package com.example.justen.shapedodge;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/17/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Screen which stores and displays highscores
        -- Rank order based on highscore
		--
		--
----------------------------------------------------------------------------------*/

public class HighScoreActivity extends AppCompatActivity
{

    public static SharedPreferences gameValues;
    public static SharedPreferences.Editor editGameValues;

    static Map<String, Integer> userScore = new HashMap<String, Integer>();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        context = HighScoreActivity.this;

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);

        gameValues = getSharedPreferences("theGameValues", Context.MODE_PRIVATE);
        editGameValues = gameValues.edit();


        Map<String, Integer> map = new HashMap<String, Integer>();

        for(Map.Entry entry : gameValues.getAll().entrySet())
        {
            map.put(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
        }

        Object[] objs = map.entrySet().toArray();

        Arrays.sort(objs, new Comparator()
                {
                    public int compare(Object o1, Object o2)
                    {
                        return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String,Integer>)o1).getValue());
                    }
                }
        );



        int count = 1;

        for(Object ent : objs)
        {
            TableRow row = new TableRow(this);

            int rank = count;
            String user = ((Map.Entry<String, Integer>) ent).getKey();
            int score = ((Map.Entry<String,Integer>) ent).getValue();

            TextView scoreView = new TextView(this);
            scoreView.setText(""+score);
            scoreView.setTextColor(Color.WHITE);
            TextView rankView = new TextView(this);
            rankView.setText(""+rank);
            rankView.setGravity(Gravity.CENTER);
            rankView.setTextColor(Color.WHITE);
            TextView userView = new TextView(this);
            userView.setText(""+user);
            userView.setTextColor(Color.WHITE);

            row.addView(rankView);
            row.addView(userView);
            row.addView(scoreView);
            table.addView(row);

            count++;

            if(count >= 14)
            {
                break;
            }
        }


        Button MainMenuButton = (Button) findViewById(R.id.mainMenuBtn);

        MainMenuButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                HighScoreActivity.super.onBackPressed();
            }
        });

        Button clearBoardButton = (Button) findViewById(R.id.clearBoard);

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        editGameValues.clear();
                        editGameValues.commit();
                        userScore.clear();
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };


        clearBoardButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();

            }
        });

        updateData();
    }

    public static void updateData()
    {
        for(Map.Entry entry : userScore.entrySet())
        {
            editGameValues.putString(entry.getKey().toString(), entry.getValue().toString());
            editGameValues.commit();
        }
    }
}
