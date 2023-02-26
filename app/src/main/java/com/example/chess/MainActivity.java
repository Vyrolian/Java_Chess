package com.example.chess;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG1 = "TAG1";
    ChessModel chessModel = new ChessModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Log.d(TAG1, chessModel.toString());

    }
}