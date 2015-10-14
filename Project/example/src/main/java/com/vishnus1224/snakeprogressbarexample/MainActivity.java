package com.vishnus1224.snakeprogressbarexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;

import com.vishnus1224.snakeprogressview.SnakeProgressView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    private SnakeProgressView snakeProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        snakeProgressView = (SnakeProgressView) findViewById(R.id.snakeProgressView);
    }

}
