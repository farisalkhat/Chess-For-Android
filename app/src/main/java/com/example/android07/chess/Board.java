package com.example.android07.chess;


import java.util.ArrayList;
import java.util.List;

public class Board {


    public static void createChessBoard(Piece[][]Board) {


        //Create Bishop pieces

        Board[0][2] = new Bishop();
        Board[0][2].setColor('b');

        Board[0][5] = new Bishop();
        Board[0][5].setColor('b');

        Board[7][2] = new Bishop();
        Board[7][2].setColor('w');

        Board[7][5] = new Bishop();
        Board[7][5].setColor('w');


        //Create Knight pieces

        Board[0][1] = new Knight();
        Board[0][1].setColor('b');

        Board[0][6] = new Knight();
        Board[0][6].setColor('b');

        Board[7][1] = new Knight();
        Board[7][1].setColor('w');

        Board[7][6] = new Knight();
        Board[7][6].setColor('w');





        //Create Queen pieces
        Board[0][3] = new Queen();
        Board[0][3].setColor('b');

        Board[7][3] = new Queen();
        Board[7][3].setColor('w');

        //Create Rook pieces

        Board[0][0] = new Rook();
        Board[0][0].setColor('b');

        Board[0][7] = new Rook();
        Board[0][7].setColor('b');

        Board[7][0] = new Rook();
        Board[7][0].setColor('w');

        Board[7][7] = new Rook();
        Board[7][7].setColor('w');




        //Create King pieces
        Board[0][4] = new King();
        Board[0][4].setColor('b');

        Board[7][4] = new King();
        Board[7][4].setColor('w');


        //Create Pawn pieces
        for(int i=0; i<=7;i++) {
            Board[1][i]= new Pawn();
            Board[1][i].setColor('b');

            Board[6][i]=new Pawn();
            Board[6][i].setColor('w');

        }





    }

    public static void displayChessBoard( Piece[][]Board) {

        System.out.println("");
        for(int i=0;i<=7;i++) {
            if(i!=0) {
                System.out.println("");
            }
            for(int j=0;j<=7;j++) {
                if (Board[i][j]==null){
                    if(((i%2)==0 && (j%2)==0) || (i%2)!=0 && (j%2)!=0){
                        System.out.print("   ");}
                    else {
                        System.out.print("## ");
                    }}
                else{System.out.print(Board[i][j].getPiece() + " ");
                }

                if(j==7) {
                    switch(i) {
                        case 0: System.out.print("8");
                            break;
                        case 1: System.out.print("7");
                            break;
                        case 2: System.out.print("6");
                            break;
                        case 3: System.out.print("5");
                            break;
                        case 4: System.out.print("4");
                            break;
                        case 5: System.out.print("3");
                            break;
                        case 6: System.out.print("2");
                            break;
                        case 7: System.out.print("1");
                            break;
                    }
                }
            }
        }
        System.out.println("");
        System.out.print(" a  b  c  d  e  f  g  h");
        System.out.println("");


    }
    public static int[] getValues( String n) {
        int[] inputs = new int[2];
        char column = n.charAt(0);
        int columnInt = 0;


        int row = Integer.parseInt(String.valueOf(n.charAt(1)));
        int rowInt = 0;



        switch(column) {
            case 'A': columnInt = 0;
                break;
            case 'B': columnInt = 1;
                break;
            case 'C': columnInt = 2;
                break;
            case 'D': columnInt = 3;
                break;
            case 'E': columnInt = 4;
                break;
            case 'F': columnInt = 5;
                break;
            case 'G': columnInt = 6;
                break;
            case 'H': columnInt = 7;
                break;
            default:

        }

        switch(row) {
            case 1: rowInt = 7;
                break;
            case 2: rowInt = 6;
                break;
            case 3: rowInt = 5;
                break;
            case 4: rowInt = 4;
                break;
            case 5: rowInt = 3;
                break;
            case 6: rowInt = 2;
                break;
            case 7: rowInt = 1;
                break;
            case 8: rowInt = 0;
                break;
            default:

        }




        inputs[0] = rowInt;
        inputs[1] = columnInt;


        return inputs;
    }

