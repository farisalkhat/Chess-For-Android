package com.example.android07.chess;

import java.util.ArrayList;
import java.util.Collections;

public class ReplaySorter {

    ArrayList<Replay>replays = new ArrayList<>();

    public ReplaySorter(ArrayList<Replay> replays){
        this.replays = replays;
    }

    public ArrayList<Replay> getReplaysSortedByDate(){
        Collections.sort(replays);
        return replays;
    }






}
