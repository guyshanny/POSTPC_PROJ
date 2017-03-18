package com.example.guy.ex1;

/**
 * Created by GUY on 3/18/2017.
 */

import java.util.HashMap;

/**
 * This class represents a container for the chat message
 */
public class ChatMessage
{
    public static HashMap<String, Integer> _colorBase;

    private String _name;           // Chatter name
    private String _message;        // Chatter message
    private Integer _color;         // Chatter's color

    /**
     * C'tor for this obj
     * @param name the chatter's name
     * @param message the message
     * @param color the color of the chatter's messages
     */
    ChatMessage(String name, String message, Integer color)
    {
        this._name = name;
        this._message = message;
        this._color = color;
    }

    /**
     * Handles the "toString" of this obj
     * @return the string represents the obj
     */
    @Override
    public String toString()
    {
        return this._name + ":  " + this._message;
    }
}