package com.example.chess;
import android.media.MediaPlayer;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements ChessDelegate{

    public static final String TAG1 = "TAG1";
    ChessModel chessModel = new ChessModel();
    // Declare MediaPlayer
    private MediaPlayer mediaPlayer;
    private ChessView chessView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MediaPlayer with a source (Replace R.raw.your_audio_file with your audio file)
        mediaPlayer = MediaPlayer.create(this, R.raw.operus);

        // Set looping to false
        mediaPlayer.setLooping(true);

        // Start playing
        mediaPlayer.start();

        Log.d(TAG1, chessModel.toString());

        chessView = findViewById(R.id.chess_view);

        chessView.chessDelegate = this;
        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chessModel.reset();
                chessView.invalidate();
            }
        });
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the activity is destroyed, also stop the media player
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}