/***************************************************************
 * file: AudioPlayer.java
 * author: Gary Machorro,Jaskaranpreet Sidhu
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: This class has the necessary files for playing music within
 * the gameboard used in gameactivity.java
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Context;
import android.media.MediaPlayer;

public class  AudioPlayer
{
    private MediaPlayer mPlayer;

    public void stop()
    {
        if (mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c)
    {
        // Initial call to stop MediaPlayer.
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.jingle);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            public void onCompletion(MediaPlayer mp)
            {
                stop();
            }
        });
        mPlayer.start();
    }
}