package com.example.android07.chess;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReplayManager {

    private static ArrayList<Replay> AllReplays = new ArrayList<Replay>();

    String lmao = AllReplays.get(1).getName();


    //serializes all replays
    public static void saveAllReplays(Context context)throws IOException {

        File f = new File(".");
        System.out.println(f.getAbsolutePath()+"       findmeplease");
        FileOutputStream fos = context.openFileOutput("savedReplays.dat", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(AllReplays);
        oos.close();
        fos.close();

    }

    //loads all replays from serlialized file
    public static void loadAllReplays(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("savedReplays.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        AllReplays = (ArrayList)ois.readObject();
        ois.close();
        fis.close();
    }

    //lets a finished game be added to the list
    public static void saveReplay(Replay t, Context context) {
        AllReplays.add(t);
        try {
            saveAllReplays(context);

        }catch(IOException ex){
            System.out.println("IOException is caught: failure to save new replay");
        }
    }

    //get wrapper for the list of replays
    public static ArrayList<Replay> getAllReplays(){
        return AllReplays;
    }
}
