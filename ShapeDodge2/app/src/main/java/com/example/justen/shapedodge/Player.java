package com.example.justen.shapedodge;


/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/28/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Controls the player/user parameters
        --
		--
		--
----------------------------------------------------------------------------------*/

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player {
    private int xPos;
    private int yPos;
    private int xSpeed;
    private int ySpeed;
    private int maxX;
    private int maxY;
    private final int notMoving = 0;
    private Paint playerStyle;
    private int playerSize = 20;
    private final int [] playerColor = {Color.GREEN, Color.GRAY, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.rgb(138,43,226), Color.rgb(255,105,180), Color.rgb(255,140,0), Color.rgb(255,215,0), Color.WHITE }; // Custom colors: Color.BLUE
    private int colorIndex;

    public Player(int gameWidth, int gameHeight,int index){
        maxX = gameWidth;
        maxY = gameHeight;
        colorIndex = index;
        this.setxSpeed(notMoving);
        this.setySpeed(notMoving);
        playerStyle=new Paint();
        playerStyle.setColor(playerColor[colorIndex]);

    }

    public void center(){
        this.setxPos(maxX/2);
        this.setyPos(maxY/2);
    }


    public void move(JoystickView joystick){

        int angle = joystick.getAngle();
        int power = joystick.getPower();

// As long as the joystick is moving, player will move U,D,L,R based off angle
        // otherwise, don't move player
        if (power != 0) {

            if (angle > -45 && angle < 45) {
                xSpeed = notMoving;
                ySpeed = -2;
            }
            if (angle > 45 && angle < 135) {
                ySpeed = notMoving;
                xSpeed = 2;
            }
            if (angle > 135 || angle < -135) {
                ySpeed = 2;
                xSpeed = notMoving;
            }

            if (angle > -135 && angle < -45) {
                ySpeed = notMoving;
                xSpeed = -2;
            }
        }

        else {
            xSpeed = notMoving;
            ySpeed = notMoving;
        }
        //updates position based off speed
        xPos += xSpeed *(power/15);
        yPos += ySpeed *(power/15);
    }

    //This method is used to determine if the player is within the bounds
    public void checkBounds (){
        if(xPos >= (maxX-playerSize)){
            xPos = (maxX-playerSize);}

        if(xPos <= playerSize){
            xPos = playerSize;}

        if(yPos >= maxY-playerSize){
            yPos = maxY-playerSize;}

        if(yPos <= playerSize){
            yPos = playerSize;}

    }

    public void paint(Canvas canvas){
       canvas.drawCircle(xPos, yPos ,playerSize ,playerStyle);
    }

    public int getxPos() { return this.xPos;}
    public int getyPos() { return this.yPos;}
    public void setxPos(int x){this.xPos = x;}
    public void setyPos(int y){this.yPos = y;}
    public void setxSpeed(int x){this.xSpeed = x;}
    public void setySpeed(int y){this.ySpeed = y;}


}
