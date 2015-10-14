# SnakeProgressView
A youtube style android progress view for playing the classic snake game.

##Demo

![](https://github.com/vishnus1224/SnakeProgressView/blob/master/Project/demo/demo.gif)

#Usage   

Add the following line to the build.gradle file in order to use the library:
```
compile 'com.vishnus1224.snakeprogressview:library:1.0'
```

Set the view's visibility to `INVISIBLE` to hide the view whenever a long running task is complete. Similarly make it `VISIBLE` to allow the user to interact with it, while data is being fetched in the background.   

Currently, the snake color and the item color are customizable and can be done either via xml or through code.

For doing it in xml:   
Create an identifier for the namespace to use custom attributes in xml. I have named it snake.
```
xmlns:snake="http://schemas.android.com/apk/res-auto"
```    

The namespace has following custom attributes:
```
 <declare-styleable name="SnakeProgressView">
    <attr name="snakeColor" format="color|reference"></attr> //specifies the color of the snake.
    <attr name="itemColor" format="color|reference"></attr> // specifies the color of the item.
 </declare-styleable>
```

***Usage Tips***  
-For using this view in a layout, define it as the last child as it will be drawn on top of other views.    
-The view has a transparent background color. Hence the content below will be visible.  
-Preferably set the view width and height to match_parent so that the entire screen is available as the playing area.

#Sample   
Following is the xml layout from the sample project:
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:snake="http://schemas.android.com/apk/res-auto"
    android:id="@+id/root"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent"  tools:context=".MainActivity">

    <Button
        android:text="Button"
        android:layout_alignParentRight="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_alignParentBottom="true"
        android:text="TextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />


    <com.vishnus1224.snakeprogressview.SnakeProgressView
        android:id="@+id/snakeProgressView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

***Java code example***   
For adding and customizing the view in code, follow the same steps used for creating any other view in code.   
e.g.   
```
SnakeProgressView snakeProgressView = new SnakeProgressView(context);
snakeProgressView.setSnakeColor(specify the desired color);
snakeProgressView.setItemColor(specify the desired color);

rootLayout.addView(snakeProgressView);
```

#ENJOY
