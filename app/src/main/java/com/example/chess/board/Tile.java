package com.example.chess.board;

import  com.example.chess.pieces.Piece;
public abstract class Tile {
    int _tileCoordinate;
    Tile(int tileCoordinate)
    {
        _tileCoordinate = tileCoordinate;
    }
    public abstract boolean IsTileOccupied();
    public abstract Piece GetPiece();
    public static final class EmptyTile extends Tile{
        EmptyTile(int coordinate) {
            super(coordinate);
        }
        @Override
        public boolean IsTileOccupied(){
            return false;
        }
        @Override
        public Piece GetPiece() {
            return null;
        }
    }
    public static final class OccupiedTile extends Tile{
        Piece _pieceOnTile;
        OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            _pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean IsTileOccupied() {
            return true;
        }

        @Override
        public Piece GetPiece() {
            return this._pieceOnTile;
        }
    }
}
