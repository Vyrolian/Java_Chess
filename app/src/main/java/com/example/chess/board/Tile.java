package com.example.chess.board;

import  com.example.chess.pieces.Piece;
import  com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
public abstract class Tile {
    protected final int _tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = CreateAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> CreateAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < 64; i++)
        {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return  ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile CreateTile(final int tileCoordinate, final Piece piece)
    {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private Tile(int tileCoordinate)
    {
        _tileCoordinate = tileCoordinate;
    }
    public abstract boolean IsTileOccupied();
    public abstract Piece GetPiece();
    public static final class EmptyTile extends Tile{
        private EmptyTile(final int coordinate) {
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
        private final Piece _pieceOnTile;
        private OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
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
