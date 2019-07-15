package com.example.android07;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.android07.chess.Bishop;
import com.example.android07.chess.Board;
import com.example.android07.chess.Knight;
import com.example.android07.chess.MyApplication;
import com.example.android07.chess.Piece;
import com.example.android07.chess.Queen;
import com.example.android07.chess.Replay;
import com.example.android07.chess.ReplayManager;
import com.example.android07.chess.Rook;
import com.example.android07.chess.attemptMove;

import java.util.ArrayList;


public class ChessGame extends AppCompatActivity implements View.OnClickListener{
    public ImageAdapter myImgAdapter;

    public TextView playerMove;
    public TextView playerEvent;


    public Drawable newSpot;
    public Drawable originalSpot;
    int[]reverseInputs = new int[5];


    Piece[][]chessBoard = new Piece[8][8];
    Piece[][]previousBoard = new Piece[8][];

    int[] inputs = new int[5];
    String n;
    boolean inputCheck = false;
    boolean lmao = true;
    boolean checkCheck;

    public TextView[][] DisplayBoard = new TextView[8][8];
    public TextView[][] DisplayBoardBackground = new TextView[8][8];

    boolean pieceSelected = false;
    char playerTurn = 'w';

    boolean gameover = false;
    boolean rewind = true;

    int[] tileSelected = new int[2];
    int[] firstMove = new int[2];
    int[] previousMove = new int[4];
    ArrayList<int[]> moveSequence = new ArrayList<int[]>();

    boolean disablebuttons = false;
    boolean promotionScene = false;

    ArrayList<int[]> thereplay;
    int[]currentMove = new int[4];
    int index = 0;
    int enpassantChecker;
    int eTargetRow = 10;
    int eTargetColumn = 10;




    public void returnreplay(View v) {
        startActivity(new Intent(ChessGame.this, ReplayGame.class));
    }
    public void forward(View v) {



        System.out.println(thereplay.size());
        if(thereplay.size()==0){
            Button play = findViewById(R.id.play);
            play.setVisibility(View.INVISIBLE);

            Button savereplay = findViewById(R.id.savereplay);
            savereplay.setVisibility(View.INVISIBLE);

            Button OK = findViewById(R.id.replaycancel);
            OK.setVisibility(View.INVISIBLE);

            TextView player = findViewById(R.id.playerevent);
            player.setVisibility(View.INVISIBLE);


            TextView returnreplay = findViewById(R.id.returnreplay);
            returnreplay.setVisibility(View.VISIBLE);

            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("Black won!");

            ReplayGame.replayOn=false;

        }
        else{
            int[]initialSpot = new int[2];
            int[]aimedSpot = new int[2];
            initialSpot[0] = currentMove[0];
            initialSpot[1] = currentMove[1];
            aimedSpot[0] = currentMove[2];
            aimedSpot[1] = currentMove[3];





            attemptMove.test(chessBoard,initialSpot,aimedSpot,playerTurn);
            enpassantChecker = attemptMove.enpassantEnacted;
            eTargetRow = attemptMove.enpassantTargetRow;
            eTargetColumn = attemptMove.enpassantTargetColumn;



            System.out.println(initialSpot[0]);
            System.out.println(initialSpot[1]);
            System.out.println(aimedSpot[0]);
            System.out.println(aimedSpot[1]);
            chessBoard = attemptMove.MakeMove(chessBoard,initialSpot,aimedSpot,playerTurn);
            //Reset move selection
            pieceSelected = false;

            if(enpassantChecker==1){
                int[]deletepiece = new int[2];
                deletepiece[0]=eTargetRow;
                deletepiece[1]=eTargetColumn;
                fixTiles2(deletepiece);
            }



            playerEvent = (TextView)  findViewById(R.id.playerevent);
            playerEvent.setVisibility(View.INVISIBLE);

            //DisplayBoard changes

            Drawable thePiece = DisplayBoard[initialSpot[0]][initialSpot[1]].getBackground();
            newSpot = DisplayBoard[aimedSpot[0]][aimedSpot[1]].getBackground();
            originalSpot = DisplayBoard[initialSpot[0]][initialSpot[1]].getBackground();
            DisplayBoard[aimedSpot[0]][aimedSpot[1]].setBackground(thePiece);

            fixTiles2(initialSpot);
            lookforcheck();
            Board.displayChessBoard(chessBoard);

            //Check for promotions

            //checkPromotion();


            //switch turns
            if(playerTurn=='w' && gameover==false && promotionScene==false){
                playerTurn='b';
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("Black's turn.");
            }
            else if(gameover==false && playerTurn=='b' && promotionScene==false){
                playerTurn='w';
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("White's turn.");
            }
            //save turn order
            //int[] timp = {firstMove[0],firstMove[1],tileSelected[0],tileSelected[1]};
            //moveSequence.add(timp);


        /*
        previousMove[0] = firstMove[0];
        previousMove[1] = firstMove[1];
        previousMove[2] = tileSelected[0];
        previousMove[3] = tileSelected[1];
        rewind=false;

        */
            lookforcheck();



            index++;
            if(index!=thereplay.size()){
                currentMove = thereplay.get(index);
            }
            else{



                Button play = findViewById(R.id.play);
                play.setVisibility(View.INVISIBLE);

                Button savereplay = findViewById(R.id.savereplay);
                savereplay.setVisibility(View.INVISIBLE);

                Button OK = findViewById(R.id.replaycancel);
                OK.setVisibility(View.INVISIBLE);

                TextView player = findViewById(R.id.playerevent);
                player.setVisibility(View.INVISIBLE);


                TextView returnreplay = findViewById(R.id.returnreplay);
                returnreplay.setVisibility(View.VISIBLE);

                ReplayGame.replayOn=false;
            }










        }










    }






