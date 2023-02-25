package com.example.chess.pieces;

import com.example.chess.board.Board;
import com.example.chess.board.BoardUtils;
import com.example.chess.board.Move;
import com.example.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15, -10, -6, 6, 10, 15, 17};
    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(Board board) {

        int _candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){

            _candidateDestinationCoordinate = this._piecePosition + currentCandidateOffset;

            if(BoardUtils.IsValidTileCoordinate(_candidateDestinationCoordinate)){
                if (IsFirstColumnExclusion(this._piecePosition, currentCandidateOffset) ||
                        IsSecondColumnExclusion(this._piecePosition, currentCandidateOffset) ||
                        IsSeventhColumnExclusion(this._piecePosition, currentCandidateOffset) ||
                        IsEighthColumnExclusion(this._piecePosition, currentCandidateOffset))
                {
                    continue;
                }
                final Tile _candidateDestinationTile = board.GetTile(_candidateDestinationCoordinate);

                if(!_candidateDestinationTile.IsTileOccupied())
                {
                    legalMoves.add(new Move());
                }
                else
                {
                    final Piece pieceAtDestination = _candidateDestinationTile.GetPiece();
                    final Alliance pieceAlliance = pieceAtDestination.GetPieceAlliance();
                    if(this._pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }

                }
            }


        }

        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean IsFirstColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }
    private static boolean IsSecondColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean IsSeventhColumnExclusion(final int currentPosition,
                                                    final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean IsEighthColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }
}
