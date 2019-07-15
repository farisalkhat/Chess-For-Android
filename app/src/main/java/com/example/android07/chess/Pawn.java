package com.example.android07.chess;


public class Pawn implements Piece {
    String pieceName;
    char pieceChar = 'p';
    public char color;


    public  boolean checkValidMove(int[]inputs, Piece[][]chessBoard) {
        int row1 = inputs[0];
        int row2 = inputs[2];
        int column1 = inputs[1];
        int column2 = inputs[3];





        Piece selected =  chessBoard[row1][column1];
        Piece selectedLocation = chessBoard[row2][column2];
        if(selectedLocation!=null) {
            if (selectedLocation.getColor()==selected.getColor()){
                System.out.println("Pawn: Invalid move! Same color!");
                return false;
            }
        }

        if(selected.getColor()=='b') {
            if(row1+1==row2 && column1 == column2) {
                return true;
            }
            else if(selectedLocation ==null && row1+2==row2 && column1 == column2 && row1==1) {
                return true;
            }

            else if(row1+1==row2 && column1+1 == column2 && selectedLocation!=null) {
                return true;
            }
            else if(row1+1==row2 && column1-1 == column2 && selectedLocation!=null) {
                return true;
            }
            else {
                System.out.println("Pawn: Invalid move!");
                return false;
            }

        }
        if(selected.getColor()=='w') {
            if(selectedLocation ==null && row1-1==row2 && column1 == column2) {
                return true;
            }
            if(selectedLocation ==null && row1-2==row2 && column1 == column2 && row1==6) {
                return true;
            }

            if(row1-1==row2 && column1+1 == column2 && selectedLocation!=null) {
                return true;
            }
            if(row1-1==row2 && column1-1 == column2 && selectedLocation!=null) {
                return true;
            }
            else {
                System.out.println("Pawn: Invalid move!");
                return false;
            }

        }
        return false;
    }

    public String getPiece() {
        return pieceName;
    }
    public char getColor() {
        return color;
    }

    public void setColor(char setColor) {
        if(setColor!='b' && setColor!='w') {
            System.out.println("No color given.");
        }

        else {
            color = setColor;
            String stringColor = setColor + "";
            pieceName =  stringColor + pieceChar;
        }

    }








}