    public void askResign(View v){

        if(gameover==false){
            GridLayout chessboard = findViewById(R.id.gridLayout);

            playerEvent = (TextView)  findViewById(R.id.playerevent);
            playerEvent.setVisibility(View.INVISIBLE);
            playerMove = (TextView)  findViewById(R.id.playerturn);
            if(playerTurn=='w'){
                playerMove.setText("Black won!");
                Button savereplay = findViewById(R.id.savereplay);
                Button replaycancel = findViewById(R.id.replaycancel);
                TextView playerevent = findViewById(R.id.playerevent);
                savereplay.setVisibility(View.VISIBLE);
                replaycancel.setVisibility(View.VISIBLE);
                playerevent.setText("Save replay?");
                playerevent.setVisibility(View.VISIBLE);


            }
            else{
                playerMove.setText("White Won!");
                Button savereplay = findViewById(R.id.savereplay);
                Button replaycancel = findViewById(R.id.replaycancel);
                TextView playerevent = findViewById(R.id.playerevent);
                savereplay.setVisibility(View.VISIBLE);
                replaycancel.setVisibility(View.VISIBLE);
                playerevent.setText("Save replay?");
                playerevent.setVisibility(View.VISIBLE);
            }


            chessboard.setClickable(false);
            gameover = true;
        }

    }

    public void askDraw(View v){
        if(gameover==false){
            GridLayout chessboard = findViewById(R.id.gridLayout);

            playerEvent = (TextView)  findViewById(R.id.playerevent);
            playerEvent.setVisibility(View.INVISIBLE);
            playerMove = (TextView)  findViewById(R.id.playerturn);


            playerMove.setText("Draw!");
            Button savereplay = findViewById(R.id.savereplay);
            Button replaycancel = findViewById(R.id.replaycancel);
            TextView playerevent = findViewById(R.id.playerevent);
            savereplay.setVisibility(View.VISIBLE);
            replaycancel.setVisibility(View.VISIBLE);
            playerevent.setText("Save replay?");
            playerevent.setVisibility(View.VISIBLE);



            chessboard.setClickable(false);
            gameover = true;
        }

    }

