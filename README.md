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
