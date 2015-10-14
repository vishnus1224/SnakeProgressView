package com.vishnus1224.snakeprogressview;

import android.graphics.Canvas;

/**
 * Created by Vishnu on 10/10/2015.
 *
 * Represents an object which will be shown on the screen.
 */
interface ScreenObject {

    /**
     * For initializing essential components of the object when it is constructed.
     */
    void initialize();

    /**
     * Helper for drawing the object on the screen.
     * @param canvas The canvas to draw on.
     */
    void draw(Canvas canvas);
}
