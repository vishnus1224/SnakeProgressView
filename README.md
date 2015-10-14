# SnakeProgressView
A youtube style android progress view for playing the classic snake game.

#Usage

Provide an identifier for the namespace to use custom attributes in xml. I have named it snake.
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
