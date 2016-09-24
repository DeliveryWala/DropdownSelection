package com.example.arif.dropdownselection;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Arif on 24/09/16.
 */
public class Message
{
    public static void message(Context context, String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

}