    public void AIMove(View v){
        //Change chessBoard
        for(int i = 0; i <=7; i++) {
            previousBoard[i] = chessBoard[i].clone();}
        //randomly selects a move
        int[][] tmp = attemptMove.FindAIMove(chessBoard,playerTurn);
        firstMove = tmp[0];
        tileSelected = tmp[1];
        //executes that move
        chessBoard = attemptMove.MakeMove(chessBoard,firstMove,tileSelected,playerTurn);
        //Reset move selection
        pieceSelected = false;

        playerEvent = (TextView)  findViewById(R.id.playerevent);
        playerEvent.setVisibility(View.INVISIBLE);

        //DisplayBoard changes

        Drawable thePiece = DisplayBoard[firstMove[0]][firstMove[1]].getBackground();
        newSpot = DisplayBoard[tileSelected[0]][tileSelected[1]].getBackground();
        originalSpot = DisplayBoard[firstMove[0]][firstMove[1]].getBackground();
        DisplayBoard[tileSelected[0]][tileSelected[1]].setBackground(thePiece);

        fixTiles();
        lookforcheck();

        //Check for promotions
        checkPromotion();

        //switch turns
        if(playerTurn=='w' && gameover==false && promotionScene==false){
            playerTurn='b';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("Black's turn.");
        }
        else if(gameover==false && playerTurn=='b' && promotionScene==false){
            playerTurn='w';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("White's turn.");
        }
        //save turn order
        int[] timp = {firstMove[0],firstMove[1],tileSelected[0],tileSelected[1]};
        moveSequence.add(timp);

        previousMove[0] = firstMove[0];
        previousMove[1] = firstMove[1];
        previousMove[2] = tileSelected[0];
        previousMove[3] = tileSelected[1];
        rewind=false;
    }



    public void saveReplay(View v){
        //startActivity(new Intent(ChessGame.this, MainActivity.class));

        TextView playerevent = findViewById(R.id.playerevent);
        playerevent.setText("Enter a name");

        Button savereplay = findViewById(R.id.savereplay);
        savereplay.setVisibility(View.INVISIBLE);

        Button savereplay2 = findViewById(R.id.savereplay2);
        savereplay2.setVisibility(View.VISIBLE);

        EditText gameName = findViewById(R.id.gameName);
        gameName.setVisibility(View.VISIBLE);

    }


    public void saveReplay2(View v){
        EditText gameName = findViewById(R.id.gameName);
        String title = gameName.getText().toString();

        Replay t = new Replay(moveSequence,title);
        Context context = MyApplication.getAppContext();
        ReplayManager.saveReplay(t, context);


        startActivity(new Intent(ChessGame.this, MainActivity.class));


    }



    public void endGame(View v){
        startActivity(new Intent(ChessGame.this, MainActivity.class));

    }







    public void undoCommand(View v){
        if(rewind==false){
            attemptMove.reverse();
            DisplayBoard[previousMove[0]][previousMove[1]].setBackground(originalSpot);
            DisplayBoard[previousMove[2]][previousMove[3]].setBackground(newSpot);
            for(int i = 0; i <=7; i++) {
                chessBoard[i] = previousBoard[i].clone();}
            if(playerTurn=='w' && gameover==false){

                if(promotionScene==true){
                    ImageButton bishopblack = findViewById(R.id.bishopblack);
                    ImageButton queenblack = findViewById(R.id.queenblack);
                    ImageButton rookblack = findViewById(R.id.rookblack);
                    ImageButton knightblack = findViewById(R.id.knightblack);
                    bishopblack.setVisibility(View.INVISIBLE);
                    queenblack.setVisibility(View.INVISIBLE);
                    rookblack.setVisibility(View.INVISIBLE);
                    knightblack.setVisibility(View.INVISIBLE);
                    promotionScene=false;

                    playerTurn='w';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("White's turn.");
                }
                else{
                    playerTurn='b';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("Black's turn.");
                }







            }
            else if(gameover==false && playerTurn=='b'){
                if(promotionScene==true){
                    playerTurn='b';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("Black's turn.");
                    promotionScene=false;
                    ImageButton bishopblack = findViewById(R.id.bishopblack);
                    ImageButton queenblack = findViewById(R.id.queenblack);
                    ImageButton rookblack = findViewById(R.id.rookblack);
                    ImageButton knightblack = findViewById(R.id.knightblack);

                    bishopblack.setVisibility(View.INVISIBLE);
                    queenblack.setVisibility(View.INVISIBLE);
                    rookblack.setVisibility(View.INVISIBLE);
                    knightblack.setVisibility(View.INVISIBLE);


                }
                else{
                    playerTurn='w';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("White's turn.");
                }
            }
            //removes last move from move sequence
            moveSequence.remove(moveSequence.size()-1);
        }



        rewind = true;
    }


