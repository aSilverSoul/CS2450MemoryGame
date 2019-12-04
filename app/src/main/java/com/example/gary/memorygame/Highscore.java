/***************************************************************
 * file: Highscore.java
 * author: Gary Machorro, Jaskaranpreet Sidhu
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: file that will return and save highscores to
 * json file
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import org.json.JSONException;
import org.json.JSONObject;

public class Highscore
{
    private final static String JSON_SCORE = "score";

    private int mHighscore;

    public Highscore(){}

    public void setScore(int score)
    {
        mHighscore = score;
    }

    public int getScore()
    {
        return mHighscore;
    }

    public Highscore(JSONObject json) throws JSONException
    {
        mHighscore = json.getInt(JSON_SCORE);
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_SCORE, mHighscore);
        return json;
    }


}
