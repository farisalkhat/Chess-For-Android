package com.example.android07.chess;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class attemptMove {

    public static int enpassantEnacted = 0;
    public static int enpassantTargetRow = 10;
    public static int enpassantTargetColumn = 10;
    private static boolean bRookMoved1 = false;
    private static boolean bRookMoved2 = false;
    private static boolean bKingMoved = false;
    private static boolean wRookMoved1 = false;
    private static boolean wRookMoved2 = false;
    private static boolean wKingMoved = false;
    private static boolean ExecuteCastling = false;


    private static int PenpassantTargetRow = 10;
    private static int PenpassantTargetColumn = 10;
    private static boolean PbRookMoved1 = false;
    private static boolean PbRookMoved2 = false;
    private static boolean PbKingMoved = false;
    private static boolean PwRookMoved1 = false;
    private static boolean PwRookMoved2 = false;
    private static boolean PwKingMoved = false;

   public static Piece[][]previousBoard = new Piece[8][];



    //the global tracking values need to be reset in between games
    public static void reset(){
       enpassantEnacted = 0;
       enpassantTargetRow = 10;
       enpassantTargetColumn = 10;
       bRookMoved1 = false;
       bRookMoved2 = false;
       bKingMoved = false;
       wRookMoved1 = false;
       wRookMoved2 = false;
       wKingMoved = false;
       ExecuteCastling = false;

       PenpassantTargetRow = 10;
       PenpassantTargetColumn = 10;
       PbRookMoved1 = false;
       PbRookMoved2 = false;
       PbKingMoved = false;
       PwRookMoved1 = false;
       PwRookMoved2 = false;
       PwKingMoved = false;
    }

    public static Piece[][] reverse(){
        enpassantTargetRow = PenpassantTargetRow;
        enpassantTargetColumn = PenpassantTargetColumn;
        bRookMoved1 = PbRookMoved1;
        bRookMoved2 = PbRookMoved2;
        bKingMoved = PbKingMoved;
        wRookMoved1 = PwRookMoved1;
        wRookMoved2 = PwRookMoved2;
        wKingMoved = PwKingMoved;

        return previousBoard;

    }

    //checks if the input from the user is a valid move
    public static boolean test(Piece[][] chessBoard, int[] selection, int[] destination, char turn){


        int[]inputs = new int[4];
        inputs[0]=selection[0];
        inputs[1]=selection[1];
        inputs[2]=destination[0];
        inputs[3]=destination[1];


            //its White's turn
            if (turn=='w') {
                boolean inputCheck;

                //makes sure the move is valid
                if(chessBoard[inputs[0]][inputs[1]]!=null && chessBoard[inputs[0]][inputs[1]].getColor()=='w') {
                    inputCheck = chessBoard[inputs[0]][inputs[1]].checkValidMove(inputs,chessBoard);
                    //checks for en passant attempt
                    String piecename = chessBoard[inputs[0]][inputs[1]].getPiece();
                    if(inputCheck == false && piecename.charAt(1)=='p' && inputs[2] == (enpassantTargetRow-1) && inputs[3] == enpassantTargetColumn && chessBoard[inputs[2]][inputs[3]]==null ) {
                        enpassantEnacted = 1;
                        //checks to make sure the move is performed by a pawn, the destination is behind the en passant target, and the destination is empty
                        inputCheck = true;
                    }
                    //checks for a castling attempt
                    if(inputCheck == false && piecename.equals("wK") && wKingMoved==false && Board.check(chessBoard, 'w')==false) {
                        inputCheck = Board.checkCastling(inputs,chessBoard,wRookMoved1,wRookMoved2);
                        //if inputCheck did happen, make sure to make boolean wKingMoved=true, so that castling can no longer occur.
                        if(inputCheck==true) {
                            PwKingMoved=wKingMoved;
                            wKingMoved=true;
                            ExecuteCastling= true;
                        }
                    }
                }
                else {
                    return false;
                }
                if(inputCheck == false) {
                    return false;
                }
                //make sure the move doesnt put yourself in check
                if(checkSimulation(chessBoard, inputs, turn)){
                 return false;
                }

                //if King or Rooks have moved, note it so that castling may not work for those pieces.
                if(inputs[0] == 7 && inputs[1]==0 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("wR")) {
                    PwRookMoved1= wRookMoved1;
                    wRookMoved1= true;
                }
                if(inputs[0] == 7 && inputs[1]==7 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("wR")) {
                    PwRookMoved2= wRookMoved2;
                    wRookMoved2= true;
                }
                if(inputs[0]==7 && inputs[1]==4 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("wK")) {
                    PwKingMoved = wKingMoved;
                    wKingMoved = true;
                }
                return true;
            }

            //its blacks turn
            if (turn=='b') {
                boolean inputCheck;

                //makes sure the move is valid
                if(chessBoard[inputs[0]][inputs[1]]!=null && chessBoard[inputs[0]][inputs[1]].getColor()=='b') {
                    inputCheck = chessBoard[inputs[0]][inputs[1]].checkValidMove(inputs,chessBoard);
                    //checks for en passant attempt
                    String piecename = chessBoard[inputs[0]][inputs[1]].getPiece();
                    if(inputCheck == false && piecename.charAt(1)=='p' && inputs[2] == (enpassantTargetRow-1) && inputs[3] == enpassantTargetColumn && chessBoard[inputs[2]][inputs[3]]==null ) {
                        enpassantEnacted = 1;
                        //checks to make sure the move is performed by a pawn, the destination is behind the en passant target, and the destination is empty
                        inputCheck = true;
                    }
                    //checks for a castling attempt
                    if(inputCheck == false && piecename.equals("bK") && bKingMoved==false && Board.check(chessBoard, 'b')==false) {
                        inputCheck = Board.checkCastling(inputs,chessBoard,bRookMoved1,bRookMoved2);
                        //if inputCheck did happen, make sure to make boolean bKingMoved=true, so that castling can no longer occur.
                        if(inputCheck==true) {
                            PbKingMoved = bKingMoved;
                            bKingMoved=true;
                            ExecuteCastling= true;
                        }
                    }
                }
                else {
                    return false;
                }
                if(inputCheck == false) {
                    return false;
                }
                //make sure the move doesnt put yourself in check
                if(checkSimulation(chessBoard, inputs, turn)){
                    return false;
                }

                //if King or Rooks have moved, note it so that castling may not work for those pieces.
                if(inputs[0] == 7 && inputs[1]==0 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("bR")) {
                    PbRookMoved1 = bRookMoved1;
                    bRookMoved1= true;
                }
                if(inputs[0] == 7 && inputs[1]==7 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("bR")) {
                    PbRookMoved1 = bRookMoved1;
                    bRookMoved2= true;
                }
                if(inputs[0]==7 && inputs[1]==4 && chessBoard[inputs[0]][inputs[1]].getPiece().equals("bK")) {
                    PbRookMoved1 = bRookMoved1;
                    bKingMoved = true;
                }
                return true;

            }

        return true;
    }


    //makes the given move, setting flags for things as required
    public static Piece[][] MakeMove(Piece[][] chessBoard, int[] selection, int[] destination, char turn) {


        for(int i = 0; i <=7; i++) {
            previousBoard[i] = chessBoard[i].clone();}




        int[]inputs = new int[4];
        inputs[0]=selection[0];
        inputs[1]=selection[1];
        inputs[2]=destination[0];
        inputs[3]=destination[1];
        int[] castleInputs = new int[5];

        if(turn == 'w') {
            //if a castling is happening, execute it
            if (ExecuteCastling == true) {
                chessBoard = Board.makeMove(inputs, chessBoard);
                if (inputs[3] == 2) {
                    castleInputs[0] = 7;
                    castleInputs[1] = 0;
                    castleInputs[2] = 7;
                    castleInputs[3] = 3;
                    chessBoard = Board.makeMove(castleInputs, chessBoard);
                } else if (inputs[3] == 6) {
                    castleInputs[0] = 7;
                    castleInputs[1] = 7;
                    castleInputs[2] = 7;
                    castleInputs[3] = 5;
                    chessBoard = Board.makeMove(castleInputs, chessBoard);
                }
                ExecuteCastling = false;
            } else {
            //not a castling, perform a normal move
                chessBoard = Board.makeMove(inputs, chessBoard);
            }

            //removes en passant target if necessary
            if (enpassantEnacted == 1) {
                chessBoard[enpassantTargetRow][enpassantTargetColumn] = null;
            }
            enpassantEnacted = 0;
            //flags for en passant if necessary, resets en passant else
            String piecename = chessBoard[inputs[2]][inputs[3]].getPiece();
            if (piecename.charAt(1) == 'p' && inputs[0] == 6 && inputs[2] == 4) {
                PenpassantTargetRow = enpassantTargetRow;
                PenpassantTargetColumn = enpassantTargetColumn;
                enpassantTargetRow = inputs[2];
                enpassantTargetColumn = inputs[3];
            } else {
                PenpassantTargetRow = enpassantTargetRow;
                PenpassantTargetColumn = enpassantTargetColumn;
                enpassantTargetRow = 10;
                enpassantTargetColumn = 10;
            }

        }else if(turn == 'b'){
            if(ExecuteCastling==true) {
                chessBoard = Board.makeMove(inputs,chessBoard);
                if(inputs[3]==2) {
                    castleInputs[0] = 0;
                    castleInputs[1] = 0;
                    castleInputs[2] = 0;
                    castleInputs[3] = 3;
                }
                else if(inputs[3]==6) {
                    castleInputs[0] = 0;
                    castleInputs[1] = 7;
                    castleInputs[2] = 0;
                    castleInputs[3] = 5;
                }
                chessBoard = Board.makeMove(castleInputs, chessBoard);
                ExecuteCastling = false;
            }
            else {
                chessBoard = Board.makeMove(inputs, chessBoard);
            }

            //removes en passant target if necessary
            if(enpassantEnacted == 1) {
                chessBoard[enpassantTargetRow][enpassantTargetColumn] = null;
            }
            enpassantEnacted = 0;
            //flags for en passant if necessary, resets en passant else
            String piecename = chessBoard[inputs[2]][inputs[3]].getPiece();
            if(piecename.charAt(1)=='p' && inputs[0]==1 && inputs[2]==3) {
                PenpassantTargetRow = enpassantTargetRow;
                PenpassantTargetColumn = enpassantTargetColumn;
                enpassantTargetRow = inputs[2];
                enpassantTargetColumn = inputs[3];
            }else {
                PenpassantTargetRow = enpassantTargetRow;
                PenpassantTargetColumn = enpassantTargetColumn;
                enpassantTargetRow = 10;
                enpassantTargetColumn = 10;
            }
        }

        return chessBoard;
    }

    //randomly selects a piece owned by the player and finds a random valid destination for that piece
    public static int[][] FindAIMove(Piece[][] chessBoard, char turn){

        int[] RandRow = {0,1,2,3,4,5,6,7};
        int[] RandCol = {0,1,2,3,4,5,6,7};
        int[] RandDestRow = {0,1,2,3,4,5,6,7};
        int[] RandDestCol = {0,1,2,3,4,5,6,7};

        RandRow = shuffleArray(RandRow);
        RandCol = shuffleArray(RandCol);
        RandDestRow = shuffleArray(RandDestRow);
        RandDestCol = shuffleArray(RandDestCol);

        for(int x1=0; x1<=7;x1++){
            for(int y1=0; y1<=7;y1++){
                //randomly selects a piece on the board
                for(int xd=0; xd<=7;xd++){
                    for(int yd=0; yd<=7; yd++){
                        //randomly selects a destination on the board
                        int[] sel = {RandRow[x1],RandCol[y1]};
                        int[] des = {RandDestRow[xd],RandDestCol[yd]};
                        if(test(chessBoard,sel,des,turn)){
                            int[][] ha = {sel,des};
                            return ha;
                        }
                    }
                }
            }
        }

        //there should always exist a valid move for a piece, so it should never get here
        int[][] ono = {{0,0},{0,0}};
        return ono;

    }

    //for randomizing the order of an array
    private static int[] shuffleArray(int[] t) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = t.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = t[index];
            t[index] = t[i];
            t[i] = a;
        }
        return t;
    }

    //simulates a move, returns true if it puts the current player is in check
    private static boolean checkSimulation(Piece[][] chessBoard, int[] inputs, char turn){
        //create fake board
        Piece[][] tempBoard = new Piece[8][];
        for(int i=0;i<=7;i++){tempBoard[i]=chessBoard[i].clone();}
        //make fake move
        tempBoard = Board.makeMove(inputs, tempBoard);
        //check if new board is in check
        return Board.check(tempBoard, turn);
    }


}
