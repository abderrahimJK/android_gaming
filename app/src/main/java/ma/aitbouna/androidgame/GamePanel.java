package ma.aitbouna.androidgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
    private List<RndSquare>  squares = new ArrayList<>();
    private Random random = new Random();
    private GameLoop gameLoop;

    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        gameLoop = new GameLoop(this);

    }

    public void render(){
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);
        squares.forEach( square -> square.draw(c));
        holder.unlockCanvasAndPost(c);
    }

    public void update(){
        squares.forEach(RndSquare::move);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            PointF pos = new PointF(event.getX(), event.getY());
            int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            int size = 25 + random.nextInt(101);
            squares.add(new RndSquare(pos, color, size));
//            render();
//            update();
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startGameLoog();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    private class RndSquare {
        private PointF pos;
        private int size;
        private Paint paint;
        private int xdir= 1;
        private int ydir= 1;

        public RndSquare(PointF pos, int color, int size) {
            this.pos = pos;
            this.size = size;
            paint = new Paint();
            paint.setColor(color);
        }

        public  void move(){
            pos.x += xdir*5;
            if(pos.x >= 1090 || pos.x <= 0)
                xdir *= -1;

            pos.y += ydir*5;
            if(pos.y >= 1920 || pos.y <= 0)
                ydir *= -1;
        }

        public void draw(Canvas c) {
            c.drawRect(pos.x, pos.y, pos.x + size, pos.y + size, paint);
        }
    }

}
