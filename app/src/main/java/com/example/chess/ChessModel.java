package com.example.chess;

import androidx.annotation.NonNull;

public class ChessModel {
    @NonNull
    @Override
    public String toString() {
        String desc = "";
        for(int row = 0; row <8; row++) {
            int r = 7 - row;
            desc += r;
            for (int col = 0; col < 8; col++) {
                desc += " .";
            }
            desc += "\n";
        }

        return desc;
    }
}
