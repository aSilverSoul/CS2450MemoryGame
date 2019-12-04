/***************************************************************
 * file: MainActivity.java
 * author: Gary Machorro, Jaskaranpreet Sidhu
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: The necessary methods that instantiate the game
 * or the highscore activities
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity
{
    private Button mPlayButton;
    private Button mHighscoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Sets the Play Button to start the game. BoardActivity is launched to select the game
        // size.
        mPlayButton = (Button)findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(i);
            }
        });

        mHighscoreButton = (Button)findViewById(R.id.highscore_button);
        mHighscoreButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
        public void onClick(View v)
           {
               Intent i = new Intent(getApplicationContext(), HighscoreActivity.class);
               startActivity(i);
           }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
