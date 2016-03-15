package com.example.hangdroid.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScoresActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        String scores = sharedPreferences.getString("SCORES", "NOSCORES");

        TextView textViewScores = (TextView) findViewById(R.id.textViewScores);
        textViewScores.setText(scores);

    }

}
