package com.example.justen.shapedodge;


/*----------------------------------------------------------------------------------
        -- Name: Team 12
        -- CMSC 355 Spring 2017

        -- Create Date: 03/28/2017
        -- Project Name: Shape Dodge Team 12

        -- Description:
        -- Controls individual ShapeEnemy parameters.
        --
		--
		--
----------------------------------------------------------------------------------*/
public class ShapeEnemy {
    private int xPos;
    private int yPos;
    private int eWidth;
    private int eHeight;
    private int powerUp;

    boolean movingRight = false;
    boolean movingLeft = false;
    boolean movingUp = false;
    boolean movingDown = false;

    public ShapeEnemy(int x, int y, int w, int h){
        xPos=x;
        yPos=y;
        eWidth=w;
        eHeight=h;
    }

    public void move(){
        if(isMovingRight()){
            setX(this.xPos+4);
        }else if(isMovingLeft()){
            setX(this.xPos-4);
        }else if(isMovingDown()){
            setY(this.yPos+4);
        }else if(isMovingUp()){
            setY(this.yPos-4);
        }else{
            setX(this.xPos);
            setY(this.yPos);
        }
    }

    public int getX(){return xPos;}
    public int getY(){return yPos;}
    public int getWidth(){return eWidth;}
    public int getHeight(){return eHeight;}

    public void setX(int x){xPos=x;}
    public void setY(int y){yPos=y;}

    public void goUp(){movingUp=true;}
    public void goDown(){movingDown=true;}
    public void goLeft(){movingLeft=true;}
    public void goRight(){movingRight=true;}

    public boolean isMovingRight(){return movingRight;}
    public boolean isMovingUp(){return movingUp;}
    public boolean isMovingDown(){return movingDown;}
    public boolean isMovingLeft(){return movingLeft;}

    public void setPowerUp(int p){powerUp=p;}
    public int getPowerUp(){return powerUp;}

}
