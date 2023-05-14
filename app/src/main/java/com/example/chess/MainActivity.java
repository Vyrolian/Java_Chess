package com.example.chess;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements ChessDelegate{

    public static final String TAG1 = "TAG1";
    ChessModel chessModel = new ChessModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Log.d(TAG1, chessModel.toString());
         ChessView chessView = findViewById(R.id.chess_view);
        chessView.chessDelegate = this;
    }

    @Override
    public ChessPiece pieceAt(int col, int row) {
        return chessModel.pieceAt(col, row);
    }

    @Override
    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow);
        ChessView chessView = findViewById(R.id.chess_view);
        chessView.invalidate();
    }
}