    //checks if input is a standard move
    public static boolean checkFormat( String n) {
        if (n.length()!=5) {
            return false;
        }
        else {
            char column1 = n.charAt(0);
            char column2 = n.charAt(3);
            char row1 = n.charAt(1);
            char row2 = n.charAt(4);
            char space = n.charAt(2);

            if (space !=' ') {
                return false;
            }




            int column1Int;
            int column2Int;



            //Check first character, if it is not a column letter, then return false.
            switch(column1) {
                case 'a': column1Int = 0;
                    break;
                case 'b': column1Int = 1;
                    break;
                case 'c': column1Int = 2;
                    break;
                case 'd': column1Int = 3;
                    break;
                case 'e': column1Int = 4;
                    break;
                case 'f': column1Int = 5;
                    break;
                case 'g': column1Int = 6;
                    break;
                case 'h': column1Int = 7;
                    break;
                default:
                    return false;
            }

            //Check second character, if it is not a column letter, then return false.
            switch(column2) {
                case 'a': column2Int = 0;
                    break;
                case 'b': column2Int = 1;
                    break;
                case 'c': column2Int = 2;
                    break;
                case 'd': column2Int = 3;
                    break;
                case 'e': column2Int = 4;
                    break;
                case 'f': column2Int = 5;
                    break;
                case 'g': column2Int = 6;
                    break;
                case 'h': column2Int = 7;
                    break;
                default:
                    return false;
            }


            //Check both integers to make sure they're integers.
            if(Character.isDigit(row1)==false || Character.isDigit(row2)==false) {
                return false;
            }
            int row1Int=Integer.parseInt(String.valueOf(row1));
            int row2Int=Integer.parseInt(String.valueOf(row2));

            if (row1Int<1 || row2Int<1 ||row1Int>8 || row2Int>8) {
                return false;
            }
            return true;





        }
    }


    //checks if input is a valid pawn promotion
    public static boolean checkPromotion( String n) {
        //checks for proper length
        if (n.length()!=7) {
            return false;
        }
        //checks to see move is valid
        String move = ""+n.charAt(0)+n.charAt(1)+n.charAt(2)+n.charAt(3)+n.charAt(4);
        if(!checkFormat(move)) {
            return false;
        }
        //checks space
        char space = n.charAt(5);
        if (space !=' ') {
            return false;
        }

        char promoteTo = n.charAt(6);
        //checks to make sure promoteTo is a valid piece type
        if(promoteTo == 'R' || promoteTo == 'N' || promoteTo == 'B' || promoteTo == 'Q') {
            return true;
        }

        return false;
    }

    //checks if input is a draw
    public static boolean checkDraw( String n) {
        //checks for proper length
        if(n.length()!= 11) {
            return false;
        }
        //checks to see if move is valid
        String move = ""+n.charAt(0)+n.charAt(1)+n.charAt(2)+n.charAt(3)+n.charAt(4);
        if(!checkFormat(move)) {
            return false;
        }
        //checks space
        char space = n.charAt(5);
        if (space !=' ') {
            return false;
        }
        //checks to make sure draw is spelled correctly
        String d = ""+n.charAt(6)+n.charAt(7)+n.charAt(8)+n.charAt(9)+n.charAt(10);
        if(!(d.equals("draw?"))){
            return false;
        }
        return true;
    }

    //checks if input is a resignation
    public static boolean checkResign( String n) {
        //checks if input is the string "resign"
        if(!(n.equals("resign"))) {
            return false;
        }
        return true;
    }

    public static Piece[][] makeMove(int[]inputs, Piece[][]chessBoard) {
        int row1 = inputs[0];
        int row2 = inputs[2];
        int column1 = inputs[1];
        int column2 = inputs[3];

        Piece selected =  chessBoard[row1][column1];

        chessBoard[row1][column1]=null;
        chessBoard[row2][column2]=selected;


        return chessBoard;
    }

