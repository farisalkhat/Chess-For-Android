package com.example.android07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android07.chess.MyApplication;
import com.example.android07.chess.Replay;
import com.example.android07.chess.Replay.ReplaysSortingComparator;
import com.example.android07.chess.ReplayManager;
import com.example.android07.chess.ReplaySorter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReplayGame extends AppCompatActivity {


    ArrayList<Replay> replaylist =  new ArrayList<Replay>();
    public static Replay loadedReplay;
    public static boolean replayOn = false;



    public void sortbydate(View v){
        ReplaySorter replaySorter = new ReplaySorter(replaylist);
        replaylist = replaySorter.getReplaysSortedByDate();
        ListView replays =(ListView)findViewById(R.id.replays);
        CustomAdapter customAdapter = new CustomAdapter();
        replays.setAdapter(customAdapter);

    }

    public void sortbyname(View v){

        Collections.sort(replaylist, new ReplaysSortingComparator());
        System.out.print(replaylist);
        ListView replays =(ListView)findViewById(R.id.replays);
        CustomAdapter customAdapter = new CustomAdapter();
        replays.setAdapter(customAdapter);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_game);

        //load all replays
        try {
            Context context = MyApplication.getAppContext();
            ReplayManager.loadAllReplays(context);
        }catch(IOException ex)
        {System.out.println("IOException is caught: Failure to load replays");}
        catch(ClassNotFoundException ex)
        {System.out.println("ClassNotFoundException is caught: Failure to load replays");}

        replaylist= ReplayManager.getAllReplays();
        //System.out.println(replaylist.get(0).getName());


        ListView replays =(ListView)findViewById(R.id.replays);
        CustomAdapter customAdapter = new CustomAdapter();
        replays.setAdapter(customAdapter);



        // ListView on item selected listener.
        replays.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                loadedReplay= replaylist.get(position);
                replayOn = true;
                startActivity(new Intent(ReplayGame.this, ChessGame.class));
                //Toast.makeText(ReplayGame.this, Names[position], Toast.LENGTH_SHORT).show();
            }
        });











    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount(){
            return replaylist.size();
        }

        @Override
        public Object getItem(int i){
            return null;
        }

        @Override
        public long getItemId(int i){
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup){

            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            TextView titleView = view.findViewById(R.id.titleView);
            TextView dateView = view.findViewById(R.id.dateView);
            //titleView.setText(Names[i]);
            //dateView.setText(Dates[i]);


            titleView.setText(replaylist.get(i).getName());
            dateView.setText(replaylist.get(i).getDate());

            return view;
        }





    }


}
