package com.example.chess.pieces;

import com.example.chess.board.Board;
import com.example.chess.board.Move;

import java.util.Collection;
import java.util.List;

public abstract class Piece {

    protected final int _piecePosition;
    protected final Alliance _pieceAlliance;

    Piece(final int piecePosition, final Alliance pieceAlliance)
    {
        _piecePosition = piecePosition;
        _pieceAlliance = pieceAlliance;
    }
    public Alliance GetPieceAlliance()
    {
        return this._pieceAlliance;
    }
    public abstract Collection<Move> CalculateLegalMoves(final Board board);
}