    public static Piece[][] createPiece(int[]inputs, Piece[][]chessBoard, char nPiece){
        int row2 = inputs[2];
        int column2 = inputs[3];
        char color = chessBoard[row2][column2].getColor();
        switch(nPiece) {

            case 'N':
                chessBoard[row2][column2] = new Knight();
                chessBoard[row2][column2].setColor(color);
                break;
            case 'Q':
                chessBoard[row2][column2] = new Queen();
                chessBoard[row2][column2].setColor(color);
                break;
            case 'B':
                chessBoard[row2][column2] = new Bishop();
                chessBoard[row2][column2].setColor(color);
                break;
            case 'R':
                chessBoard[row2][column2] = new Rook();
                chessBoard[row2][column2].setColor(color);
                break;

        }
        return chessBoard;
    }


    public static boolean check(Piece[][]chessBoard,char nPiece) {
        String kingName;
        char enemyPiece;
        int kingRow = 0;
        int kingColumn = 0;

        if(nPiece=='w') {
            kingName = "wK";
            enemyPiece = 'b';
        }
        else {
            kingName = "bK";
            enemyPiece = 'w';}

        //System.out.println("This is kingName: " + kingName);
        //System.out.println("This is enemyPiece: " + enemyPiece);


        for(int i=0;i<=7;i++) {
            for(int j=0;j<=7;j++) {
                if(chessBoard[i][j]!=null) {
                    if(chessBoard[i][j].getPiece().equals(kingName)) {
                        kingRow = i;
                        kingColumn = j;
                        break;
                    }
                }
            }
        }


        int [] inputs = new int[5];
        inputs[2] = kingRow;
        inputs[3] = kingColumn;



        if(enemyPiece=='b') {
            for(int i=0;i<=7;i++) {
                for(int j=0;j<=7;j++) {
                    if(chessBoard[i][j]!=null) {
                        if(chessBoard[i][j].getColor()=='b') {
                            inputs[0] = i;
                            inputs[1] = j;
                            if(chessBoard[i][j].checkValidMove(inputs,chessBoard)==true) {
                                return true;}
                        }
                    }
                }
            }
        }


        if(enemyPiece=='w') {
            for(int i=0;i<=7;i++) {
                for(int j=0;j<=7;j++) {
                    if(chessBoard[i][j]!=null) {
                        if(chessBoard[i][j].getColor()=='w') {
                            inputs[0] = i;
                            inputs[1] = j;
                            if(chessBoard[i][j].checkValidMove(inputs,chessBoard)==true) {
                                return true;}
                        }
                    }
                }
            }
        }
        return false;

    }


