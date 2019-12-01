/***************************************************************
 * file: NameEntryFragment.java
 * author: Gary Machorro, Preet
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: allows for entry of name on start
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class NameEntryFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.name_entry_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
