package com.example.chess;

import static com.example.chess.MainActivity.TAG1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChessView extends View {

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadDrawables();
    }
    private final float scaleFactor = 0.9f;


    float originX = 200f;
    float originY = 200f;
    float cellSide = 130f;
    int lightColor = Color.argb(1f, .9f, .9f, .9f);
    int darkColor = Color.argb(1f, .6f, .6f, .6f);

    private final Set<Integer> imgResIDs = new HashSet<>(Arrays.asList(
            R.drawable.wq,
            R.drawable.bq,
            R.drawable.wk,
            R.drawable.bk,
            R.drawable.wr,
            R.drawable.br,
            R.drawable.wb,
            R.drawable.bb,
            R.drawable.wn,
            R.drawable.bn,
            R.drawable.wp,
            R.drawable.bp
    ));
    private final Map<Integer, Drawable> drawables = new HashMap<>();
    private void loadDrawables() {
        for (int resId : imgResIDs) {
            drawables.put(resId, ContextCompat.getDrawable(getContext(), resId));
        }
    }
    Paint paint = new Paint();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG1, "down");
                break;case MotionEvent.ACTION_MOVE:
                Log.d(TAG1, "move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG1, "up");
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG1, "(" + getWidth() + getHeight() + ")");
        if (canvas != null) {
            int chessBoardSide = (int) (Math.min(getWidth(), getHeight()) * scaleFactor);

            cellSide = chessBoardSide / 8;
            originX = (getWidth() - chessBoardSide) / 2f;
            originY = (getHeight() - chessBoardSide) / 2f;
        }
        drawChessboard(canvas);
        drawPieces(canvas);
    }

    private void drawPieces(Canvas canvas) {



        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                ChessPiece piece = chessDelegate.pieceAt(col, row);
                if (piece != null) {
                    drawPieceAt(canvas, col, row, piece.resID);
                }

            }
        }

    }
    ChessDelegate chessDelegate = null;
    private void drawPieceAt(Canvas canvas, int col, int row, int resID) {
        Drawable wQDrawable = drawables.get(resID);
        wQDrawable.setBounds((int) (originX + col * cellSide), (int)(originY + (7 - row) * cellSide), (int)(originX + (col+1) * cellSide), (int)(originY + (7 - row+1)*cellSide));
        wQDrawable.draw(canvas);
    }
    private void drawChessboard(Canvas canvas){
        for (int row = 0; row <8; row++){
            for (int col = 0; col < 8; col++){
                drawSquareAt(canvas, row, col, (row + col) % 2 == 1);
            }
        }
    }
    private void drawSquareAt(Canvas canvas, int col, int row, boolean isDark){
        if (isDark) {
            paint.setColor(darkColor);
        } else {
            paint.setColor(lightColor);
        }
        canvas.drawRect(originX + col * cellSide, originY + row * cellSide, originX + (col + 1)* cellSide, originY + (row + 1) * cellSide, paint);
    }
}
