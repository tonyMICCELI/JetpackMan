package com.example.jetpackman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    private Paint jetpackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap jetpackBitmap;

    private int jetpackWidth;
    private int jetpackHeight;
    private int currentX;
    private int currentY;

    public GameView(Context context )
    {
        super(context);
    }
    public GameView(Context context, AttributeSet attrsSet)
    {
        super(context,attrsSet);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        jetpackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jetpack);
        jetpackBitmap = Bitmap.createScaledBitmap(jetpackBitmap,256 ,256, true);
        jetpackWidth = jetpackBitmap.getWidth();
        jetpackHeight = jetpackBitmap.getHeight();

        currentX = (w - jetpackWidth) /2;
        currentY = (h - jetpackHeight) /2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(jetpackBitmap, currentX, currentY, jetpackPaint);
    }
}
