package com.example.justen.shapedodge;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/17/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Responsible for pausing the game with additional options
        -- Can restart the game, quit the game, change music, or resume the game
		--
		--
----------------------------------------------------------------------------------*/

public class PauseActivity extends AppCompatActivity {

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        final ImageView backgroundOne = (ImageView) findViewById(R.id.imageView);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.imageView2);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

        Button resumeButton = (Button) findViewById(R.id.resumeButton);
        Button quitButton = (Button) findViewById(R.id.quitButton);

        resumeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(PauseActivity.this, MainMenu.class));
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
    */
}
