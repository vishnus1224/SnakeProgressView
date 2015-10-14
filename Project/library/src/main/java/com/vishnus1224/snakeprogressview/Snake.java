package com.vishnus1224.snakeprogressview;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 10/10/2015.
 */
class Snake implements ScreenObject{

    /**
     * Represents the current direction the snake is moving in.
     */
    public enum Direction{
        UP, LEFT, RIGHT, DOWN
    }

    /**
     * Represents the current state of the snake.
     */
    public enum State{
        IDLE, MOVING
    }

    /**
     * Specifies the current movement direction. Default is left.
     */
    private Direction movementDirection = Direction.LEFT;

    /**
     * Specifies the current state the snake is currently in. Default is idle.
     */
    private State currentState = State.IDLE;

    /**
     * Holds all parts which comprises the snake.
     */
    private List<SnakePart> snakeParts;

    /**
     * The number of parts to build the snake with.
     */
    private int numberOfParts;

    /**
     * Color of the snake.
     */
    private int snakeColor;


    public Snake(int numberOfParts, int snakeColor) {
        this.numberOfParts = numberOfParts;
        this.snakeColor = snakeColor;
        initialize();
    }

    public Direction getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(Direction movementDirection) {
        this.movementDirection = movementDirection;
    }

    public List<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setSnakeParts(List<SnakePart> snakeParts) {
        this.snakeParts = snakeParts;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public void initialize() {
        snakeParts = new ArrayList<>();

        //set starting positions for the snake parts.
        //lay the snake out horizontally.

        int xPosition = Utils.WORLD_WIDTH % 2 == 0 ? ((Utils.WORLD_WIDTH / 2) - 1): (Utils.WORLD_WIDTH / 2);
        int yPosition = Utils.WORLD_HEIGHT % 2 == 0 ? ((Utils.WORLD_HEIGHT / 2) - 1): (Utils.WORLD_HEIGHT / 2);

        for(int i = 0; i < numberOfParts; i++, xPosition++){
            SnakePart part = new SnakePart(xPosition, yPosition, snakeColor);
            snakeParts.add(i, part);
        }
    }

    @Override
    public void draw(Canvas canvas) {

        //draw each part of the snake.
        for(int i = 0; i < numberOfParts; i++){
            SnakePart part = snakeParts.get(i);
            part.draw(canvas);
        }
    }

    /**
     * Move the snake forward every update.
     */
    public void advance(){

        if(currentState == State.IDLE){
            currentState = State.MOVING;
        }

        SnakePart head = snakeParts.get(0);

        int snakeLength = snakeParts.size() - 1;

        for(int i = snakeLength; i > 0; i--){
            SnakePart previousPart = snakeParts.get(i - 1);
            SnakePart snakePart = snakeParts.get(i);
            //set the part's new position to the previous parts position.
            snakePart.setX(previousPart.getX());
            snakePart.setY(previousPart.getY());
        }

        switch (movementDirection){
            case UP:
                head.setY(head.getY() - 1);
                break;
            case DOWN:
                head.setY(head.getY() + 1);
                break;
            case LEFT:
                head.setX(head.getX() - 1);
                break;
            case RIGHT:
                head.setX(head.getX() + 1);
                break;
        }
    }

    /**
     * Move to the left.
     * If the snake if already moving in the same direction or wants to move in the reverse direction, then return.
     */
    public void moveLeft(){
        if(movementDirection == Direction.LEFT || movementDirection == Direction.RIGHT){
            return;
        }
        movementDirection = Direction.LEFT;
    }

    /**
     * Move to the right.
     * If the snake if already moving in the same direction or wants to move in the reverse direction, then return.
     */
    public void moveRight(){
        if(movementDirection == Direction.RIGHT || movementDirection == Direction.LEFT){
            return;
        }
        movementDirection = Direction.RIGHT;
    }

    /**
     * Move up.
     * If the snake if already moving in the same direction or wants to move in the reverse direction, then return.
     */
    public void moveUp(){
        if(movementDirection == Direction.UP || movementDirection == Direction.DOWN){
            return;
        }
        movementDirection = Direction.UP;
    }

    /**
     * Move down.
     * If the snake if already moving in the same direction or wants to move in the reverse direction, then return.
     */
    public void moveDown(){
        if(movementDirection == Direction.DOWN || movementDirection == Direction.UP){
            return;
        }
        movementDirection = Direction.DOWN;
    }

    /**
     * Adds a new part to the end of the snake.
     */
    public void addPart(){

        numberOfParts++;

        SnakePart tailPart = snakeParts.get(snakeParts.size() - 1);
        SnakePart newTailPart = new SnakePart(tailPart.getX(), tailPart.getY(), snakeColor);
        snakeParts.add(newTailPart);
    }

    /**
     * Checks whether any parts overlap.
     * @return true if parts overlap, false otherwise.
     */
    public boolean checkGameOver(){

        int snakeLength = snakeParts.size();

        SnakePart head = snakeParts.get(0);

        for(int i = 1; i < snakeLength; i++){
            SnakePart snakePart = snakeParts.get(i);
            if(head.getX() == snakePart.getX() && head.getY() == snakePart.getY()){
                return true;
            }
        }

        //check boundary conditions
        if(head.getX() < 0 || head.getX() >= Utils.WORLD_WIDTH){
            //check x coordinates.
            return true;
        }else if(head.getY() < 0 || head.getY() >= Utils.WORLD_HEIGHT){
            //check y coordinates.
            return true;
        }

        return false;
    }
}