    public static boolean checkmate(Piece[][]theBoard,char nPiece) {
        Piece[][] chessBoard = new Piece[8][];
        for(int i = 0; i <=7; i++) {
            chessBoard[i] = theBoard[i].clone();}

        Piece[][] chessBoard2 = new Piece[8][];
        for(int i = 0; i <=7; i++) {
            chessBoard2[i] = theBoard[i].clone();}


        String kingName;
        int kingRow = 0;
        int kingColumn = 0;

        if(nPiece=='w') {
            kingName = "wK";
        }
        else {
            kingName = "bK";
        }

        for(int i=0;i<=7;i++) {
            for(int j=0;j<=7;j++) {
                if(chessBoard[i][j]!=null) {
                    if(chessBoard[i][j].getPiece().equals(kingName)) {
                        kingRow = i;
                        kingColumn = j;
                        break;
                    }
                }
            }
        }


        boolean[][] checkmateLoc = new boolean[3][3];
        checkmateLoc[1][1] = true;

        if(kingRow+1 >7 || kingColumn+1 >7) {
            checkmateLoc[2][2] = true;
        }
        if(kingRow+1 >7 || kingColumn >7) {
            checkmateLoc[2][1] = true;
        }
        if(kingRow+1 >7 || kingColumn-1 <0) {
            checkmateLoc[2][0] = true;
        }
        if(kingRow >7 || kingColumn+1 >7) {
            checkmateLoc[1][2] = true;
        }
        if(kingRow >7 || kingColumn-1 <0) {
            checkmateLoc[1][0] = true;
        }
        if(kingRow-1 <0 || kingColumn+1 >7) {
            checkmateLoc[0][2] = true;
        }
        if(kingRow-1 <0 || kingColumn >7) {
            checkmateLoc[0][1] = true;
        }
        if(kingRow-1 <0 || kingColumn-1 <0) {
            checkmateLoc[0][0] = true;
        }

        Piece theKing = chessBoard[kingRow][kingColumn];


        /***
         for(int i=0;i<=2;i++) {
         for(int j=0;j<=2;j++) {
         System.out.print(checkmateLoc[i][j] + " ");
         }
         System.out.println("");
         }
         ***/

        for(int i=0;i<=2;i++) {
            for(int j=0;j<=2;j++) {

                if(checkmateLoc[i][j]==false) {
                    if(i==0 && j==0) {
                        if(chessBoard[kingRow-1][kingColumn-1]!=null  ) {
                            if(chessBoard[kingRow-1][kingColumn-1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==0 && j==1) {
                        if(chessBoard[kingRow-1][kingColumn]!=null) {
                            if(chessBoard[kingRow-1][kingColumn].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==0 && j==2) {
                        if(chessBoard[kingRow-1][kingColumn+1]!=null) {
                            if(chessBoard[kingRow-1][kingColumn+1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==1 && j==0) {
                        if(chessBoard[kingRow][kingColumn-1]!=null) {
                            if(chessBoard[kingRow][kingColumn-1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==1 && j==2) {
                        if(chessBoard[kingRow][kingColumn+1]!=null) {
                            if(chessBoard[kingRow][kingColumn+1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==2 && j==0) {
                        if(chessBoard[kingRow+1][kingColumn-1]!=null) {
                            if(chessBoard[kingRow+1][kingColumn-1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==2 && j==1) {
                        if(chessBoard[kingRow+1][kingColumn]!=null) {
                            if(chessBoard[kingRow+1][kingColumn].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }
                    if(i==2 && j==2) {
                        if(chessBoard[kingRow+1][kingColumn+1]!=null) {
                            if(chessBoard[kingRow+1][kingColumn+1].getColor()==theKing.getColor() ) {
                                checkmateLoc[i][j]=true;
                            }
                        }
                    }


                }




            }
        }






        for(int i=0;i<=2;i++) {
            for(int j=0;j<=2;j++) {

                if(checkmateLoc[i][j]==false) {




                    int []inputs = new int[5];
                    inputs[0] = kingRow;
                    inputs[1] = kingColumn;


                    if(i==0 && j==0) {
                        inputs[2] = kingRow-1;
                        inputs[3] = kingColumn-1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        //System.out.println("Made move on chessBoard2");
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}
                        //if(checkmateLoc[i][j]==false) {
                        //	return false;
                        //}
                    }

                    else if(i==0 && j==1) {
                        inputs[2] = kingRow-1;
                        inputs[3] = kingColumn;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==0 && j==2) {
                        inputs[2] = kingRow-1;
                        inputs[3] = kingColumn+1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==1 && j==0) {
                        inputs[2] = kingRow;
                        inputs[3] = kingColumn-1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        //Board.displayChessBoard(chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==1 && j==2) {
                        inputs[2] = kingRow;
                        inputs[3] = kingColumn+1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==2 && j==0) {
                        inputs[2] = kingRow+1;
                        inputs[3] = kingColumn-1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==2 && j==1) {
                        inputs[2] = kingRow+1;
                        inputs[3] = kingColumn;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }
                    else if(i==2 && j==2) {
                        inputs[2] = kingRow+1;
                        inputs[3] = kingColumn+1;
                        chessBoard2 = Board.makeMove(inputs, chessBoard2);
                        checkmateLoc[i][j]= Board.check(chessBoard2, nPiece);
                        for(int x = 0; x <=7; x++) {
                            chessBoard2[x] = theBoard[x].clone();}

                    }

                }


            }

        }
        /***
         for(int i=0;i<=2;i++) {
         for(int j=0;j<=2;j++) {
         System.out.print(checkmateLoc[i][j] + " ");
         }
         System.out.println("");
         }
         ***/




        for(int i=0;i<=2;i++) {
            for(int j=0;j<=2;j++) {
                if(checkmateLoc[i][j]==false) {
                    return false;
                }
            }
        }

        return true;
    }


    public static boolean checkCastling(int []inputs, Piece[][]chessBoard, boolean rookMoved1, boolean rookMoved2) {
        //System.out.println("In castling!");
        int row1 = inputs[0];
        int row2 = inputs[2];
        int column1 = inputs[1];
        int column2 = inputs[3];
        Piece[][] checkBoard = new Piece[8][];
        for(int i = 0; i <=7; i++) {
            checkBoard[i] = chessBoard[i].clone();}

        Piece selected =  chessBoard[row1][column1];
        String kingName = selected.getPiece();
        //System.out.println("kingName: " + kingName);
        if(kingName.equals("wK")) {

            //Check if wRookLeft and wKing can castle
            if(row2==7 && column2==2) {
                if(rookMoved1==true) {
                    return false;
                }
                if(checkBoard[7][1]!=null || checkBoard[7][2]!=null || checkBoard[7][3]!=null ) {
                    return false;
                }
                checkBoard[row1][column1]=null;
                checkBoard[7][3] = selected;
                if(Board.check(checkBoard, 'w')==true) {
                    return false;
                }
                checkBoard[7][3]=null;
                checkBoard[7][2] = selected;
                if(Board.check(checkBoard, 'w')==true) {
                    return false;
                }
                return true;
            }

            //check if wRookRight and wKing can castle
            if(row2==7 && column2==6) {
                if(rookMoved2==true) {
                    return false;
                }
                if(checkBoard[7][5]!=null || checkBoard[7][6]!=null) {
                    return false;
                }
                checkBoard[row1][column1]=null;
                checkBoard[7][5] = selected;
                if(Board.check(checkBoard, 'w')==true) {
                    return false;
                }
                checkBoard[7][5]=null;
                checkBoard[7][6] = selected;
                if(Board.check(checkBoard, 'w')==true) {
                    return false;
                }
                return true;
            }



            return false;



        }




        if(kingName.equals("bK")) {
            //check if bRookLeft and bKing can castle
            if(row2==0 && column2==2) {
                if(rookMoved1==true) {
                    return false;
                }
                if(checkBoard[0][1]!=null || checkBoard[0][2]!=null || checkBoard[0][3]!=null ) {
                    return false;
                }
                checkBoard[row1][column1]=null;
                checkBoard[0][3] = selected;
                if(Board.check(checkBoard, 'b')==true) {
                    return false;
                }
                checkBoard[0][3]=null;
                checkBoard[0][2] = selected;
                if(Board.check(checkBoard, 'b')==true) {
                    return false;
                }
                return true;



            }



            //check if bRookRight and bKing can castle
            if(row2==0 && column2==6) {
                if(rookMoved2==true) {
                    return false;
                }
                if(checkBoard[0][5]!=null || checkBoard[0][6]!=null ) {
                    return false;
                }
                checkBoard[row1][column1]=null;
                checkBoard[0][5] = selected;
                if(Board.check(checkBoard, 'b')==true) {
                    return false;
                }
                checkBoard[0][5]=null;
                checkBoard[0][6] = selected;
                if(Board.check(checkBoard, 'n')==true) {
                    return false;
                }
                return true;


            }



            return false;
        }
















        return false;
    }

}

