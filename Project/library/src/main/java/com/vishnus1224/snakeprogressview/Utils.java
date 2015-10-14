package com.vishnus1224.snakeprogressview;

/**
 * Created by Vishnu on 10/10/2015.
 */
class Utils {

    public static int SCREEN_WIDTH = 0;
    public static int SCREEN_HEIGHT = 0;

    /**
     * The size of each block on the grid.
     */
    public static int BLOCK_SIZE = 0;

    /**
     * The width of the world in blocks.
     */
    public static final int WORLD_WIDTH = 20;
    /**
     * The height of the world in blocks.
     */
    public static int WORLD_HEIGHT = 0;

    /**
     * Converts the world position to screen position for drawing the object on screen.
     * @param worldPosition The position of the object in world space.
     * @return The x coordinate of the screen position.
     */
    public static int getScreenPosition(int worldPosition){
        return worldPosition * BLOCK_SIZE;
    }

    public static void setScreenDimensions(int width, int height){
        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;

        BLOCK_SIZE = SCREEN_WIDTH / WORLD_WIDTH;

        WORLD_HEIGHT = (SCREEN_HEIGHT / BLOCK_SIZE);
    }

}