    @Override
    public void onClick(View v){

        //reads input, converts to tileSelected
        TextView selectedSpot = (TextView) v;
        String location = selectedSpot.getTag().toString();
       // System.out.println(location);

        tileSelected = Board.getValues(location);
        System.out.println(tileSelected[0] + " " + tileSelected[1]);

        //this is the first selection
        if(pieceSelected==false && gameover==false && promotionScene==false && ReplayGame.replayOn==false){
            if(chessBoard[tileSelected[0]][tileSelected[1]]!= null && chessBoard[tileSelected[0]][tileSelected[1]].getColor()==playerTurn) {

                firstMove = tileSelected;
                pieceSelected = true;
                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.INVISIBLE);
            }
            else{
                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.VISIBLE);
            }
        //this is the second selection
        }else if(pieceSelected==true && promotionScene==false && gameover==false && ReplayGame.replayOn==false){




            lookforcheck();
            //see if the move is valid
            if(attemptMove.test(chessBoard,firstMove,tileSelected,playerTurn)){
                enpassantChecker = attemptMove.enpassantEnacted;
                eTargetRow = attemptMove.enpassantTargetRow;
                eTargetColumn = attemptMove.enpassantTargetColumn;



                //Change chessBoard
                for(int i = 0; i <=7; i++) {
                    previousBoard[i] = chessBoard[i].clone();}
                chessBoard = attemptMove.MakeMove(chessBoard,firstMove,tileSelected,playerTurn);
                //Reset move selection
                pieceSelected = false;

                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.INVISIBLE);

                //DisplayBoard changes

                Drawable thePiece = DisplayBoard[firstMove[0]][firstMove[1]].getBackground();
                newSpot = DisplayBoard[tileSelected[0]][tileSelected[1]].getBackground();
                originalSpot = DisplayBoard[firstMove[0]][firstMove[1]].getBackground();
                DisplayBoard[tileSelected[0]][tileSelected[1]].setBackground(thePiece);

                fixTiles();
                lookforcheck();



                if(enpassantChecker==1){
                    int[]deletepiece = new int[2];
                    deletepiece[0]=eTargetRow;
                    deletepiece[1]=eTargetColumn;
                    fixTiles2(deletepiece);
                }





                //Check for promotions

                checkPromotion();


                //switch turns
                if(playerTurn=='w' && gameover==false && promotionScene==false){
                    playerTurn='b';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("Black's turn.");
                }
                else if(gameover==false && playerTurn=='b' && promotionScene==false){
                    playerTurn='w';
                    playerMove = (TextView)  findViewById(R.id.playerturn);
                    playerMove.setText("White's turn.");
                }
                //save turn order
                int[] timp = {firstMove[0],firstMove[1],tileSelected[0],tileSelected[1]};
                moveSequence.add(timp);

                previousMove[0] = firstMove[0];
                previousMove[1] = firstMove[1];
                previousMove[2] = tileSelected[0];
                previousMove[3] = tileSelected[1];
                rewind=false;










            }else{
                pieceSelected = false;
                //otherwise, tell the user its invalid and reset the selection
                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.VISIBLE);

            }

        }





    }


    public void fixTiles(){
        if(firstMove[0] == 0 || firstMove[0] == 2 || firstMove[0] == 4 || firstMove[0] == 6){
            if(firstMove[1] == 0 || firstMove[1] == 2 || firstMove[1] == 4 || firstMove[1] == 6){
                DisplayBoard[firstMove[0]][firstMove[1]].setBackgroundColor(Color.WHITE);
            }
            else{
                DisplayBoard[firstMove[0]][firstMove[1]].setBackgroundColor(Color.BLACK);
            }
        }


        //1 3 5 7 - black
        else if(firstMove[0] == 1 || firstMove[0] == 3 || firstMove[0] == 5 || firstMove[0] == 7){
            if(firstMove[1] == 0 || firstMove[1] == 2 || firstMove[1] == 4 || firstMove[1] == 6){
                DisplayBoard[firstMove[0]][firstMove[1]].setBackgroundColor(Color.BLACK);
            }
            else{
                DisplayBoard[firstMove[0]][firstMove[1]].setBackgroundColor(Color.WHITE);
            }
        }
    }



    public void fixTiles2(int[]initialSpot){
        if(initialSpot[0] == 0 || initialSpot[0] == 2 || initialSpot[0] == 4 || initialSpot[0] == 6){
            if(initialSpot[1] == 0 || initialSpot[1] == 2 || initialSpot[1] == 4 || initialSpot[1] == 6){
                DisplayBoard[initialSpot[0]][initialSpot[1]].setBackgroundColor(Color.WHITE);
            }
            else{
                DisplayBoard[initialSpot[0]][initialSpot[1]].setBackgroundColor(Color.BLACK);
            }
        }


        //1 3 5 7 - black
        else if(initialSpot[0] == 1 || initialSpot[0] == 3 || initialSpot[0] == 5 || initialSpot[0] == 7){
            if(initialSpot[1] == 0 || initialSpot[1] == 2 || initialSpot[1] == 4 || initialSpot[1] == 6){
                DisplayBoard[initialSpot[0]][initialSpot[1]].setBackgroundColor(Color.BLACK);
            }
            else{
                DisplayBoard[initialSpot[0]][initialSpot[1]].setBackgroundColor(Color.WHITE);
            }
        }
    }






    public void checkPromotion(){
        ImageButton bishopblack = findViewById(R.id.bishopblack);
        ImageButton queenblack = findViewById(R.id.queenblack);
        ImageButton rookblack = findViewById(R.id.rookblack);
        ImageButton knightblack = findViewById(R.id.knightblack);


        if(playerTurn=='w'){
            if(chessBoard[tileSelected[0]][tileSelected[1]].getPiece().equals("wp") && tileSelected[0]==0){
                bishopblack.setVisibility(View.VISIBLE);
                queenblack.setVisibility(View.VISIBLE);
                rookblack.setVisibility(View.VISIBLE);
                knightblack.setVisibility(View.VISIBLE);
                promotionScene = true;
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("Promote a piece.");


            }
        }
        else{
            if(chessBoard[tileSelected[0]][tileSelected[1]].getPiece().equals("bp") && tileSelected[0]==7){

                bishopblack.setVisibility(View.VISIBLE);
                queenblack.setVisibility(View.VISIBLE);
                rookblack.setVisibility(View.VISIBLE);
                knightblack.setVisibility(View.VISIBLE);
                promotionScene = true;
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("Promote a piece.");
            }
        }




    }



    public void clickPromotionBishop(View v){
        ImageButton bishopblack = findViewById(R.id.bishopblack);
        ImageButton queenblack = findViewById(R.id.queenblack);
        ImageButton rookblack = findViewById(R.id.rookblack);
        ImageButton knightblack = findViewById(R.id.knightblack);

        bishopblack.setVisibility(View.INVISIBLE);
        queenblack.setVisibility(View.INVISIBLE);
        rookblack.setVisibility(View.INVISIBLE);
        knightblack.setVisibility(View.INVISIBLE);
        promotionScene=false;

        if(playerTurn=='w'){
                playerTurn='b';
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("Black's turn.");



                chessBoard[tileSelected[0]][tileSelected[1]] = new Bishop();
                chessBoard[tileSelected[0]][tileSelected[1]].setColor('w');

                DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.bishopwhite);

        }
        else{
                playerTurn='w';
                playerMove = (TextView)  findViewById(R.id.playerturn);
                playerMove.setText("White's turn.");


            chessBoard[tileSelected[0]][tileSelected[1]] = new Bishop();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('b');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.bishopblack);

        }






    }
    public void clickPromotionKnight(View v){
        ImageButton bishopblack = findViewById(R.id.bishopblack);
        ImageButton queenblack = findViewById(R.id.queenblack);
        ImageButton rookblack = findViewById(R.id.rookblack);
        ImageButton knightblack = findViewById(R.id.knightblack);

        bishopblack.setVisibility(View.INVISIBLE);
        queenblack.setVisibility(View.INVISIBLE);
        rookblack.setVisibility(View.INVISIBLE);
        knightblack.setVisibility(View.INVISIBLE);
        promotionScene=false;


        if(playerTurn=='w'){
            playerTurn='b';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("Black's turn.");

            chessBoard[tileSelected[0]][tileSelected[1]] = new Knight();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('w');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.knightwhite);
        }
        else{
            playerTurn='w';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("White's turn.");

            chessBoard[tileSelected[0]][tileSelected[1]] = new Knight();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('b');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.knightblack);
        }


    }
    public void clickPromotionRook(View v){
        ImageButton bishopblack = findViewById(R.id.bishopblack);
        ImageButton queenblack = findViewById(R.id.queenblack);
        ImageButton rookblack = findViewById(R.id.rookblack);
        ImageButton knightblack = findViewById(R.id.knightblack);

        bishopblack.setVisibility(View.INVISIBLE);
        queenblack.setVisibility(View.INVISIBLE);
        rookblack.setVisibility(View.INVISIBLE);
        knightblack.setVisibility(View.INVISIBLE);
        promotionScene=false;
        if(playerTurn=='w'){
            playerTurn='b';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("Black's turn.");


            chessBoard[tileSelected[0]][tileSelected[1]] = new Rook();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('w');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.rookwhite);
        }
        else{
            playerTurn='w';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("White's turn.");

            chessBoard[tileSelected[0]][tileSelected[1]] = new Rook();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('b');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.rookblack);
        }


    }
    public void clickPromotionQueen(View v){
        ImageButton bishopblack = findViewById(R.id.bishopblack);
        ImageButton queenblack = findViewById(R.id.queenblack);
        ImageButton rookblack = findViewById(R.id.rookblack);
        ImageButton knightblack = findViewById(R.id.knightblack);

        bishopblack.setVisibility(View.INVISIBLE);
        queenblack.setVisibility(View.INVISIBLE);
        rookblack.setVisibility(View.INVISIBLE);
        knightblack.setVisibility(View.INVISIBLE);
        promotionScene=false;
        if(playerTurn=='w'){
            playerTurn='b';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("Black's turn.");

            chessBoard[tileSelected[0]][tileSelected[1]] = new Queen();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('w');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.queenwhite);





        }
        else{
            playerTurn='w';
            playerMove = (TextView)  findViewById(R.id.playerturn);
            playerMove.setText("White's turn.");

            Piece newPiece =  new Queen();
            newPiece.setColor('b');
            chessBoard[tileSelected[0]][tileSelected[1]] = newPiece;
            chessBoard[tileSelected[0]][tileSelected[1]] = new Queen();
            chessBoard[tileSelected[0]][tileSelected[1]].setColor('b');
            DisplayBoard[tileSelected[0]][tileSelected[1]].setBackgroundResource(R.drawable.queenblack);
        }


    }






    public void lookforcheck(){
        char enemy = 'a';
        //check for check/checkmate
        if(playerTurn=='w'){
            enemy= 'b';
        }
        else{
            enemy = 'w';

        }
        if(Board.check(chessBoard, enemy)){
            if(Board.checkmate(chessBoard,enemy)){
                GridLayout chessboard = findViewById(R.id.gridLayout);

                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.INVISIBLE);
                playerMove = (TextView)  findViewById(R.id.playerturn);

                if(playerTurn=='w'){
                    playerMove.setText("White won!");
                    Button savereplay = findViewById(R.id.savereplay);
                    Button replaycancel = findViewById(R.id.replaycancel);
                    TextView playerevent = findViewById(R.id.playerevent);
                    savereplay.setVisibility(View.VISIBLE);
                    replaycancel.setVisibility(View.VISIBLE);
                    playerevent.setText("Save replay?");
                    playerevent.setVisibility(View.VISIBLE);
                }
                else{
                    playerMove.setText("Black Won!");
                    Button savereplay = findViewById(R.id.savereplay);
                    Button replaycancel = findViewById(R.id.replaycancel);
                    TextView playerevent = findViewById(R.id.playerevent);
                    savereplay.setVisibility(View.VISIBLE);
                    replaycancel.setVisibility(View.VISIBLE);
                    playerevent.setText("Save replay?");
                    playerevent.setVisibility(View.VISIBLE);
                }


                chessboard.setClickable(false);
                System.out.println("checkmate!1");
                gameover = true;
            }else{
                System.out.println("check!");
            }
        }

        if(Board.check(chessBoard, playerTurn)){
            if(Board.checkmate(chessBoard,playerTurn)){
                System.out.println("Game is over!");

                GridLayout chessboard = findViewById(R.id.gridLayout);

                playerEvent = (TextView)  findViewById(R.id.playerevent);
                playerEvent.setVisibility(View.INVISIBLE);
                playerMove = (TextView)  findViewById(R.id.playerturn);

                if(playerTurn=='w'){
                    playerMove.setText("White won!");
                    Button savereplay = findViewById(R.id.savereplay);
                    Button replaycancel = findViewById(R.id.replaycancel);
                    TextView playerevent = findViewById(R.id.playerevent);
                    savereplay.setVisibility(View.VISIBLE);
                    replaycancel.setVisibility(View.VISIBLE);
                    playerevent.setText("Save replay?");
                    playerevent.setVisibility(View.VISIBLE);
                }
                else{
                    playerMove.setText("Black Won!");
                    Button savereplay = findViewById(R.id.savereplay);
                    Button replaycancel = findViewById(R.id.replaycancel);
                    TextView playerevent = findViewById(R.id.playerevent);
                    savereplay.setVisibility(View.VISIBLE);
                    replaycancel.setVisibility(View.VISIBLE);
                    playerevent.setText("Save replay?");
                    playerevent.setVisibility(View.VISIBLE);
                }


                chessboard.setClickable(false);
                gameover = true;
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_game);

         TextView playerMove = (TextView)findViewById(R.id.playerturn);
         TextView playerEvent = (TextView)findViewById(R.id.playerevent);
        Board.createChessBoard(chessBoard);
        attemptMove.reset();

        DisplayBoard[0][0] = (TextView) findViewById(R.id.A8);
        DisplayBoard[0][1] = (TextView) findViewById(R.id.B8);
        DisplayBoard[0][2] = (TextView) findViewById(R.id.C8);
        DisplayBoard[0][3] = (TextView) findViewById(R.id.D8);
        DisplayBoard[0][4] = (TextView) findViewById(R.id.E8);
        DisplayBoard[0][5] = (TextView) findViewById(R.id.F8);
        DisplayBoard[0][6] = (TextView) findViewById(R.id.G8);
        DisplayBoard[0][7] = (TextView) findViewById(R.id.H8);

        DisplayBoard[1][0] = (TextView) findViewById(R.id.A7);
        DisplayBoard[1][1] = (TextView) findViewById(R.id.B7);
        DisplayBoard[1][2] = (TextView) findViewById(R.id.C7);
        DisplayBoard[1][3] = (TextView) findViewById(R.id.D7);
        DisplayBoard[1][4] = (TextView) findViewById(R.id.E7);
        DisplayBoard[1][5] = (TextView) findViewById(R.id.F7);
        DisplayBoard[1][6] = (TextView) findViewById(R.id.G7);
        DisplayBoard[1][7] = (TextView) findViewById(R.id.H7);

        DisplayBoard[2][0] = (TextView) findViewById(R.id.A6);
        DisplayBoard[2][1] = (TextView) findViewById(R.id.B6);
        DisplayBoard[2][2] = (TextView) findViewById(R.id.C6);
        DisplayBoard[2][3] = (TextView) findViewById(R.id.D6);
        DisplayBoard[2][4] = (TextView) findViewById(R.id.E6);
        DisplayBoard[2][5] = (TextView) findViewById(R.id.F6);
        DisplayBoard[2][6] = (TextView) findViewById(R.id.G6);
        DisplayBoard[2][7] = (TextView) findViewById(R.id.H6);

        DisplayBoard[3][0] = (TextView) findViewById(R.id.A5);
        DisplayBoard[3][1] = (TextView) findViewById(R.id.B5);
        DisplayBoard[3][2] = (TextView) findViewById(R.id.C5);
        DisplayBoard[3][3] = (TextView) findViewById(R.id.D5);
        DisplayBoard[3][4] = (TextView) findViewById(R.id.E5);
        DisplayBoard[3][5] = (TextView) findViewById(R.id.F5);
        DisplayBoard[3][6] = (TextView) findViewById(R.id.G5);
        DisplayBoard[3][7] = (TextView) findViewById(R.id.H5);

        DisplayBoard[4][0] = (TextView) findViewById(R.id.A4);
        DisplayBoard[4][1] = (TextView) findViewById(R.id.B4);
        DisplayBoard[4][2] = (TextView) findViewById(R.id.C4);
        DisplayBoard[4][3] = (TextView) findViewById(R.id.D4);
        DisplayBoard[4][4] = (TextView) findViewById(R.id.E4);
        DisplayBoard[4][5] = (TextView) findViewById(R.id.F4);
        DisplayBoard[4][6] = (TextView) findViewById(R.id.G4);
        DisplayBoard[4][7] = (TextView) findViewById(R.id.H4);

        DisplayBoard[5][0] = (TextView) findViewById(R.id.A3);
        DisplayBoard[5][1] = (TextView) findViewById(R.id.B3);
        DisplayBoard[5][2] = (TextView) findViewById(R.id.C3);
        DisplayBoard[5][3] = (TextView) findViewById(R.id.D3);
        DisplayBoard[5][4] = (TextView) findViewById(R.id.E3);
        DisplayBoard[5][5] = (TextView) findViewById(R.id.F3);
        DisplayBoard[5][6] = (TextView) findViewById(R.id.G3);
        DisplayBoard[5][7] = (TextView) findViewById(R.id.H3);

        DisplayBoard[6][0] = (TextView) findViewById(R.id.A2);
        DisplayBoard[6][1] = (TextView) findViewById(R.id.B2);
        DisplayBoard[6][2] = (TextView) findViewById(R.id.C2);
        DisplayBoard[6][3] = (TextView) findViewById(R.id.D2);
        DisplayBoard[6][4] = (TextView) findViewById(R.id.E2);
        DisplayBoard[6][5] = (TextView) findViewById(R.id.F2);
        DisplayBoard[6][6] = (TextView) findViewById(R.id.G2);
        DisplayBoard[6][7] = (TextView) findViewById(R.id.H2);

        DisplayBoard[7][0] = (TextView) findViewById(R.id.A1);
        DisplayBoard[7][1] = (TextView) findViewById(R.id.B1);
        DisplayBoard[7][2] = (TextView) findViewById(R.id.C1);
        DisplayBoard[7][3] = (TextView) findViewById(R.id.D1);
        DisplayBoard[7][4] = (TextView) findViewById(R.id.E1);
        DisplayBoard[7][5] = (TextView) findViewById(R.id.F1);
        DisplayBoard[7][6] = (TextView) findViewById(R.id.G1);
        DisplayBoard[7][7] = (TextView) findViewById(R.id.H1);





        DisplayBoard[1][0].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][1].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][2].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][3].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][4].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][5].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][6].setBackgroundResource(R.drawable.pawnblack);
        DisplayBoard[1][7].setBackgroundResource(R.drawable.pawnblack);


        DisplayBoard[6][0].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][1].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][2].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][3].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][4].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][5].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][6].setBackgroundResource(R.drawable.pawnwhite);
        DisplayBoard[6][7].setBackgroundResource(R.drawable.pawnwhite);


        DisplayBoard[0][2].setBackgroundResource(R.drawable.bishopblack);
        DisplayBoard[0][5].setBackgroundResource(R.drawable.bishopblack);
        DisplayBoard[7][2].setBackgroundResource(R.drawable.bishopwhite);
        DisplayBoard[7][5].setBackgroundResource(R.drawable.bishopwhite);


        DisplayBoard[0][1].setBackgroundResource(R.drawable.knightblack);
        DisplayBoard[0][6].setBackgroundResource(R.drawable.knightblack);
        DisplayBoard[7][1].setBackgroundResource(R.drawable.knightwhite);
        DisplayBoard[7][6].setBackgroundResource(R.drawable.knightwhite);

        DisplayBoard[0][3].setBackgroundResource(R.drawable.queenblack);
        DisplayBoard[7][3].setBackgroundResource(R.drawable.queenwhite);

        DisplayBoard[0][0].setBackgroundResource(R.drawable.rookblack);
        DisplayBoard[0][7].setBackgroundResource(R.drawable.rookblack);
        DisplayBoard[7][0].setBackgroundResource(R.drawable.rookwhite);
        DisplayBoard[7][7].setBackgroundResource(R.drawable.rookwhite);

        DisplayBoard[0][4].setBackgroundResource(R.drawable.kingblack);
        DisplayBoard[7][4].setBackgroundResource(R.drawable.kingwhite);





        if(ReplayGame.replayOn){

            Button aiButton = findViewById(R.id.AI);
            Button resign = findViewById(R.id.resign);
            Button draw = findViewById(R.id.draw);
            Button undo = findViewById(R.id.undoButton);

            aiButton.setVisibility(View.INVISIBLE);
            resign.setVisibility(View.INVISIBLE);
            draw.setVisibility(View.INVISIBLE);
            undo.setVisibility(View.INVISIBLE);

            Button play = findViewById(R.id.play);
            play.setVisibility(View.VISIBLE);


            thereplay = ReplayGame.loadedReplay.getMoves();
            if(thereplay.size()!=0){
                currentMove = thereplay.get(index);
            }


        }







    }


}
