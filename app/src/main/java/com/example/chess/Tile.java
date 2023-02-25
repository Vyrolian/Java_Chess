package com.example.chess;

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
}
