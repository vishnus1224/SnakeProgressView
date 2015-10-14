package com.vishnus1224.snakeprogressview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Vishnu on 10/10/2015.
 *
 * Represents an item the snake will eat to grow. There will be only one item at a time.
 */
class Item implements ScreenObject{

    /**
     * The x coordinate of the item's position.
     */
    private int x;

    /**
     * The y coordinate of the item's position.
     */
    private int y;

    /**
     * The radius of the item.
     */
    private float radius;

    /**
     * Paint used for drawing the item.
     */
    private Paint paint;

    /**
     * Color of the item.
     */
    private int itemColor;

    public Item(int x, int y, int itemColor) {
        this.x = x;
        this.y = y;
        this.itemColor = itemColor;
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
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(itemColor);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawCircle(Utils.getScreenPosition(x) + radius, Utils.getScreenPosition(y) + radius, radius, paint);
    }
}
