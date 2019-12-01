/***************************************************************
 * file: BoardActivity.java
 * author: Gary Machorro, Preet
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: the class that creates the gameboard according to
 * user input on game size
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BoardActivity extends AppCompatActivity
{

    // String to be used a a key in the key-value pair when passing the Extra.
    public static final String EXTRA_GAMESIZE = "com.example.gary.memorygameproject.gameSize";
    // Define the androidListView.
    ListView mListView;
    // Elements that will be displayed in ListView.
    String[] gameTypes = new String[] {"4 cards", "6 cards", "8 cards", "10 cards", "12 cards",
            "14 cards", "16 cards", "18 cards", "20 cards"};

    // Initialization methods.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        mListView = (ListView) findViewById(R.id.list);

        // Declare Array adapter.
        ArrayAdapter<String> gameAdapter = new ArrayAdapter<String>(this,android.R.layout
                .simple_list_item_1, gameTypes);

        // Setting the android ListView's adapter to the newly created adapter.
        mListView.setAdapter(gameAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Creates a variable to hold the selected item from the list.
                String ETGameSize = (String)mListView.getItemAtPosition(position);

                // Launches the GameActivity depending on the size of ETGameSize.
                switch (ETGameSize)
                {
                    case "4 cards":
                        Intent a = new Intent(BoardActivity.this, GameActivity.class);
                        a.putExtra("GameSize", (int) 2);
                        startActivity(a);
                        break;
                    case "6 cards":
                        Intent b = new Intent(BoardActivity.this, GameActivity.class);
                        b.putExtra("GameSize", (int) 3);
                        startActivity(b);
                        break;
                    case "8 cards":
                        Intent c = new Intent(BoardActivity.this, GameActivity.class);
                        c.putExtra("GameSize", (int) 4);
                        startActivity(c);
                        break;
                    case "10 cards":
                        Intent d = new Intent(BoardActivity.this, GameActivity.class);
                        d.putExtra("GameSize", (int) 5);
                        startActivity(d);
                        break;
                    case "12 cards":
                        Intent e = new Intent(BoardActivity.this, GameActivity.class);
                        e.putExtra("GameSize", (int) 6);
                        startActivity(e);
                        break;
                    case "14 cards":
                        Intent f = new Intent(BoardActivity.this, GameActivity.class);
                        f.putExtra("GameSize", (int) 7);
                        startActivity(f);
                        break;
                    case "16 cards":
                        Intent g = new Intent(BoardActivity.this, GameActivity.class);
                        g.putExtra("GameSize", (int) 8);
                        startActivity(g);
                        break;
                    case "18 cards":
                        Intent h = new Intent(BoardActivity.this, GameActivity.class);
                        h.putExtra("GameSize", (int) 9);
                        startActivity(h);
                        break;
                    case "20 cards":
                        Intent i = new Intent(BoardActivity.this, GameActivity.class);
                        i.putExtra("GameSize", (int) 10);
                        startActivity(i);
                        break;
                }
            }
        });
    }


} // End class BoardActivity.
