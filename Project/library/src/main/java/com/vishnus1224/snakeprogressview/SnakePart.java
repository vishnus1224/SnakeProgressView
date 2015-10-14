package com.vishnus1224.snakeprogressview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Vishnu on 10/10/2015.
 *
 * Represents a single part of the snake.
 */
class SnakePart implements ScreenObject {

    /**
     * The x coordinate of the position in the world.
     */
    private int x;

    /**
     * The y coordinate of the position in the world.
     */
    private int y;

    /**
     * The radius of the part.
     */
    private float radius;

    /**
     * Paint used for specifying the color of the part.
     */
    private Paint paint;

    /**
     * Color of the snake.
     */
    private int snakeColor;

    public SnakePart(int x, int y, int snakeColor) {
        this.x = x;
        this.y = y;
        this.snakeColor = snakeColor;
        initialize();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void initialize() {

        initializePaint();

        radius = Utils.BLOCK_SIZE / 2f;
    }

    private void initializePaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(snakeColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawCircle(Utils.getScreenPosition(x) + radius, Utils.getScreenPosition(y) + radius, radius, paint);

    }
}
