/***************************************************************
 * file: Card.java
 * author: Gary Machorro, Jaskaranpreet Sidhu
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: the properties of  card that are created in the
 * game activity
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Context;

public class Card extends android.support.v7.widget.AppCompatButton
{
    // Initializes the variables and sets matched to false to clear
    // the value every time this class is created.
    private int id;
    private boolean matched = false;

    // Constructor for a Card object.
    public Card(Context context)
    {
        super(context);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setMatched(boolean matched)
    {
        this.matched = matched;
    }

    public boolean getMatched()
    {
        return this.matched;
    }

    public void setText(Integer integer)
    {

    }

}

