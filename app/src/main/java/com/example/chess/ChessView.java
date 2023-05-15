package com.example.chess;

import static com.example.chess.MainActivity.TAG1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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
    private Drawable movingPieceDrawable = null;
    private ChessPiece movingPiece = null;

    float originX = 200f;
    float originY = 200f;
    float cellSide = 130f;
    int lightColor = Color.parseColor("#ebecd0");
    int darkColor = Color.parseColor("#779556");

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
    private int fromCol = -1;
    private int fromRow = -1;
    private float movingPieceX = -1f;
    private float movingPieceY = -1f;

    private void playSoundForPiece(int pieceResID) {
        int soundResId;
        switch (pieceResID) {
            case R.drawable.wq:
            case R.drawable.bq:
                soundResId = R.raw.queen;
                break;
            case R.drawable.wk:
            case R.drawable.bk:
                soundResId = R.raw.king;
                break;
            case R.drawable.wr:
            case R.drawable.br:
                soundResId = R.raw.rook;
                break;
            case R.drawable.wp:
            case R.drawable.bp:
                soundResId = R.raw.pawn;
                break;
            case R.drawable.wn:
            case R.drawable.bn:
                soundResId = R.raw.knight;
                break;
            case R.drawable.wb:
            case R.drawable.bb:
                soundResId = R.raw.bishop;
                break;
            default:
                return;
        }

        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), soundResId);
        mediaPlayer.start();

        // Make sure you release the media player resources
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromCol = (int) ((event.getX() - originX) / cellSide);
                fromRow= 7 - (int) ((event.getY() - originY) / cellSide);
                Log.d(TAG1, "down at " + fromRow);
                ChessPiece piece = chessDelegate.pieceAt(fromCol, fromRow);
                if (piece != null) {
                    movingPiece = piece;
                    movingPieceDrawable = drawables.get(piece.resID);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG1, "move");
                movingPieceX = event.getX();
                movingPieceY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                ChessPiece pieces = chessDelegate.pieceAt(fromCol, fromRow);
                int col = (int) ((event.getX() - originX) / cellSide);
                int row = 7 - (int) ((event.getY() - originY) / cellSide);

                if (pieces != null) {
                    playSoundForPiece(pieces.resID);
                }
                Log.d(TAG1, "up");

                if (fromCol != col || fromRow != row) {
                    chessDelegate.movePiece(fromCol, fromRow, col, row);
                }
                movingPiece = null;
                movingPieceDrawable = null;
                invalidate();
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int smaller = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(smaller, smaller);
    }

    private void drawPieces(Canvas canvas) {



        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = chessDelegate.pieceAt(col, row);
                if (piece != null && piece != movingPiece) {
                    drawPieceAt(canvas, col, row, piece.resID);
                }
            }
        }

        if (movingPieceDrawable != null) {
            movingPieceDrawable.setBounds(
                    (int) (movingPieceX - cellSide/2),
                    (int) (movingPieceY - cellSide/2),
                    (int) (movingPieceX + cellSide/2),
                    (int) (movingPieceY + cellSide/2)
            );
            movingPieceDrawable.draw(canvas);
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