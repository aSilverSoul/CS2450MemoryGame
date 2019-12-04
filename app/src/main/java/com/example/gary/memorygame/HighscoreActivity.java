/***************************************************************
 * file: HighscoreActivity.java
 * author: Gary Machorro, Jaskaranpreet Sidhu
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: The java class that will display the highscores
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity
{
    ListView mListView;
    private JSONSerializer mSerializer;
    private Context mAppContext;

    private static final String FILENAME = "filename";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        mListView = (ListView)findViewById(R.id.highscores);

        mAppContext = getApplicationContext();

        mSerializer = new JSONSerializer(mAppContext, FILENAME);
        ArrayList<Highscore> mScores = new ArrayList<Highscore>();

        try
        {
            mScores = mSerializer.loadScores();
        } catch (Exception e)
        {
            mScores = new ArrayList<Highscore>();

        }

        Integer[] mNewScores = new Integer[mScores.size()];
        mScores.toArray(mNewScores);


        // Declare Array adapter.
        ArrayAdapter<Integer> scoreAdapter = new ArrayAdapter<Integer>(this,android.R.layout
                .simple_list_item_1,mNewScores);

        // Setting the android ListView's adapter to the newly created adapter.
        mListView.setAdapter(scoreAdapter);
    }

    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_board_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
