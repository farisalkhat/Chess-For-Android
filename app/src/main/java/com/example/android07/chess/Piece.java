package com.example.android07.chess;

public interface Piece {



    public boolean checkValidMove(int[]inputs, Piece[][]chessBoard);

    public String getPiece();

    public void setColor(char c);
    public char getColor();

}
