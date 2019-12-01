/***************************************************************
 * file: JSONSerializer.java
 * author: Gary Machorro, Preet
 * class: CS 2450 – Programming Graphical User Interfaces
 *
 * assignment: Project 2
 * date last modified: 12/3/19
 *
 * purpose: holds the data used in the game such as the highscores
 *
 *
 ****************************************************************/
package com.example.gary.memorygame;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class JSONSerializer
{
    private Context mContext;
    private String mFilename;

    public JSONSerializer(Context c, String f)
    {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<Highscore> loadScores() throws IOException, JSONException
    {
        ArrayList<Highscore> scores = new ArrayList<Highscore>();
        BufferedReader reader = null;
        try
        {
            // Open and read the file into a StringBuilder.
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                jsonString.append(line);
            }
            // Parse the JSON using JSONTokener.
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // Build the array of scores from JSONObjects.
            for (int i = 0; i < array.length(); i++)
            {
                scores.add(new Highscore(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e)
        {
            // Ignore this one, it happens when starting fresh.
            int x = 1;
        } finally
        {
            if (reader != null)
                reader.close();
        }

        Integer[] mNewScores = new Integer[scores.size()];
        scores.toArray(mNewScores);

        return scores;
    }

    public void saveScores(ArrayList<Highscore> scores) throws JSONException, IOException
    {
        // Build an array in JSON.
        JSONArray array = new JSONArray();
        for (Highscore h : scores)
                array.put(h.toJSON());

        // Write the files to the disk.
        Writer writer = null;
        try
        {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally
        {
            if (writer != null)
                writer.close();
        }
    }


}
