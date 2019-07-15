package com.example.android07.chess;

public class Queen implements Piece {
    String pieceName;
    char pieceChar = 'Q';
    char color;


    public  boolean checkValidMove(int[]inputs, Piece[][]chessBoard) {
        int row1 = inputs[0];
        int row2 = inputs[2];
        int column1 = inputs[1];
        int column2 = inputs[3];

        if(row1 == row2 && column1 == column2) {
            System.out.println("Queen: Illegal move! must move!");
            return false;
        }

        //checks destination color if piece is already there
        Piece selected =  chessBoard[row1][column1];
        Piece selectedLocation = chessBoard[row2][column2];

        if(selectedLocation!=null) {
            if (selectedLocation.getColor()==selected.getColor()){
                System.out.println("Queen: Illegal move! Same color!");
                return false;
            }
        }

        int acc = 0;
        int straight = 1;
        //checks for straight move
        if(row1 != row2 && column1 != column2) {
            acc++;
            straight = 0;
        }


        //checks for diagonal move
        int hd = 0;
        int vd = 0;
        if(row2 > row1) {
            vd = row2 - row1;
        }else{
            vd = row1 - row2;
        }
        if(column2 > column1) {
            hd = column2 - column1;
        }else{
            hd = column1 - column2;
        }
        if(hd != vd) {
            acc++;
        }

        if(acc > 1) {
            System.out.println("Queen: Illegal move! must move either straight or diagonal!");
            return false;
        }


        if(straight == 1) {//queen moved straight
            //checks path is clear
            if(row1 == row2) { //move is horizontal
                if(column1 > column2) {
                    for(int c = column2+1; c < column1; c++) {
                        if(chessBoard[row1][c] != null) {
                            System.out.println("Queen: Illegal move! must have a clear path!");
                            return false;
                        }
                    }
                }else {
                    for(int c = column1+1; c < column2; c++) {
                        if(chessBoard[row1][c] != null) {
                            System.out.println("Queen: Illegal move! must have a clear path!");
                            return false;
                        }
                    }
                }
            }else{ //move is vertical
                if(row1 > row2) {
                    for(int c = row2+1; c < row1; c++) {
                        if(chessBoard[c][column1] != null) {
                            System.out.println("Queen: Illegal move! must have a clear path!");
                            return false;
                        }
                    }
                }else {
                    for(int c = row1+1; c < row2; c++) {
                        if(chessBoard[c][column1] != null) {
                            System.out.println("Queen: Illegal move! must have a clear path!");
                            return false;
                        }
                    }
                }

            }
        }else {//queen moved diagonal
            //checks path is clear
            if(row2 > row1 && column2 > column1) {
                for(int c = hd-1; c > 0; c--) {
                    if(chessBoard[row1+c][column1+c] != null) {
                        System.out.println("Queen: Illegal move! must have a clear path!");
                        return false;
                    }
                }
            }else if(row2 > row1 && column2 < column1) {
                for(int c = hd-1; c > 0; c--) {
                    if(chessBoard[row1+c][column1-c] != null) {
                        System.out.println("Queen: Illegal move! must have a clear path!");
                        return false;
                    }
                }
            }else if(row2 < row1 && column2 > column1) {
                for(int c = hd-1; c > 0; c--) {
                    if(chessBoard[row1-c][column1+c] != null) {
                        System.out.println("Queen: Illegal move! must have a clear path!");
                        return false;
                    }
                }
            }else {
                for(int c = hd-1; c > 0; c--) {
                    if(chessBoard[row1-c][column1-c] != null) {
                        System.out.println("Queen: Illegal move! must have a clear path!");
                        return false;
                    }
                }
            }
        }


        return true;
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

