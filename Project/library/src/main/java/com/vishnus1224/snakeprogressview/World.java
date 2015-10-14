package com.vishnus1224.snakeprogressview;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by Vishnu on 10/10/2015.
 * Represents the world used to render and interact with the snake.
 * The world is represented in its own coordinate system as a grid of squares each having a size of 30 pixels.
 */
class World implements ScreenObject {

    /**
     * State of the world.
     */
    public enum WorldState{
        IDLE, PLAYING
    }

    /**
     * Represents the current state of the world.
     */
    private WorldState worldState = WorldState.IDLE;

    /**
     * Reference to the snake object.
     */
    private Snake snake;

    /**
     * Reference to the item object.
     */
    private Item item;

    /**
     * Flag to indicate if game is over.
     */
    private boolean gameOver;

    /**
     * 2-D array to check the position at which snake parts are so that item is not placed at that position.
     */
    private boolean[][] worldGrid = new boolean[Utils.WORLD_WIDTH][Utils.WORLD_HEIGHT];

    /**
     * To generate a random number.
     */
    private Random random = new Random();

    /**
     * Color of the snake.
     */
    private int snakeColor;

    /**
     * Color of the item.
     */
    private int itemColor;

    public Snake getSnake() {
        return snake;
    }

    public WorldState getWorldState() {
        return worldState;
    }

    public void setWorldState(WorldState worldState) {
        this.worldState = worldState;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public World(int snakeColor, int itemColor){
        this.snakeColor = snakeColor;
        this.itemColor = itemColor;
        initialize();
    }

    @Override
    public void initialize() {
        snake = new Snake(3, snakeColor);
        placeItem();
    }


    @Override
    public void draw(Canvas canvas) {
        snake.draw(canvas);
        item.draw(canvas);

    }

    public void update(){

        if(gameOver){
            return;
        }

        snake.advance();

        if(snake.checkGameOver()){
            gameOver = true;
            return;
        }

        SnakePart head = snake.getSnakeParts().get(0);
        if(head.getX() == item.getX() && head.getY() == item.getY()){
            snake.addPart();

            if(snake.getSnakeParts().size() == Utils.WORLD_WIDTH * Utils.WORLD_HEIGHT){
                gameOver = true;
                return;
            }else {
                placeItem();
            }
        }
    }


    /**
     * Place the item at a random position not occupied by a snake part.
     */
    private void placeItem() {

        resetGrid();

        int snakeLength = snake.getSnakeParts().size();

        for(int i = 0; i < snakeLength; i++){
            SnakePart snakePart = snake.getSnakeParts().get(i);
            worldGrid[snakePart.getX()][snakePart.getY()] = true;
        }

        int itemX = random.nextInt(Utils.WORLD_WIDTH);
        int itemY = random.nextInt(Utils.WORLD_HEIGHT);

        while (true){
            //break if an empty position is found.
            if(worldGrid[itemX][itemY] == false){
                break;
            }

            itemX += 1;
            if(itemX >= Utils.WORLD_WIDTH){
                itemX = 0;
                itemY += 1;
                if(itemY >= Utils.WORLD_HEIGHT){
                    itemY = 0;
                }
            }

        }
        item = new Item(itemX, itemY, itemColor);
    }

    /**
     * Set all elements in world grid to false.
     */
    private void resetGrid() {

        for(int i = 0; i < Utils.WORLD_WIDTH; i++){
            for(int j = 0; j < Utils.WORLD_HEIGHT; j++){
                worldGrid[i][j] = false;
            }
        }
    }


    /**
     * Resets the world to its initial position.
     */
    public void reset() {
        worldState = WorldState.IDLE;

        snake.setSnakeParts(null);
        snake = null;
        item = null;
        gameOver = false;

        initialize();

    }

}
