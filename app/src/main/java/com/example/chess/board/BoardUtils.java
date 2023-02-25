package com.example.chess.board;

import androidx.appcompat.view.ActionBarPolicy;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;


    private BoardUtils(){
        throw new RuntimeException("Class cannot be instantiated");
    }
    public static boolean IsValidTileCoordinate(int coordinate) {
        return coordinate >=0 && coordinate < 64;
    }
}
