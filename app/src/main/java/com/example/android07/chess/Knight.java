package com.example.android07.chess;

public class Knight implements Piece {
    String pieceName;
    char pieceChar = 'N';
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
                System.out.println("Knight: Invalid move! Same color!");
                return false;
            }
        }


        if(row1+1 == row2 && column1+2 ==column2) {
            return true;
        }
        else if(row1+2 == row2 && column1+1 ==column2) {
            return true;
        }
        else if(row1+1 == row2 && column1-2 ==column2) {
            return true;
        }
        else if(row1+2 == row2 && column1-1 ==column2) {
            return true;
        }
        else if(row1-1 == row2 && column1-2 ==column2) {
            return true;
        }
        else if(row1-2 == row2 && column1-1 ==column2) {
            return true;
        }
        else if(row1-1 == row2 && column1+2 ==column2) {
            return true;
        }
        else if(row1-2 == row2 && column1+1 ==column2) {
            return true;
        }
        else {
            System.out.println("Knight: Invalid move! The Knight cannot move there.");
            return false;
        }





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

