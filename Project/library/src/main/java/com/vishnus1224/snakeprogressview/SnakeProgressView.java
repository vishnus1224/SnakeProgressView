package com.vishnus1224.snakeprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Vishnu on 10/10/2015.
 */
public class SnakeProgressView extends View {

    /**
     * Minimum distance to swipe in order to move the snake.
     */
    private static final int MIN_SWIPE_DISTANCE = 50;

    private World world;

    private Handler handler = new Handler();

    /**
     * Time interval after which the screen will update in milliseconds.
     */
    private long updateInterval = 100;

    /**
     * Timer variable to update the world at every updateInterval.
     */
    private Timer timer;

    /**
     * Flag to initialize the world when the view is measured for the first time.
     */
    private boolean viewInitialized;

    /**
     * Color of the snake. Default is gray.
     */
    private int snakeColor = Color.GRAY;

    /**
     * Color of the item. Default is green.
     */
    private int itemColor = Color.GREEN;

    public int getSnakeColor() {
        return snakeColor;
    }

    public void setSnakeColor(int snakeColor) {
        this.snakeColor = snakeColor;
    }

    public int getItemColor() {
        return itemColor;
    }

    public void setItemColor(int itemColor) {
        this.itemColor = itemColor;
    }

    public SnakeProgressView(Context context) {
        super(context);

    }

    public SnakeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnakeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SnakeProgressView, defStyleAttr, 0);
        snakeColor = typedArray.getColor(R.styleable.SnakeProgressView_snakeColor, snakeColor);
        itemColor = typedArray.getColor(R.styleable.SnakeProgressView_itemColor, itemColor);

        typedArray.recycle();

    }

    private void initializeView(){
        world = new World(snakeColor, itemColor);

        setOnTouchListener(onTouchListener);
        setClickable(true);
    }


    private void initTimer() {

        timer = new Timer();

        TimerTask updateTimerTask = new UpdateTimerTask();

        timer.scheduleAtFixedRate(updateTimerTask, updateInterval, updateInterval);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        Utils.setScreenDimensions(width, height);

        setMeasuredDimension(width, height);

        if(!viewInitialized){
            initializeView();
            viewInitialized = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT);

        world.draw(canvas);

    }


    /**
     * Update the world.
     */
    private void update() {

        if(world.isGameOver()){
            restart();
            return;
        }

        world.update();

        handler.post(updateRunnable);

    }

    /**
     * Cancels the update loop and resets the world to it's starting position.
     */
    private void restart() {
        timer.cancel();
        world.reset();
        handler.post(updateRunnable);
    }

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    private OnTouchListener onTouchListener = new OnTouchListener() {

        boolean touchInProgress;

        float startX = 0f;
        float startY = 0f;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:

                    startGameIfIdle();

                    startX = event.getX();
                    startY = event.getY();

                    touchInProgress = true;

                    break;
                case MotionEvent.ACTION_MOVE:

                    if(touchInProgress) {

                        final float endX = event.getX();
                        final float endY = event.getY();

                        final float dx = endX - startX;
                        final float dy = endY - startY;

                        //user has swiped horizontally
                        if (Math.abs(dx) > MIN_SWIPE_DISTANCE) {

                            if (dx < 0) {
                                moveLeft();
                            } else {
                                moveRight();
                            }
                            touchInProgress = false;
                        }
                        //user has swiped vertically
                        else if (Math.abs(dy) > MIN_SWIPE_DISTANCE) {

                            if (dy < 0) {
                                moveUp();
                            } else {
                                moveDown();
                            }
                            touchInProgress = false;
                        }

                    }

                    break;
                case MotionEvent.ACTION_CANCEL:

                    startX = 0f;
                    startY = 0f;

                    touchInProgress = false;

                    break;
                case MotionEvent.ACTION_UP:

                    startX = 0f;
                    startY = 0f;

                    touchInProgress = false;

                    break;
            }

            return true;
        }
    };

    private void startGameIfIdle() {
        if(world.getWorldState() == World.WorldState.IDLE){
            initTimer();
            world.setWorldState(World.WorldState.PLAYING);
        }
    }

    private void moveLeft(){
        world.getSnake().moveLeft();
    }

    private void moveRight() {
        world.getSnake().moveRight();
    }

    private void moveDown() {
        world.getSnake().moveDown();
    }

    private void moveUp() {
        world.getSnake().moveUp();
    }


    private class UpdateTimerTask extends TimerTask{

        @Override
        public void run() {
            update();
        }
    }

}
