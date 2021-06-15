package com.example.jetpackman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Color;

public class Spikes extends View {
    private Paint spikesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap spikesBitmap[] = new Bitmap[50];

    private int spikesWidth[] = new int[50];
    private int spikesHeight[] = new int[50];
    private int currentX[] = new int[50];
    private int currentY[] = new int[50];

    public Spikes(Context context, AttributeSet attrsSet)
    {
        super(context,attrsSet);
        this.init(context);
    }
    public Spikes(Context context) {
        super(context);
        this.init(context);
    }

    private void init( Context context ) {
        for(int i=0; i<50; i++) {
            this.spikesBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.spikes);
            spikesBitmap[i] = Bitmap.createScaledBitmap(spikesBitmap[i],64 ,64, true);
            this.spikesWidth[i] = spikesBitmap[i].getWidth();
            this.spikesHeight[i] = spikesBitmap[i].getHeight();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for(int i=0; i<50; i++){
            this.currentX[i] = 0;
            this.currentY[i] = i*60;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<50; i++)
        {
            canvas.drawBitmap(this.spikesBitmap[i], this.currentX[i], this.currentY[i], this.spikesPaint);
        }

    }
}
