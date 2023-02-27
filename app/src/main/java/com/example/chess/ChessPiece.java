package com.example.chess;

public class ChessPiece {
    private final int col;
    private final int row;
    private final ChessPlayer player;
    private final ChessRank rank;

    public ChessPiece(int col, int row, ChessPlayer player, ChessRank rank) {
        this.col = col;
        this.row = row;
        this.player = player;
        this.rank = rank;
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
