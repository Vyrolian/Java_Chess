package com.example.chess;

public class ChessPiece {
    int col;
    int row;
    private final ChessPlayer player;
    private final ChessRank rank;
    public final int resID;
    public ChessPiece(int col, int row, ChessPlayer player, ChessRank rank, int resID) {
        this.col = col;
        this.row = row;
        this.player = player;
        this.rank = rank;
        this.resID = resID;
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public ChessRank getRank(){
        return rank;
    }
    public ChessPlayer getPlayer() {
        return player;
    }
}
