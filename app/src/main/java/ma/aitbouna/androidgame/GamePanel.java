package ma.aitbouna.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
    private float x;
    private float y;
    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
    }

    private void render(){
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);
        c.drawRect(x, y, x+50, y+50, redPaint);
        holder.unlockCanvasAndPost(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         x = event.getX();
         y = event.getY();
        render();
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        c.drawRect(50, 50, 100, 100, redPaint);
        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
