package com.example.justen.shapedodge;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.justen.shapedodge.SettingsActivity.maxIndex;
/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/28/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Controls the GameView responsible for communication between
        -- classes of the game.
		--
		--
----------------------------------------------------------------------------------*/

public class GameView extends SurfaceView implements Runnable{

    private Thread runner;
    private int score;
    private int finalScore;
    private Button submitBtn;
    private TextView scoreView;
    private EditText userNameText;
    private String userName;

    private int lives;
    public static Player player;
    private int colorIndex;
    private int minDimension;
    private int direction;
    private int maxratio;
    private ShapeEnemyControl enemyControl;
    private Context context;

    private Paint textStyle;
    private Paint countStyle;
    private Canvas textCanvas;
    private Canvas gameCanvas;
    private SurfaceHolder gameViewSurfaceHolder;
    private SurfaceHolder textSurfaceHolder;

    private JoystickView joystick;
    private SurfaceView scoreText;
    private SurfaceView livesText;
    private int textWidth;
    private int textHeight;
    public static int gameHeight;
    public static int gameWidth;
    private int difficulty;
    private int powerSpawn;

    private SharedPreferences.Editor editGameValues;

    private int counter;
    private int secondsCounter;

    volatile boolean isPlaying;
    volatile boolean isGameOver = false;
    volatile boolean isCountdown;

//      Constructors
    public GameView(Context context){super(context);this.init();}
    public GameView(Context context, AttributeSet attributes){super(context,attributes);this.init();}
//      Functional Methods
    //This method initializes saved data values.
    public void setGameValues(SharedPreferences gameValues, SharedPreferences.Editor editGameValues){
        this.colorIndex = gameValues.getInt("colorIndex",0);
        setDifficulty(gameValues.getInt("difficulty",100));
        this.minDimension=gameValues.getInt("minDimension",10);
        this.maxratio=gameValues.getInt("maxratio",6);
        this.direction=gameValues.getInt("enemyDirection",2);
        this.powerSpawn=gameValues.getInt("powerUpSpawn",1000);
        this.editGameValues=editGameValues;
    }
    //This method is called to initialize the game
    public void init(){
        runner=null;
        isCountdown=true;
        this.setScore(0);
        this.setLives(3);
        textStyle = new Paint();
        textStyle.setColor(Color.WHITE);
        textStyle.setTextSize(50);
        textStyle.setTextAlign(Paint.Align.CENTER);
        countStyle = new Paint();
        countStyle.setColor(Color.BLUE);
        countStyle.setTextSize(750);
        countStyle.setTextAlign(Paint.Align.CENTER);
        this.setDifficulty(difficulty);
        this.setUpCount();
    }
    //This method is called when the game is restarted
    public void restart(){
        this.setScore(0);
        this.setLives(3);
        this.setDifficulty(difficulty);
        this.setUpCount();
        player.center();
        enemyControl.removeAll();
        enemyControl.removePowerUps();
        isGameOver=false;
    }
    //This method is called to start the main game thread
    public void start(){
        // Centers player on screen
        player = new Player(gameWidth, gameHeight, colorIndex);
        player.center();
        enemyControl = new ShapeEnemyControl(gameWidth,gameHeight,maxratio,direction,minDimension);
        if(runner==null) {
            runner = new Thread(this);
        }
        runner.start();
    }
    //This method is called when the game is stopped.....
    public void stop(){
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onStateChange();
                }
            });
    }
    //This method is called when the game is resumed.
    public void resume(){
        this.setUpCount();
        isCountdown=true;
        if(!(runner.isAlive())) {
            runner=new Thread(this);
            runner.start();
        }
    }
    //This method is called when the game is paused.
    public void pause(){
        isPlaying=false;
        isCountdown=false;
        try {
            runner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    //Main Thread run method which is repeatedly called all the TIME
    //Will be called to update the coordinates, paint new coordinated objects, and control the sleep of the game thread
    public void run(){
        while (isCountdown){
            this.displayCount(this,counter);
            this.updateCount();
            this.threadControl();
        }
        while(isPlaying) {
            this.updateCoordinates();
            this.paint();
            if(!isGameOver) {
                this.setScore(this.getScore() + 1);//THIS SHOULD BE LAST and will remain in run method
            }this.threadControl();
        }if(isGameOver){
            this.stop();
        }
    }
    //This method prepares the counter @ restart, initialization, resume
    public void setUpCount(){
        this.counter=3;
        this.secondsCounter=0;
    }
    //This method counts the seconds past and updates the countdown
    public void updateCount(){
        if(secondsCounter==50){
            counter--;
            secondsCounter=0;
        }if(counter==0){
            isCountdown=false;
            isPlaying=true;
        }
        secondsCounter++;
    }
    //This method is called repeatedly to update ALL coordinates.
    public void updateCoordinates(){
        if(!enemyControl.getIsFrozen()) {
            enemyControl.moveAll();
        }
        enemyControl.updatePowerCounter();
        enemyControl.checkOff();
        player.move(joystick);
        player.checkBounds();
        this.increaseDifficulty();
        if(enemyControl.checkPowerUpHit(player.getxPos(),player.getyPos())==2){
            lives++;
        }
        if(!enemyControl.getIsFrozen()) {
            if (enemyControl.checkHit(player.getxPos(), player.getyPos())) {
                enemyControl.removeAll();
                enemyControl.removePowerUps();
                lives--;
            }
        }
        if(lives==0) {
            isGameOver = true;
            isPlaying = false;
            finalScore = this.getScore();
            unlockColors();
        }
    }

    //This method is called to paint ALL objects of the game to the screen
    public void paint(){
        //Responsible for displaying text in corresponding surface views
        this.displayText(scoreText,getScore());
        this.displayText(livesText,getLives());

        //Below this statement deals with gameScreen
        gameViewSurfaceHolder=this.getHolder();
        if(gameViewSurfaceHolder.getSurface().isValid()) {
            gameCanvas = gameViewSurfaceHolder.lockCanvas();
            gameCanvas.drawColor(Color.BLACK);
            player.paint(gameCanvas);
            enemyControl.paint(gameCanvas);
            gameViewSurfaceHolder.unlockCanvasAndPost(gameCanvas);
        }
    }
    //This method is called to control the FPS 17mili ~ 60fps
    public void threadControl(){
        try {
            runner.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //This method is called to display the integer in the corresponding surface view
    public void displayText(SurfaceView text, int integer){
        textSurfaceHolder=text.getHolder();
        if(textSurfaceHolder.getSurface().isValid()){
            textCanvas=textSurfaceHolder.lockCanvas();
            textCanvas.drawColor(Color.BLACK);
            textWidth=textCanvas.getWidth();
            textHeight=textCanvas.getHeight();
            textCanvas.drawText(Integer.toString(integer),textWidth/2,(int) ((textHeight / 2) - ((textStyle.descent() + textStyle.ascent()) / 2)),textStyle);
            textSurfaceHolder.unlockCanvasAndPost(textCanvas);
        }
    }
    //This method displays the counter when, restarted/initialized/resumed
    public void displayCount(SurfaceView text, int integer){
        textSurfaceHolder=text.getHolder();
        if(textSurfaceHolder.getSurface().isValid()){
            textCanvas=textSurfaceHolder.lockCanvas();
            textCanvas.drawColor(Color.BLACK);
            textWidth=textCanvas.getWidth();
            textHeight=textCanvas.getHeight();
            textCanvas.drawText(Integer.toString(integer),textWidth/2,(int) ((textHeight / 2) - ((countStyle.descent() + countStyle.ascent()) / 2)),countStyle);
            textSurfaceHolder.unlockCanvasAndPost(textCanvas);
        }
    }
    //This method gradually increases difficulty over score count.
    public void increaseDifficulty(){
        if ((this.score % this.difficulty) == 0){
            if(!enemyControl.getIsFrozen()) {
                enemyControl.addEnemy();
            }
        }
        if((getScore()%500)==0) {
            setDifficulty(this.difficulty - 10);
            if (getDifficulty() < 15) {
                setDifficulty(15);
            }
        }if((this.score + 50)% powerSpawn == 0){
            enemyControl.addPowerUp();
        }
    }
    //This method is responsible for change state of isGameOver
    public void onStateChange() {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.game_over);
            dialog.show();
            final ImageView backgroundOne = (ImageView) dialog.findViewById(R.id.imageView);
            final ImageView backgroundTwo = (ImageView) dialog.findViewById(R.id.imageView2);

            final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
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

        scoreView = (TextView) dialog.findViewById(R.id.finalScore);
        if(unlockColors()){
            scoreView.setText("Final Score: " + finalScore +"\nNew Color Unlocked!");
        }else{
            scoreView.setText("Final Score: " + finalScore);
        }
        submitBtn = (Button) dialog.findViewById(R.id.submitButton);
        userNameText = (EditText) dialog.findViewById(R.id.userName);


        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                userName = userNameText.getText().toString();
                HighScoreActivity.userScore.put(userName, finalScore);
                HighScoreActivity.updateData();
                dialog.dismiss();
                Activity gameactivity = (Activity) context;
                gameactivity.onBackPressed();
            }
        });

    }
    //This method unlocks additional skins based on score;
    public boolean unlockColors(){
        boolean isUnlocked = false;
        if(finalScore>500){
            if(maxIndex < 6){
                editGameValues.putInt("maxIndex",6);
                editGameValues.commit();
                isUnlocked = true;
            }
        }if(finalScore>1000){
            if(maxIndex < 7){
                editGameValues.putInt("maxIndex",7);
                editGameValues.commit();
                isUnlocked = true;
            }
        }if(finalScore>5000){
            if(maxIndex < 8){
                editGameValues.putInt("maxIndex",8);
                editGameValues.commit();
                isUnlocked = true;
            }
        }if(finalScore>10000){
            if(maxIndex < 9){
                editGameValues.putInt("maxIndex",9);
                editGameValues.commit();
                isUnlocked = true;
            }
        }if(finalScore>25000){
            if(maxIndex < 10){
                editGameValues.putInt("maxIndex",10);
                editGameValues.commit();
                isUnlocked = true;
            }
        }if(finalScore>30000){
            if(maxIndex < 11){
                editGameValues.putInt("maxIndex",11);
                editGameValues.commit();
                isUnlocked = true;
            }
        }
            return isUnlocked;
    }


//  Getters and Setters
    public void setContext(Context c){this.context=c;}
    public int getScore(){return score;    }
    public void setScore(int score){
        this.score=score;
    }
    public int getLives(){ return lives; }
    public void setLives(int lives) { this.lives=lives; }
    public int getDifficulty(){return difficulty;}
    public void setDifficulty(int difficulty){this.difficulty=difficulty;}
    public void setScoreText(SurfaceView view){scoreText=view;}
    public void setLivesText(SurfaceView view){livesText=view;}
    public void setJoyStick(JoystickView js){joystick=js;}
    public void setGameHeight(int height){gameHeight=height;}
    public void setGameWidth (int width){gameWidth=width;}

}
