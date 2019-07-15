package com.example.android07.chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;



public class Replay implements Comparable,Serializable {
    ArrayList<int[]> moves;
    String name;
    Date dayPlayed;

    public Replay(ArrayList<int[]> m, String n) {
        moves = m;
        name = n;
        dayPlayed = new Date();
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return dayPlayed.toString();
    }

    public Date getDateDate(){return dayPlayed;}

    public ArrayList<int[]> getMoves(){
        return moves;
    }


    @Override
    public int compareTo(Object replayO) {
        Replay replay = (Replay) replayO;
        if (this.getDateDate().compareTo(replay.getDateDate()) < 0) {
            return -1;
            //return 0;
            //return this.getDate();
        }
        else{
            return 0;
        }
    }



        public static class ReplaysSortingComparator implements Comparator<Replay>{
            @Override
            public int compare(Replay jc1, Replay jc2) {
                return (int) (jc1.getName().compareTo(jc2.getName()));
            }
        }




}



