package com.example.jetpackman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Color;

public class GameView extends View implements SensorEventListener {
    private Paint jetpackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap jetpackBitmap;

    private int jetpackWidth;
    private int jetpackHeight;
    private int currentX;
    private int currentY;

    public GameView(Context context )
    {
        super(context);
        this.init(context);
    }
    public GameView(Context context, AttributeSet attrsSet)
    {
        super(context,attrsSet);
        this.init(context);
    }
    private void init( Context context ) {
        this.jetpackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jetpack );
        jetpackBitmap = Bitmap.createScaledBitmap(jetpackBitmap,256 ,256, true);
        this.jetpackWidth = jetpackBitmap.getWidth();
        this.jetpackHeight = jetpackBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.currentX = (getWidth() - this.jetpackWidth) /2;
        this.currentY = (getHeight() - this.jetpackHeight) /2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.jetpackBitmap, this.currentX, this.currentY, this.jetpackPaint);
    }

    private void moveJetpack(float x) {
        this.currentX += (int)x;
        if(this.currentX < 0)
        {
            Log.i("DEBUG", "You loose");
        }
        else if (this.currentX+this.jetpackWidth > getWidth())
        {
            Log.i("DEBUG", "You loose");
        }
        this.invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[2];
        if (sensorEvent.values[2] > 1.0f)
//        { //gauche
//            moveJetpack(-x*10);
//        }
//        else if (sensorEvent.values[2] < -1.0f)
//        { //droite
//            moveJetpack(-x*10);
//        }
            moveJetpack(-x*10);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
