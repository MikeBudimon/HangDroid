package com.example.hangdroid.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startSinglePlayerGame(new View(this));
    }

    public void startSinglePlayerGame(View v){

        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }

    public void startMultiGame(View v){

        Intent myIntent = new Intent(this, MultiplayerActivity.class);
        startActivity(myIntent);
    }

    public void openScores(View v){
        Intent myIntent = new Intent(this, ScoresActivity.class);
        startActivity(myIntent);
    }
}
