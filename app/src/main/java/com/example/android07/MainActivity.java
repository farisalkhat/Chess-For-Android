package com.example.android07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android07.chess.MyApplication;
import com.example.android07.chess.ReplayManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] menuItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.main_menu_list);
        menuItems = getResources().getStringArray(R.array.main_list);

        try {
            Context context = MyApplication.getAppContext();
            ReplayManager.loadAllReplays(context);
        }catch(IOException ex)
        {System.out.println("IOException is caught: Failure to load replays");}
        catch(ClassNotFoundException ex)
        {System.out.println("ClassNotFoundException is caught: Failure to load replays");}



        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.menu, menuItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                showScene(position);
            }
        });





    }

    private void showScene(int pos) {
        Intent intent = new Intent(this, ChessGame.class);


        if(pos==0){
            intent = new Intent(this, ChessGame.class);
        }
        if(pos==1){
            intent = new Intent(this, ReplayGame.class);
        }
        if(pos==2){
            intent = new Intent(this, AboutUs.class);
        }


        startActivity(intent);
    }

}
