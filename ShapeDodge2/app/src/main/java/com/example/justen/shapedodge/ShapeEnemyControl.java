package com.example.justen.shapedodge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/28/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Controls every ShapeEnemy object on the gameView.
        --
		--
		--
----------------------------------------------------------------------------------*/

public class ShapeEnemyControl {
    private ArrayList<ShapeEnemy> enemies;
    private ArrayList<ShapeEnemy> powerUps;
    private final int freeze = 0;
    private final int clear = 1;
    private final int life = 2;
    private final int [] powerColor = {Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA,Color.rgb(255,153,0)}; // Custom colors: Color.rgb(255,255,255)=white
    private int maxX;
    private int maxY;
    private int minEnemyDimension = 20; //THIS COULD BE DIFFICULTY ALTERED
    private int directionValue;
    private int maxRatio;
    private Paint enemyStyle;
    private Paint powerStyle;
    private int colorIndex = 0;
    private int powerCounter = 0;
    private boolean isFrozen;
    public int powerIndex = 0;


    public ShapeEnemyControl(int maxiX, int maxiY, int ratio, int dir, int dim){
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        maxX=maxiX;
        maxY=maxiY;
        enemyStyle = new Paint();
        enemyStyle.setColor(Color.RED);
        powerStyle = new Paint();
        directionValue=dir;
        maxRatio=ratio;
        minEnemyDimension=dim;
    }

    public void paint(Canvas canvas){
        ShapeEnemy enemy;
        for (int i = 0; i < enemies.size(); i++){
            enemy = enemies.get(i);
            canvas.drawRect(enemy.getX(),enemy.getY(),
                    (enemy.getX()+enemy.getWidth()),
                    (enemy.getY()+enemy.getHeight()),enemyStyle);
        }
        colorIndex%=6;
        powerStyle.setColor(powerColor[colorIndex]);
        for(int i = 0; i < powerUps.size(); i++){
            enemy = powerUps.get(i);
            canvas.drawRect(enemy.getX(),enemy.getY(),
                    (enemy.getX()+enemy.getWidth()),
                    (enemy.getY()+enemy.getHeight()),powerStyle);
        }
        colorIndex++;
    }

    public void checkOff(){
        ShapeEnemy enemy;
        for (int i = 0; i < enemies.size(); i++){
            enemy = enemies.get(i);
            if(enemy.isMovingLeft()){
                if(enemy.getX()+enemy.getWidth()<0){
                    enemies.remove(enemy);
                }
            }else if(enemy.isMovingRight()){
                if(enemy.getX()>maxX){
                    enemies.remove(enemy);
                }
            }else if(enemy.isMovingDown()){
                if(enemy.getY()>maxY){
                    enemies.remove(enemy);
                }
            }else if(enemy.isMovingUp()){
                if(enemy.getY()+enemy.getHeight()<0){
                    enemies.remove(enemy);
                }
            }
        }
    }

    public void addEnemy(){
        int randX = (int) (Math.random()*(maxX+1));
        int randY = (int) (Math.random()*(maxY+1));
        int randWidth = (int) (((Math.random()*(maxX+1))/maxRatio)+minEnemyDimension);
        int randHeight = (int) (((Math.random()*(maxY+1))/maxRatio)+minEnemyDimension);
        ShapeEnemy enemy = new ShapeEnemy(randX,randY,randWidth,randHeight);
        int direction = (int) (Math.random()*directionValue);
        if(direction==0){
            enemy.goDown();
            enemy.setY(0-enemy.getHeight());
            enemy.setX(Math.abs(enemy.getX()-enemy.getWidth()));
        }else if(direction==1){
            enemy.goUp();
            enemy.setY(maxY);
            enemy.setX(Math.abs(enemy.getX()-enemy.getWidth()));
        }else if(direction==2){
            enemy.goLeft();
            enemy.setX(maxX);
            enemy.setY(Math.abs(enemy.getY()-enemy.getHeight()));
        }else{
            enemy.goRight();
            enemy.setX(0-enemy.getWidth());
            enemy.setY(Math.abs(enemy.getY()-enemy.getHeight()));
        }
        enemies.add(enemy);
    }
    public void addPowerUp(){
        int randX = (int) (Math.random()*(maxX+1));
        int randY = (int) (Math.random()*(maxY+1));
        ShapeEnemy power = new ShapeEnemy(randX,randY,20,20);
        int powerUpType =  powerIndex; //(int) (Math.random()*3);
        if(powerUpType==freeze){
            power.setPowerUp(freeze);
        }else if(powerUpType==life){
            power.setPowerUp(life);
        }else{
            power.setPowerUp(clear);
        }
        powerUps.add(power);
        powerIndex++;
        if(powerIndex > 3)
        {
            powerIndex = 0;
        }
    }

    public void removeAll(){
        enemies= new ArrayList<>();
    }
    public void removePowerUps(){
        powerUps = new ArrayList<>();
    }
    public void moveAll(){
        ShapeEnemy enemy;
        for (int i = 0; i < enemies.size(); i++)
        {
            enemy=enemies.get(i);
            enemy.move();
        }
    }

    public boolean checkHit(int x, int y){
        //check if the player has hit an enemy.
        //returns true if it has.

        boolean betweenY=false; //boolean variable that is responsible for y coordinate collision
        boolean betweenX=false;	//boolean variable that is responsible for x coordinate collision
        ShapeEnemy e;
        boolean hit=false;		//boolean variable that is responsible for collision detection
        for(int i=0; i<enemies.size(); i++){//for i = 0 to i=current number of enemies drawn,.....
            e=enemies.get(i);	//set e to the current referenced ShapeEnemy from the arraylist
            betweenX=(x>e.getX()&&x<e.getWidth()+e.getX());   //compares player x coordinate with current shape enemy. if within range, betweenX =true.
            betweenY=(y>e.getY()&&y<e.getY()+e.getHeight());  //compares player y coordinate with current shape enemy. if within range, betweenY =true.
            if(betweenX&&betweenY){ //if both boolean statements are true, then the player is inside an ShapeEnemy
                hit=true;			 //which means the player is hit = true.
            }
        }
        return hit;	//return the boolean value.
    }

    public int checkPowerUpHit(int x, int y){
        boolean betweenY = false;
        boolean betweenX = false;
        ShapeEnemy p;
        int powerType = -1;
        for(int i=0; i<powerUps.size(); i++) {
            p = powerUps.get(i);
            for(int j = 0; j < 20; j++){
                if((x-j > p.getX() && x-j < p.getWidth() + p.getX())){
                    betweenX=true;
                    break;
                }if ((x+j > p.getX() && x+j < p.getWidth() + p.getX())){
                    betweenX=true;
                    break;
                }
            }
            for(int j =0; j<20; j++){
                if((y-j > p.getY() && y-j < p.getHeight() + p.getY())){
                    betweenY=true;
                    break;
                }if ((y+j > p.getY() && y+j < p.getHeight() + p.getY())){
                    betweenY=true;
                    break;
                }
            }
            if(betweenX&&betweenY){
                if (p.getPowerUp()==freeze){
                    powerUps.remove(p);
                    isFrozen=true;
                    powerCounter=0;
                    powerType = freeze;
                }else if(p.getPowerUp()==life){
                    powerUps.remove(p);
                    powerType = life;
                }else{
                    powerUps.remove(p);
                    this.removeAll();
                    powerType = clear;
                }
            }
        }
        return powerType;
    }

    public void updatePowerCounter(){
        powerCounter++;
        if (powerCounter >= 100){
            isFrozen = false;
        }
    }

    public boolean getIsFrozen(){
        return isFrozen;
    }
}
