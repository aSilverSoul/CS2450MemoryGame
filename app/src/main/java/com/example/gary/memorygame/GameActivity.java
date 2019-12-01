/***************************************************************
 * file: GameActivity.java
 * author: Gary Machorro, Preet
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: This is the main class that handles the game logic
 * the card.java,audioplayer.java, and json file are used
 *
 *
 ****************************************************************/

package com.example.gary.memorygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    // Tags for key-value pairs.
    private static final String TRIES_REMAINING = "tries_left";
    private static final String CURRENT_SCORE = "current_score";
    private static final String BUTTON_VALUES = "button_values";
    private static final String CHAR_SEQUENCE = "char_sequence";
    private static final String INTEGER_ARRAY = "integer_array";
    private static final String ENABLED = "enabled";
    private static final String C1VAL = "c1Val";
    private static final String C2VAL = "c2Val";
    private static final String FILENAME = "filename";

    // Declaration of necessary variables.
    private Card selectedCard;
    private Button mNewGame;
    private Button mTryAgain;
    private List<Card> cards;
    private CharSequence[] charSequenceForButtons;
    private Card c1;
    private Card c2;
    int triesLeft;
    TextView mTriesRemaining;
    TextView mScoreNumber;
    int mScoreIncrement;
    int mTotalScore;
    boolean enabled;
    int c1Val;
    int c2Val;
    ArrayList<Integer> cardVals = new ArrayList<Integer>();
    Context mContext;
    private AudioPlayer mPlayer = new AudioPlayer();
    private JSONSerializer mSerializer;


    public void onClickPlayMusic(View view) {
        mPlayer.play(this);

    }

    public void onClickStopMusic(View view) {
        mPlayer.stop();
    }


    private void startGame() {
        // Receives the game size from BoardActivity.
        Bundle extras = getIntent().getExtras();
        int gameSize = extras.getInt("GameSize");
        // Creates an empty ArrayList.
        List<Card> cardList = new ArrayList<Card>();
        mScoreIncrement = 1000;

        // Initializes the Score and Tries Left fields on the game screen.
        mTriesRemaining = (TextView) findViewById(R.id.triesLeftNumber);
        mTriesRemaining.setText(triesLeft + "");
        mScoreNumber = (TextView) findViewById(R.id.score_number);
        mScoreNumber.setText(mTotalScore + "");

        // Sets the New Game button to launch MenuActivity to create a new game.
        mNewGame = (Button) findViewById(R.id.new_game);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        // Loop through the 10 pairs and add each incremented values to
        // the ArrayList twice so that a pair will exist.
        if (cardVals.size() < 1) {
            for (int i = 0; i < gameSize; i++) {
                cardVals.add(i);
                cardVals.add(i);
            }
            // Shuffles the ArrayList of cards for a new game.
            Collections.shuffle(cardVals);
        }

        // Gets references to the layouts used to populate the game.
        ViewGroup row1 = (ViewGroup) findViewById(R.id.row1);
        ViewGroup row2 = (ViewGroup) findViewById(R.id.row2);
        ViewGroup row3 = (ViewGroup) findViewById(R.id.row3);
        ViewGroup row4 = (ViewGroup) findViewById(R.id.row4);
        ViewGroup row5 = (ViewGroup) findViewById(R.id.row5);
        ViewGroup row6 = (ViewGroup) findViewById(R.id.row6);
        ViewGroup row7 = (ViewGroup) findViewById(R.id.row7);
        int count = 0;

        // Loops through each Card Value in the ArrayList to create Card objects that is a Button.
        for (final int val : cardVals) {
            final Card c = new Card(this);
            c.setId(val);
            c.setText("");
            c.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams
                            .WRAP_CONTENT));

            // Determines where in the layout to add the newly created button.
            if (count < 3) {
                row1.addView(c);
            } else if (count >= 3 && count <= 5) {
                row2.addView(c);
            } else if (count >= 6 && count <= 8) {
                row3.addView(c);
            } else if (count >= 9 && count <= 11) {
                row4.addView(c);
            } else if (count >= 12 && count <= 14) {
                row5.addView(c);
            } else if (count >= 15 && count <= 17) {
                row6.addView(c);
            } else if (count >= 18 && count <= 20) {
                row7.addView(c);
            }

            count++;

            // Calls the flipCard() method when a Card button is clicked.
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // When the user presses a button, selectedCard is set to the current card
                    // selected.
                    selectedCard = c;
                    flipCard();
                }
            });
            // Each Card object that is created is added to the cardsList ArrayList.
            cardList.add(c);
        }

        if (charSequenceForButtons != null) {
            if (charSequenceForButtons.length > 0) {
                for (int i = 0; i < cardList.size(); i++) {
                    cardList.get(i).setText(charSequenceForButtons[i]);
                    cardList.get(i).setEnabled(enabled);
                }

                if (c1Val != c2Val) {
                    mTryAgain = (Button) findViewById(R.id.try_again);
                    mTryAgain.setEnabled(true);

                    mTryAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Card c : cards) {
                                c.setText("");
                                c.setEnabled(true);
                            }

                            mTryAgain.setEnabled(false);
                        }
                    });
                }

                mNewGame.setEnabled(true);
            }
        }

        this.cards = cardList;


    }

    // Private method that stores the clicked Card butons in storage variables to prepre for
    // checkCard().
    private void flipCard() {
        if (c1 == null && c2 == null) {
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getId()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null) {
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getId()));
            checkCard();
        }
    }

    // Private method determines whether two clicked Card buttons match each other. If they do,
    // isGameWon() is called to determine whether all possible matches have been made. If they
    // don't match, the screen resets itself for the next try.
    private void checkCard() {
        if (c1.getId() == c2.getId()) {
            c1.setEnabled(false);
            c2.setEnabled(false);
            c1.setMatched(true);
            c2.setMatched(true);
            mScoreNumber = (TextView) findViewById(R.id.score_number);
            mTotalScore = mTotalScore + mScoreIncrement;
            mScoreNumber.setText("" + mTotalScore);

            // If the game is won, a congratulatory message is displayed and the screen prepares
            // for a new game to be clicked.
            if (this.isGameWon()) {
                Toast ImageToast = new Toast(getBaseContext());
                LinearLayout toastLayout = new LinearLayout(getBaseContext());
                toastLayout.setOrientation(LinearLayout.HORIZONTAL);

                ImageView image = new ImageView(getBaseContext());
                TextView text = new TextView(getBaseContext());

                image.setImageResource(R.mipmap.ic_launcher);
                text.setText("You won!");
                text.setTextColor(Color.BLUE);

                toastLayout.addView(image);
                toastLayout.addView(text);

                ImageToast.setView(toastLayout);
                ImageToast.setDuration(Toast.LENGTH_LONG);
                ImageToast.show();

                //Toast.makeText(this, "You Won!", Toast.LENGTH_LONG).show();

                mNewGame = (Button) findViewById(R.id.new_game);
                mNewGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(GameActivity.this, MenuActivity.class);
                        startActivity(i);
                    }
                });
            }
        } else {
            mTriesRemaining = (TextView) findViewById(R.id.triesLeftNumber);
            triesLeft--;
            mTriesRemaining.setText("" + triesLeft);

            for (Card c : cards) {
                c.setMatched(false);
            }

            mScoreIncrement = mScoreIncrement - 100;

            if (triesLeft == 0) {
                gameOver();
            } else {
                mTryAgain = (Button) findViewById(R.id.try_again);
                mTryAgain.setEnabled(true);
                mTotalScore = 0;
                mScoreNumber = (TextView) findViewById(R.id.score_number);
                mScoreNumber.setText(mTotalScore + "");

                for (Card c : cards) {
                    c.setEnabled(false);
                }

                mTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Card c : cards) {
                            c.setText("");
                            c.setEnabled(true);
                        }
                        mTryAgain.setEnabled(false);
                    }
                });
            }
        }
        c1Val = c1.getId();
        c2Val = c2.getId();
        c1 = null;
        c2 = null;
    }

    // Loops through the entire ArrayList to check if the matched property is set to true for all
    // the Card objects.
    private boolean isGameWon() {
        for (Card c : this.cards) {
            if (c.getMatched() == false) {
                return false;
            }
        }
        saveScore(this);
        return true;

    }

    private void saveScore(Context mAppContext) {
        mSerializer = new JSONSerializer(mAppContext, FILENAME);
        ArrayList<Highscore> mScores = new ArrayList<Highscore>();

        try {
            mScores = mSerializer.loadScores();
            Highscore highscore = new Highscore();
            highscore.setScore(mTotalScore);
            mScores.add(highscore);
            // TODO: sort scores and display top 3.
            mSerializer.saveScores(mScores);
        } catch (Exception e) {
            mScores = new ArrayList<Highscore>();
        }

    }

    // Once no more tries remain the game is over. All Card objects are reset and the new game
    // button is the only thing enabled on the screen.
    private void gameOver() {
        for (Card c : this.cards) {
            c.setText("");
            c.setEnabled(false);
        }
        //mTryAgain.setEnabled(false);
        mNewGame = (Button) findViewById(R.id.new_game);
        mNewGame.setEnabled(true);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });
        Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show();

    }


    // Activity Initilization Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the view.
        setContentView(R.layout.game);

        // Pulls values from savedInstanceState when the screen is rotated if the instance
        // already exists.
        if (savedInstanceState != null) {
            triesLeft = savedInstanceState.getInt(TRIES_REMAINING);
            mTotalScore = savedInstanceState.getInt(CURRENT_SCORE);
            charSequenceForButtons = savedInstanceState.getCharSequenceArray(CHAR_SEQUENCE);
            cardVals = savedInstanceState.getIntegerArrayList(INTEGER_ARRAY);
            enabled = savedInstanceState.getBoolean(ENABLED);
            c1Val = savedInstanceState.getInt(C1VAL);
            c2Val = savedInstanceState.getInt(C2VAL);
        } else {
            triesLeft = 10;
            mTotalScore = 0;
            charSequenceForButtons = new CharSequence[0];
            cardVals = new ArrayList<>();
        }
        // Method call to start the game logic.
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Loops through the Card array and saves the properties of the objects for a screen
        // rotation.
        CharSequence[] cardList = new CharSequence[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            cardList[i] = cards.get(i).getText();
            enabled = cards.get(i).isEnabled();
        }
        // Puts values into savedInstanceState to retrieve upon rotation.
        savedInstanceState.putInt(C1VAL, c1Val);
        savedInstanceState.putInt(C2VAL, c2Val);
        savedInstanceState.putBoolean(ENABLED, enabled);
        savedInstanceState.putCharSequenceArray(CHAR_SEQUENCE, cardList);
        savedInstanceState.putIntegerArrayList(INTEGER_ARRAY, cardVals);
        savedInstanceState.putInt(TRIES_REMAINING, triesLeft);
        savedInstanceState.putInt(CURRENT_SCORE, mTotalScore);
    } // End onSaveInstanceState

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mPlayer.stop();
    }

}


