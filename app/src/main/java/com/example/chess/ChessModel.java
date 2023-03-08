package com.example.chess;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public class ChessModel {
    Set<ChessPiece> piecesBox = new HashSet<>();
    public ChessModel() {
      reset();
    }
    void reset() {
        piecesBox.clear();
        for (int i = 0; i <= 1; i++) {
            piecesBox.add(new ChessPiece(0 + i * 7, 0, ChessPlayer.WHITE, ChessRank.ROOK, R.drawable.wr));
            piecesBox.add(new ChessPiece(0 + i * 7, 7, ChessPlayer.BLACK, ChessRank.ROOK, R.drawable.br));

            piecesBox.add(new ChessPiece(1 + i * 5, 0, ChessPlayer.WHITE, ChessRank.KNIGHT,R.drawable.wn));
            piecesBox.add(new ChessPiece(1 + i * 5, 7, ChessPlayer.BLACK, ChessRank.KNIGHT,R.drawable.bn));

            piecesBox.add(new ChessPiece(2 + i * 3 ,0 ,ChessPlayer.WHITE ,ChessRank.BISHOP ,R.drawable.wb));
            piecesBox.add(new ChessPiece(2 + i * 3 ,7 ,ChessPlayer.BLACK ,ChessRank.BISHOP ,R.drawable.bb));
        }
        for (int i = 0; i <8 ;i++){
            piecesBox.add(new ChessPiece(i ,1 ,ChessPlayer.WHITE ,ChessRank.PAWN,R.drawable.wp));
            piecesBox.add(new ChessPiece(i ,6 ,ChessPlayer.BLACK ,ChessRank.PAWN,R.drawable.bp));
        }
        piecesBox.add(new ChessPiece(3 ,0 ,ChessPlayer.WHITE ,ChessRank.QUEEN,R.drawable.wq));
        piecesBox.add(new ChessPiece(3 ,7 ,ChessPlayer.BLACK ,ChessRank.QUEEN,R.drawable.bq));
        piecesBox.add(new ChessPiece(4 ,0 ,ChessPlayer.WHITE ,ChessRank.KING,R.drawable.wk));
        piecesBox.add(new ChessPiece(4,7, ChessPlayer.BLACK, ChessRank.KING, R.drawable.bk));

    }
     ChessPiece pieceAt(int col, int row) {
        for (ChessPiece piece : piecesBox) {
            if (piece.getCol() == col && piece.getRow() == row) {
                return piece;
            }
        }
        return null;
    }
    @NonNull
    @Override
    public String toString() {
        String desc = "";
        for(int row = 7; row >=0; row--) {
            int r = 7 - row;
            desc += r;
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = pieceAt(col, row);

                if (piece == null) {
                    desc += " .";
                } else {
                    switch (piece.getRank()) {
                        case KING:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " K";
                            } else {
                                desc += " k";
                            }
                            break;
                        case QUEEN:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " Q";
                            } else {
                                desc += " q";
                            }
                            break;
                        case ROOK:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " R";
                            } else {
                                desc += " r";
                            }
                            break;
                        case BISHOP:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " B";
                            } else {
                                desc += " b";
                            }
                            break;
                        case KNIGHT:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " N";
                            } else {
                                desc += " n";
                            }
                            break;
                        case PAWN:
                            if (piece.getPlayer() == ChessPlayer.WHITE) {
                                desc += " P";
                            } else {
                                desc += " p";
                            }
                            break;
                    }
                }
            }
            desc += "\n";
        }

        return desc;
    }
}
