package com.example.chess;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ChessView extends View {

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    float originX = 200f;
    float originY = 200f;
    float cellSide = 130f;
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint = new Paint();
        for (int i = 0; i <8; i++){
            for (int j = 0; j < 8; j++){

                if ((i + j) % 2 == 1) {
                    paint.setColor(Color.DKGRAY);
                } else {
                    paint.setColor(Color.LTGRAY);
                }
                canvas.drawRect(originX + i * cellSide, originY + j * cellSide, originX + (i + 1)* cellSide, originY + (j + 1) * cellSide, paint);
            }

        }


        Resources resources = getResources();
        Rect rect = new Rect();
        Drawable wQDrawable = ContextCompat.getDrawable(getContext(), R.drawable.wq);
        wQDrawable.setBounds(0, 0, 400, 400);
        wQDrawable.draw(canvas);

    }
}
