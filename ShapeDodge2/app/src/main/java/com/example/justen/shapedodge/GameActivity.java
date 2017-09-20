package com.example.justen.shapedodge;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/17/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Main Screen for the actual game
        -- Controls Communication between the game, joystick and HUD
		--
		--
----------------------------------------------------------------------------------*/

public class GameActivity extends AppCompatActivity{
    private SurfaceView score;
    private SurfaceView lives;
    private JoystickView joystick;
    private GameView gameScreen;
    private ImageButton pauseButton;
    private Button resumeButton;
    private Button quitButton;
    private Button restartButton;
    private Button soundSettingsButton;
    final Context context = this;
    private volatile boolean playMusic;
    MediaPlayer gameMusic;

    private SharedPreferences gameValues;
    private SharedPreferences.Editor editGameValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameValues = getSharedPreferences("gameValues", Context.MODE_PRIVATE);
        editGameValues = gameValues.edit();
        gameMusic = MediaPlayer.create(context, R.raw.gamemusic);
        gameMusic.setLooping(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        joystick = (JoystickView) findViewById(R.id.joystickView);
        score = (SurfaceView) findViewById(R.id.inScore);
        lives = (SurfaceView) findViewById(R.id.lives);

        gameScreen = (GameView) findViewById(R.id.gameView);
        gameScreen.setScoreText(score);
        gameScreen.setLivesText(lives);
        gameScreen.setJoyStick(joystick);
        gameScreen.setContext(context);
        gameScreen.setGameValues(gameValues,editGameValues);
        playMusic=gameValues.getBoolean("playMusic", true);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        if(playMusic) {
            gameMusic.start();
        }
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gameScreen.pause();
                if(playMusic) {
                    gameMusic.pause();
                }
                final Dialog pauseDialog = new Dialog(context);
                pauseDialog.setContentView(R.layout.activity_pause);
                pauseDialog.show();
                final ImageView backgroundOne = (ImageView) pauseDialog.findViewById(R.id.imageView);
                final ImageView backgroundTwo = (ImageView) pauseDialog.findViewById(R.id.imageView2);

                final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

                resumeButton = (Button) pauseDialog.findViewById(R.id.resumeButton);
                quitButton = (Button) pauseDialog.findViewById(R.id.quitButton);
                restartButton = (Button) pauseDialog.findViewById(R.id.restartButton);
                soundSettingsButton = (Button) pauseDialog.findViewById(R.id.soundSettingsButton);

                resumeButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        animator.end();
                        pauseDialog.dismiss();
                        gameScreen.resume();
                        if(playMusic) {
                          gameMusic.start();
                        }
                    }
                });
                quitButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        animator.end();
                        if(playMusic) {
                          gameMusic.release();
                        }
                        GameActivity.super.onBackPressed();

                    }

                });
                restartButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        animator.end();
                        pauseDialog.dismiss();
                        if(playMusic) {
                          gameMusic.start();
                        }
                        gameScreen.setGameValues(gameValues,editGameValues);
                        gameScreen.restart();
                        gameScreen.resume();
                    }
                });

                soundSettingsButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        playMusic=gameValues.getBoolean("playMusic", true);
                        playMusic=!playMusic;
                        editGameValues.putBoolean("playMusic",playMusic);
                        editGameValues.commit();
                        if(playMusic){
                            soundSettingsButton.setText("Music: On");
                        }else{
                            soundSettingsButton.setText("Music: Off");
                            gameMusic.release();
                        }
                    }
                });
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(10000L);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        final float progress = (float) animation.getAnimatedValue();
                        final float width = backgroundOne.getWidth();
                        final float translationX = width * progress;
                        backgroundOne.setTranslationX(translationX);
                        backgroundTwo.setTranslationX(translationX - width);
                    }
                });
                animator.start();
            }
        });
        ViewTreeObserver vto = gameScreen.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = gameScreen.getViewTreeObserver();
                gameScreen.setGameHeight(gameScreen.getHeight());
                gameScreen.setGameWidth(gameScreen.getWidth());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                    obs.removeOnGlobalLayoutListener(this);
                }
                else{
                    obs.removeOnGlobalLayoutListener(this);
                }
                gameScreen.start();
            }
        });
    };

    public void onBackPressed() {
                        if(playMusic) {
                          gameMusic.release();
                        }
        gameScreen.pause();
        gameScreen.restart();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        gameScreen.pause();
        super.onPause();
    }

}
